
package net.seagullboi.originofspirits.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import net.seagullboi.originofspirits.block.CursedLavaBlock;

@Mod.EventBusSubscriber
public class CursedLavaFuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == CursedLavaBlock.bucket.asItem())
			event.setBurnTime(40000);
	}
}
