
package net.seagullboi.originofspirits.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import net.seagullboi.originofspirits.block.MoltenTempestCrystalBlock;

@Mod.EventBusSubscriber
public class MoltenCrystalBucketFuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == MoltenTempestCrystalBlock.bucket.asItem())
			event.setBurnTime(20000);
	}
}
