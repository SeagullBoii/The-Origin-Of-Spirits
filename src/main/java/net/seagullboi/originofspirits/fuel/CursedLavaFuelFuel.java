
package net.seagullboi.originofspirits.fuel;

import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.seagullboi.originofspirits.registry.TOOSItems;

@Mod.EventBusSubscriber
public class CursedLavaFuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == TOOSItems.CURSED_LAVA_BUCKET.get())
			event.setBurnTime(40000);
	}
}
