package net.seagullboi.originofspirits;

import net.seagullboi.originofspirits.item.TempestBowItem;
import net.seagullboi.originofspirits.registry.TOOSItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "originofspirits", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemAnimClient {
	@SubscribeEvent
	public static void setModelProperties(FMLClientSetupEvent event) {
		//Tempest Bow
		ItemModelsProperties.registerProperty((TempestBowItem.block), new ResourceLocation("pull"), (itemstack, world, entity) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getActiveItemStack() != itemstack ? 0.0F : (float) (itemstack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
			}
		});
		ItemModelsProperties.registerProperty((TempestBowItem.block), new ResourceLocation("pulling"), (itemstack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemstack ? 1.0F : 0.0F);

		//EyeCannon
		ItemModelsProperties.registerProperty((TOOSItems.EYE_CANNON.get()), new ResourceLocation("charged"), (itemstack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemstack && ((itemstack.getUseDuration() - entity.getItemInUseCount()) >= 30)  ? 1.0F : 0.0F);
		ItemModelsProperties.registerProperty((TOOSItems.EYE_CANNON.get()), new ResourceLocation("weapon_mod"), (itemstack, world, entity) -> itemstack.getOrCreateTag().getInt("weapon_mod"));
		//Redstone Handgun
		ItemModelsProperties.registerProperty((TOOSItems.REDSTONE_HANDGUN.get()), new ResourceLocation("overheat"), (itemstack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == itemstack && (itemstack.getOrCreateTag().getBoolean("overheat"))  ? 1.0F : 0.0F);
		ItemModelsProperties.registerProperty((TOOSItems.REDSTONE_HANDGUN.get()), new ResourceLocation("weapon_mod"), (itemstack, world, entity) -> itemstack.getOrCreateTag().getInt("weapon_mod"));
		//Shotgun
		ItemModelsProperties.registerProperty((TOOSItems.SHOTGUN.get()), new ResourceLocation("weapon_mod"), (itemstack, world, entity) -> itemstack.getOrCreateTag().getInt("weapon_mod"));
	}
}
