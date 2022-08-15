package net.seagullboi.originofspirits.entity;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.seagullboi.originofspirits.item.BarleyBreadItem;
import net.seagullboi.originofspirits.item.SmenerelCoinItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.villager.IVillagerDataHolder;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CaecanusTrade {
    public static int maxUseIn = 9999999;

    public static final Map<VillagerProfession, Int2ObjectMap<CaecanusTrade.ITrade[]>> VILLAGER_DEFAULT_TRADES = Util.make(Maps.newHashMap(), (p_221237_0_) -> {
        p_221237_0_.put(VillagerProfession.FARMER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.WHEAT, 20, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.POTATO, 26, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.CARROT, 22, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.BEETROOT, 15, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(BarleyBreadItem.block, 1, 6, 16, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Blocks.PUMPKIN, 6, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Items.PUMPKIN_PIE, 1, 4, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Items.APPLE, 1, 4, 16, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.COOKIE, 3, 18, 10), new CaecanusTrade.EmeraldForItemsTrade(Blocks.MELON, 4, 12, 20)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CAKE, 1, 1, 12, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.NIGHT_VISION, 100, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.JUMP_BOOST, 160, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.WEAKNESS, 140, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.BLINDNESS, 120, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.POISON, 280, 15), new CaecanusTrade.SuspiciousStewForEmeraldTrade(Effects.SATURATION, 7, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.GOLDEN_CARROT, 3, 3, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GLISTERING_MELON_SLICE, 4, 3, 30)})));
        p_221237_0_.put(VillagerProfession.FISHERMAN, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.STRING, 20, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.COAL, 10, 16, 2), new CaecanusTrade.ItemsForEmeraldsAndItemsTrade(Items.COD, 6, Items.COOKED_COD, 6, 16, 1), new CaecanusTrade.ItemsForEmeraldsTrade(Items.COD_BUCKET, 3, 1, 16, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.COD, 15, 16, 10), new CaecanusTrade.ItemsForEmeraldsAndItemsTrade(Items.SALMON, 6, Items.COOKED_SALMON, 6, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Items.CAMPFIRE, 2, 1, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.SALMON, 13, 16, 20), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.FISHING_ROD, 3, 3, 10, 0.2F)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.TROPICAL_FISH, 6, 12, 30)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.PUFFERFISH, 4, 12, 30), new CaecanusTrade.EmeraldForVillageTypeItemTrade(1, 12, 30, ImmutableMap.<VillagerType, Item>builder().put(VillagerType.PLAINS, Items.OAK_BOAT).put(VillagerType.TAIGA, Items.SPRUCE_BOAT).put(VillagerType.SNOW, Items.SPRUCE_BOAT).put(VillagerType.DESERT, Items.JUNGLE_BOAT).put(VillagerType.JUNGLE, Items.JUNGLE_BOAT).put(VillagerType.SAVANNA, Items.ACACIA_BOAT).put(VillagerType.SWAMP, Items.DARK_OAK_BOAT).build())})));
        p_221237_0_.put(VillagerProfession.SHEPHERD, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Blocks.WHITE_WOOL, 18, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Blocks.BROWN_WOOL, 18, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Blocks.BLACK_WOOL, 18, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Blocks.GRAY_WOOL, 18, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.RED_WOOL, 2, 1, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.WHITE_DYE, 12, 16, 10), new CaecanusTrade.EmeraldForItemsTrade(Items.GRAY_DYE, 12, 16, 10), new CaecanusTrade.EmeraldForItemsTrade(Items.BLACK_DYE, 12, 16, 10), new CaecanusTrade.EmeraldForItemsTrade(Items.LIGHT_BLUE_DYE, 12, 16, 10), new CaecanusTrade.EmeraldForItemsTrade(Items.LIME_DYE, 12, 16, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.WHITE_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.ORANGE_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.MAGENTA_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.YELLOW_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIME_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PINK_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GRAY_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CYAN_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PURPLE_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLUE_WOOL, 2, 2, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BROWN_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GREEN_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.RED_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLACK_WOOL, 1, 1, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.WHITE_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.ORANGE_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.MAGENTA_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.YELLOW_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIME_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PINK_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GRAY_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CYAN_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PURPLE_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLUE_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BROWN_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GREEN_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.RED_CARPET, 1, 4, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLACK_CARPET, 1, 4, 16, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.YELLOW_DYE, 12, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.LIGHT_GRAY_DYE, 12, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.ORANGE_DYE, 12, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.RED_DYE, 12, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.PINK_DYE, 12, 16, 20), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.WHITE_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.YELLOW_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.RED_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLACK_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLUE_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BROWN_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CYAN_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GRAY_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GREEN_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIME_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.MAGENTA_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.ORANGE_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PINK_BED, 3, 1, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PURPLE_BED, 3, 1, 12, 10)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.BROWN_DYE, 12, 16, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.PURPLE_DYE, 12, 16, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.BLUE_DYE, 12, 16, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.GREEN_DYE, 12, 16, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.MAGENTA_DYE, 12, 16, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.CYAN_DYE, 12, 16, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Items.WHITE_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BLUE_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.RED_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.PINK_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GREEN_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIME_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GRAY_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BLACK_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.PURPLE_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.MAGENTA_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.CYAN_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BROWN_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.YELLOW_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.ORANGE_BANNER, 3, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.PAINTING, 2, 3, 30)})));
        p_221237_0_.put(VillagerProfession.FLETCHER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.STICK, 32, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.ARROW, 1, 16, 1), new CaecanusTrade.ItemsForEmeraldsAndItemsTrade(Blocks.GRAVEL, 10, Items.FLINT, 10, 12, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.FLINT, 26, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BOW, 2, 1, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.STRING, 14, 16, 20), new CaecanusTrade.ItemsForEmeraldsTrade(Items.CROSSBOW, 3, 1, 10)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.FEATHER, 24, 16, 30), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.BOW, 2, 3, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.TRIPWIRE_HOOK, 8, 12, 30), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.CROSSBOW, 3, 3, 15), new CaecanusTrade.ItemWithPotionForEmeraldsAndItemsTrade(Items.ARROW, 5, Items.TIPPED_ARROW, 5, 2, 12, 30)})));
        p_221237_0_.put(VillagerProfession.LIBRARIAN, gatAsIntMap(ImmutableMap.<Integer, CaecanusTrade.ITrade[]>builder().put(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.PAPER, 24, 16, 2), new CaecanusTrade.EnchantedBookForEmeraldsTrade(1), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BOOKSHELF, 9, 1, 12, 1)}).put(2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.BOOK, 4, 12, 10), new CaecanusTrade.EnchantedBookForEmeraldsTrade(5), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LANTERN, 1, 1, 5)}).put(3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.INK_SAC, 5, 12, 20), new CaecanusTrade.EnchantedBookForEmeraldsTrade(10), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GLASS, 1, 4, 10)}).put(4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.WRITABLE_BOOK, 2, 12, 30), new CaecanusTrade.EnchantedBookForEmeraldsTrade(15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.CLOCK, 5, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.COMPASS, 4, 1, 15)}).put(5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.NAME_TAG, 20, 1, 30)}).build()));
        p_221237_0_.put(VillagerProfession.CARTOGRAPHER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.PAPER, 24, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.MAP, 7, 1, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.GLASS_PANE, 11, 16, 10), new CaecanusTrade.EmeraldForMapTrade(13, Structure.MONUMENT, MapDecoration.Type.MONUMENT, 12, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.COMPASS, 1, 12, 20), new CaecanusTrade.EmeraldForMapTrade(14, Structure.WOODLAND_MANSION, MapDecoration.Type.MANSION, 12, 10)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.ITEM_FRAME, 7, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.WHITE_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BLUE_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIGHT_BLUE_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.RED_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.PINK_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GREEN_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIME_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.GRAY_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BLACK_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.PURPLE_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.MAGENTA_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.CYAN_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BROWN_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.YELLOW_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.ORANGE_BANNER, 3, 1, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LIGHT_GRAY_BANNER, 3, 1, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Items.GLOBE_BANNER_PATTERN, 8, 1, 30)})));
        p_221237_0_.put(VillagerProfession.CLERIC, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.ROTTEN_FLESH, 32, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.REDSTONE, 1, 2, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.GOLD_INGOT, 3, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Items.LAPIS_LAZULI, 1, 1, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.RABBIT_FOOT, 2, 12, 20), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GLOWSTONE, 4, 1, 12, 10)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.SCUTE, 4, 12, 30), new CaecanusTrade.EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 12, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Items.ENDER_PEARL, 5, 1, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.NETHER_WART, 22, 12, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Items.EXPERIENCE_BOTTLE, 3, 1, 30)})));
        p_221237_0_.put(VillagerProfession.ARMORER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.IRON_INGOT, 15, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 7, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2F)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2F)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.LAVA_BUCKET, 1, 12, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 20), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2F)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2F)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_HELMET, 8, 3, 30, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2F)})));
        p_221237_0_.put(VillagerProfession.WEAPONSMITH, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.IRON_SWORD, 15, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.IRON_SWORD, 2, 3, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.FLINT, 24, 12, 20)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.IRON_AXE, 8, 3, 30, 0.2F)})));
        p_221237_0_.put(VillagerProfession.TOOLSMITH, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2F)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.FLINT, 30, 12, 20), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.IRON_AXE, 1, 3, 10, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.IRON_SHOVEL, 2, 3, 10, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.IRON_PICKAXE, 3, 3, 10, 0.2F), new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2F)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F), new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2F)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EnchantedItemForEmeraldsTrade(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2F)})));
        p_221237_0_.put(VillagerProfession.BUTCHER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.CHICKEN, 14, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.PORKCHOP, 7, 16, 2), new CaecanusTrade.EmeraldForItemsTrade(Items.RABBIT, 4, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.RABBIT_STEW, 1, 1, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.COOKED_PORKCHOP, 1, 5, 16, 5), new CaecanusTrade.ItemsForEmeraldsTrade(Items.COOKED_CHICKEN, 1, 8, 16, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.MUTTON, 7, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Items.BEEF, 10, 16, 20)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.DRIED_KELP_BLOCK, 10, 12, 30)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.SWEET_BERRIES, 10, 12, 30)})));
        p_221237_0_.put(VillagerProfession.LEATHERWORKER, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.LEATHER, 6, 16, 2), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_LEGGINGS, 3), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_CHESTPLATE, 7)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.FLINT, 26, 12, 10), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_HELMET, 5, 12, 5), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_BOOTS, 4, 12, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.RABBIT_HIDE, 9, 12, 20), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_CHESTPLATE, 7)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.SCUTE, 4, 12, 30), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_HORSE_ARMOR, 6, 12, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(new ItemStack(Items.SADDLE), 6, 1, 12, 30, 0.2F), new CaecanusTrade.DyedArmorForEmeraldsTrade(Items.LEATHER_HELMET, 5, 12, 30)})));
        p_221237_0_.put(VillagerProfession.MASON, gatAsIntMap(ImmutableMap.of(1, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.CLAY_BALL, 10, 16, 2), new CaecanusTrade.ItemsForEmeraldsTrade(Items.BRICK, 1, 10, 16, 1)}, 2, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Blocks.STONE, 20, 16, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CHISELED_STONE_BRICKS, 1, 4, 16, 5)}, 3, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Blocks.GRANITE, 16, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Blocks.ANDESITE, 16, 16, 20), new CaecanusTrade.EmeraldForItemsTrade(Blocks.DIORITE, 16, 16, 20), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.POLISHED_DIORITE, 1, 4, 16, 10), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.POLISHED_GRANITE, 1, 4, 16, 10)}, 4, new CaecanusTrade.ITrade[]{new CaecanusTrade.EmeraldForItemsTrade(Items.QUARTZ, 12, 12, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.ORANGE_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.WHITE_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLUE_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GRAY_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLACK_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.RED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PINK_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.MAGENTA_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIME_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GREEN_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CYAN_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PURPLE_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.YELLOW_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BROWN_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15)}, 5, new CaecanusTrade.ITrade[]{new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.QUARTZ_PILLAR, 1, 1, 12, 30), new CaecanusTrade.ItemsForEmeraldsTrade(Blocks.QUARTZ_BLOCK, 1, 1, 12, 30)})));
    });
    private static Int2ObjectMap<CaecanusTrade.ITrade[]> gatAsIntMap(ImmutableMap<Integer, CaecanusTrade.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    static class DyedArmorForEmeraldsTrade implements CaecanusTrade.ITrade {
        private final Item tradeItem;
        private final int price;
        private final int maxUses;
        private final int xpValue;

        public DyedArmorForEmeraldsTrade(Item itemIn, int priceIn) {
            this(itemIn, priceIn, maxUseIn, 1);
        }

        public DyedArmorForEmeraldsTrade(Item tradeItemIn, int priceIn, int maxUsesIn, int xpValueIn) {
            this.tradeItem = tradeItemIn;
            this.price = priceIn;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack itemstack = new ItemStack(SmenerelCoinItem.block, this.price);
            ItemStack itemstack1 = new ItemStack(this.tradeItem);
            if (this.tradeItem instanceof DyeableArmorItem) {
                List<DyeItem> list = Lists.newArrayList();
                list.add(getRandomDyeItem(rand));
                if (rand.nextFloat() > 0.7F) {
                    list.add(getRandomDyeItem(rand));
                }

                if (rand.nextFloat() > 0.8F) {
                    list.add(getRandomDyeItem(rand));
                }

                itemstack1 = IDyeableArmorItem.dyeItem(itemstack1, list);
            }

            return new MerchantOffer(itemstack, itemstack1, maxUseIn, this.xpValue, 0.2F);
        }

        private static DyeItem getRandomDyeItem(Random p_221232_0_) {
            return DyeItem.getItem(DyeColor.byId(p_221232_0_.nextInt(16)));
        }
    }

    static class EmeraldForItemsTrade implements CaecanusTrade.ITrade {
        private final Item tradeItem;
        private final int count;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(IItemProvider tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
            this.tradeItem = tradeItemIn.asItem();
            this.count = countIn;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
            return new MerchantOffer(itemstack, new ItemStack(SmenerelCoinItem.block), maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }

    static class EmeraldForMapTrade implements CaecanusTrade.ITrade {
        private final int count;
        private final Structure<?> structureName;
        private final MapDecoration.Type mapDecorationType;
        private final int maxUses;
        private final int xpValue;

        public EmeraldForMapTrade(int count, Structure<?> structureName, MapDecoration.Type mapDecorationType, int maxUses, int xpValue) {
            this.count = count;
            this.structureName = structureName;
            this.mapDecorationType = mapDecorationType;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random rand) {
            if (!(trader.world instanceof ServerWorld)) {
                return null;
            } else {
                ServerWorld serverworld = (ServerWorld)trader.world;
                BlockPos blockpos = serverworld.func_241117_a_(this.structureName, trader.getPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = FilledMapItem.setupNewMap(serverworld, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    FilledMapItem.func_226642_a_(serverworld, itemstack);
                    MapData.addTargetDecoration(itemstack, blockpos, "+", this.mapDecorationType);
                    itemstack.setDisplayName(new TranslationTextComponent("filled_map." + this.structureName.getStructureName().toLowerCase(Locale.ROOT)));
                    return new MerchantOffer(new ItemStack(SmenerelCoinItem.block, this.count), new ItemStack(Items.COMPASS), itemstack, maxUseIn, this.xpValue, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }

    static class EmeraldForVillageTypeItemTrade implements CaecanusTrade.ITrade {
        private final Map<VillagerType, Item> villagerTypeItems;
        private final int count;
        private final int maxUses;
        private final int xpValue;

        public EmeraldForVillageTypeItemTrade(int count, int maxUsesIn, int xpValueIn, Map<VillagerType, Item> villagerTypeItemsIn) {
            Registry.VILLAGER_TYPE.stream().filter((villagerType) -> {
                return !villagerTypeItemsIn.containsKey(villagerType);
            }).findAny().ifPresent((villagerType) -> {

            });
            this.villagerTypeItems = villagerTypeItemsIn;
            this.count = count;
            this.maxUses = maxUsesIn;
            this.xpValue = xpValueIn;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random rand) {
            if (trader instanceof IVillagerDataHolder) {
                ItemStack itemstack = new ItemStack(this.villagerTypeItems.get(((IVillagerDataHolder)trader).getVillagerData().getType()), this.count);
                return new MerchantOffer(itemstack, new ItemStack(SmenerelCoinItem.block), maxUseIn, this.xpValue, 0.05F);
            } else {
                return null;
            }
        }
    }

    static class EnchantedBookForEmeraldsTrade implements CaecanusTrade.ITrade {
        private final int xpValue;

        public EnchantedBookForEmeraldsTrade(int xpValueIn) {
            this.xpValue = xpValueIn;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::canVillagerTrade).collect(Collectors.toList());
            Enchantment enchantment = list.get(rand.nextInt(list.size()));
            int i = MathHelper.nextInt(rand, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = EnchantedBookItem.getEnchantedItemStack(new EnchantmentData(enchantment, i));
            int j = 2 + rand.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.isTreasureEnchantment()) {
                j *= 2;
            }

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(SmenerelCoinItem.block, j), new ItemStack(Items.BOOK), itemstack, maxUseIn, this.xpValue, 0.2F);
        }
    }

    static class EnchantedItemForEmeraldsTrade implements CaecanusTrade.ITrade {
        private final ItemStack sellingStack;
        private final int emeraldCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public EnchantedItemForEmeraldsTrade(Item p_i50535_1_, int emeraldCount, int maxUses, int xpValue) {
            this(p_i50535_1_, emeraldCount, maxUseIn, xpValue, 0.05F);
        }

        public EnchantedItemForEmeraldsTrade(Item sellItem, int emeraldCount, int maxUses, int xpValue, float priceMultiplier) {
            this.sellingStack = new ItemStack(sellItem);
            this.emeraldCount = emeraldCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            int i = 5 + rand.nextInt(15);
            ItemStack itemstack = EnchantmentHelper.addRandomEnchantment(rand, new ItemStack(this.sellingStack.getItem()), i, false);
            int j = Math.min(this.emeraldCount + i, 64);
            ItemStack itemstack1 = new ItemStack(SmenerelCoinItem.block, j);
            return new MerchantOffer(itemstack1, itemstack, maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }

    public interface ITrade {
        @Nullable
        MerchantOffer getOffer(Entity trader, Random rand);
    }

    static class ItemWithPotionForEmeraldsAndItemsTrade implements CaecanusTrade.ITrade {
        /** An ItemStack that can have potion effects written to it. */
        private final ItemStack potionStack;
        private final int potionCount;
        private final int emeraldCount;
        private final int maxUses;
        private final int xpValue;
        private final Item buyingItem;
        private final int buyingItemCount;
        private final float priceMultiplier;

        public ItemWithPotionForEmeraldsAndItemsTrade(Item buyingItem, int buyingItemCount, Item p_i50526_3_, int p_i50526_4_, int emeralds, int maxUses, int xpValue) {
            this.potionStack = new ItemStack(p_i50526_3_);
            this.emeraldCount = emeralds;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.buyingItem = buyingItem;
            this.buyingItemCount = buyingItemCount;
            this.potionCount = p_i50526_4_;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack itemstack = new ItemStack(SmenerelCoinItem.block, this.emeraldCount);
            List<Potion> list = Registry.POTION.stream().filter((potion) -> {
                return !potion.getEffects().isEmpty() && PotionBrewing.isBrewablePotion(potion);
            }).collect(Collectors.toList());
            Potion potion = list.get(rand.nextInt(list.size()));
            ItemStack itemstack1 = PotionUtils.addPotionToItemStack(new ItemStack(this.potionStack.getItem(), this.potionCount), potion);
            return new MerchantOffer(itemstack, new ItemStack(this.buyingItem, this.buyingItemCount), itemstack1, maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }

    static class ItemsForEmeraldsAndItemsTrade implements CaecanusTrade.ITrade {
        private final ItemStack buyingItem;
        private final int buyingItemCount;
        private final int emeraldCount;
        private final ItemStack sellingItem;
        private final int sellingItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public ItemsForEmeraldsAndItemsTrade(IItemProvider buyingItem, int buyingItemCount, Item sellingItem, int sellingItemCount, int maxUses, int xpValue) {
            this(buyingItem, buyingItemCount, 1, sellingItem, sellingItemCount, maxUseIn, xpValue);
        }

        public ItemsForEmeraldsAndItemsTrade(IItemProvider buyingItem, int buyingItemCount, int emeraldCount, Item sellingItem, int sellingItemCount, int maxUses, int xpValue) {
            this.buyingItem = new ItemStack(buyingItem);
            this.buyingItemCount = buyingItemCount;
            this.emeraldCount = emeraldCount;
            this.sellingItem = new ItemStack(sellingItem);
            this.sellingItemCount = sellingItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(SmenerelCoinItem.block, this.emeraldCount), new ItemStack(this.buyingItem.getItem(), this.buyingItemCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }

    static class ItemsForEmeraldsTrade implements CaecanusTrade.ITrade {
        private final ItemStack sellingItem;
        private final int emeraldCount;
        private final int sellingItemCount;
        private final int maxUses;
        private final int xpValue;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), emeraldCount, sellingItemCount, maxUseIn, xpValue);
        }

        public ItemsForEmeraldsTrade(Item sellingItem, int emeraldCount, int sellingItemCount, int xpValue) {
            this(new ItemStack(sellingItem), emeraldCount, sellingItemCount, maxUseIn, xpValue);
        }

        public ItemsForEmeraldsTrade(Item sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(new ItemStack(sellingItem), emeraldCount, sellingItemCount, maxUseIn, xpValue);
        }

        public ItemsForEmeraldsTrade(ItemStack sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue) {
            this(sellingItem, emeraldCount, sellingItemCount, maxUseIn, xpValue, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack sellingItem, int emeraldCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
            this.sellingItem = sellingItem;
            this.emeraldCount = emeraldCount;
            this.sellingItemCount = sellingItemCount;
            this.maxUses = maxUses;
            this.xpValue = xpValue;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity trader, Random rand) {
            return new MerchantOffer(new ItemStack(SmenerelCoinItem.block, this.emeraldCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }

    static class SuspiciousStewForEmeraldTrade implements CaecanusTrade.ITrade {
        final Effect effect;
        final int duration;
        final int xpValue;
        private final float priceMultiplier;

        public SuspiciousStewForEmeraldTrade(Effect effectIn, int durationIn, int xpValue) {
            this.effect = effectIn;
            this.duration = durationIn;
            this.xpValue = xpValue;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random rand) {
            ItemStack itemstack = new ItemStack(Items.SUSPICIOUS_STEW, 1);
            SuspiciousStewItem.addEffect(itemstack, this.effect, this.duration);
            return new MerchantOffer(new ItemStack(SmenerelCoinItem.block, 1), itemstack, maxUseIn, this.xpValue, this.priceMultiplier);
        }
    }
}