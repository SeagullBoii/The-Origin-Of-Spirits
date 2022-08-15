package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.entity.ElectricEelEntity;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.HashMap;

public class ElectricEelVariantsProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntitySpawned(EntityJoinWorldEvent event) {
			Entity entity = event.getEntity();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			World world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure ElectricEelVariants!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double Variant = 0;
		if (entity instanceof ElectricEelEntity.CustomEntity) {
			if (((ElectricEelEntity.CustomEntity) entity).getVariant() == 0) {
				Variant = Math.ceil(4 * Math.random());
				((ElectricEelEntity.CustomEntity) entity).setVariant((int) Variant);
			}
		}
		if (Variant > 4) {
			Variant = 0;
		}
	}
}
