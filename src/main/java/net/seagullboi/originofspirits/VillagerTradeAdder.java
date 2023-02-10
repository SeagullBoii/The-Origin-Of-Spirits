
package net.seagullboi.originofspirits;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.item.YeastItem;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.List;

@Mod.EventBusSubscriber
public class VillagerTradeAdder{

	@SubscribeEvent
	public void addNewTrade(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.FARMER) {
			List<VillagerTrades.ITrade> trades3 = event.getTrades().get(3);
			List<VillagerTrades.ITrade> trades4 = event.getTrades().get(4);
			trades3.add(new BasicTrade(new ItemStack(Items.EMERALD, 5), new ItemStack(TOOSItems.BEANS.get(), 2), 16, 20, 2));
			trades3.add(new BasicTrade(new ItemStack(TOOSItems.BEANS.get(), 3), new ItemStack(Items.EMERALD, 1), 16, 20, 2));
			trades3.add(new BasicTrade(new ItemStack(Items.EMERALD, 7), new ItemStack(YeastItem.block, 3), 16, 20, 2));
		}
	}
}
