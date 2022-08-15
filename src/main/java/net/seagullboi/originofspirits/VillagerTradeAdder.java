
package net.seagullboi.originofspirits;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.seagullboi.originofspirits.item.BeansItem;
import net.seagullboi.originofspirits.item.YeastItem;

import java.util.List;

@OriginofspiritsModElements.ModElement.Tag
public class VillagerTradeAdder extends OriginofspiritsModElements.ModElement {
	public VillagerTradeAdder(OriginofspiritsModElements instance) {
		super(instance, 9);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}

	@SubscribeEvent
	public void addNewTrade(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.FARMER) {
			List<VillagerTrades.ITrade> trades3 = event.getTrades().get(3);
			List<VillagerTrades.ITrade> trades4 = event.getTrades().get(4);
			trades3.add(new BasicTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(BeansItem.block, 2), 16, 20, 2));
			trades3.add(new BasicTrade(new ItemStack(BeansItem.block, 3), new ItemStack(Items.EMERALD, 1), 16, 20, 2));
			trades3.add(new BasicTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(YeastItem.block, 3), 16, 20, 2));
		}
	}
}
