package net.seagullboi.originofspirits.registry;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.YeastItem;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TOOSVillagerTrades {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

        if (event.getType() == VillagerProfession.FARMER) {
            trades.get(3).add(new BasicTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(TOOSItems.BEANS.get(), 2), 16, 20, 2));
            trades.get(3).add(new BasicTrade(new ItemStack(TOOSItems.BEANS.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 20, 2));
            trades.get(3).add(new BasicTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(YeastItem.block, 3), 16, 20, 2));
        }
    }
        public static void registerCustomTrades() {
            // Gunsmith TRADES
            VillagerTrades.ITrade[] gunsmithLevel1 = new VillagerTrades.ITrade[]{
                    new TOOSVillagerTrades.EmeraldForItemsTrade(TOOSItems.IRON_BULLET.get(), 5, 16, 2, 0.5f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.IRON_BULLET.get(), 1, 16, 2, 0.5f, 4),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.IRON_GUN_UPGRADE_TOKEN.get(), 5, 16, 5, 0.5f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.PISTOL.get(), 15, 16, 10, 0.2f, 1),
            };
            VillagerTrades.ITrade[] gunsmithLevel2 = new VillagerTrades.ITrade[]{
                    new TOOSVillagerTrades.EmeraldForItemsTrade(TOOSItems.SHOTGUN_SHELLS.get(), 5, 16, 2, 0.5f, 2),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.SHOTGUN_SHELLS.get(), 2, 16, 2, 0.5f, 2),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.IRON_GUN_UPGRADE_TOKEN.get(), 4, 5, 10, 0.5f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.IRON_BULLET.get(), 2, 16, 2, 0.5f, 6),
            };
            VillagerTrades.ITrade[] gunsmithLevel3 = new VillagerTrades.ITrade[]{
                    new TOOSVillagerTrades.EmeraldForItemsTrade(Items.REDSTONE, 10, 16, 2, 0.5f, 3),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.SHOTGUN_SHELLS.get(), 3, 16, 2, 0.5f, 4),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get(), 7, 16, 10, 0.5f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.SHOTGUN.get(), 25, 16, 15, 0.5f, 1),
            };
            VillagerTrades.ITrade[] gunsmithLevel4 = new VillagerTrades.ITrade[]{
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.PISTOL.get(), 13, 16, 10, 0.2f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get(), 6, 16, 10, 0.5f, 1),
            };
            VillagerTrades.ITrade[] gunsmithLevel5 = new VillagerTrades.ITrade[]{
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.REDSTONE_HANDGUN.get(), 33, 16, 20, 0.2f, 1),
                    new TOOSVillagerTrades.ItemsForEmeraldTrade(TOOSItems.DIAMOND_GUN_UPGRADE_TOKEN.get(), 14, 16, 10, 0.5f, 1),

            };

            VillagerTrades.VILLAGER_DEFAULT_TRADES.put(ModVillagers.GUNSMITH.get(), getAsIntMap(ImmutableMap.of(1, gunsmithLevel1, 2, gunsmithLevel2, 3, gunsmithLevel3, 4, gunsmithLevel4, 5, gunsmithLevel5)));

        }
    private static Int2ObjectMap<VillagerTrades.ITrade[]> getAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    public static class EmeraldForItemsTrade implements VillagerTrades.ITrade {
        private final Item tradeItem;
        private final int count;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;
        private final int emeraldPrice;

        public EmeraldForItemsTrade(IItemProvider tradeItemIn, int countIn, int maxUsesIn, int xpValueIn, float priceMultiplier, int emeraldPrice) {
            this.tradeItem = tradeItemIn.asItem();
            this.count = countIn;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
            this.priceMultiplier = priceMultiplier;
            this.emeraldPrice = emeraldPrice;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, emeraldPrice), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }

    public static class ItemsForEmeraldTrade implements VillagerTrades.ITrade {
        private final Item tradeItem;
        private final int count;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;
        private final int itemPrice;

        public ItemsForEmeraldTrade(IItemProvider tradeItemIn, int countIn, int maxUsesIn, int xpValueIn, float priceMultiplier, int itemPrice) {
            this.tradeItem = tradeItemIn.asItem();
            this.count = countIn;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
            this.priceMultiplier = priceMultiplier;
            this.itemPrice = itemPrice;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, count), new ItemStack(this.tradeItem, itemPrice), this.maxUses, this.xpValue, this.priceMultiplier);
        }
    }

}
