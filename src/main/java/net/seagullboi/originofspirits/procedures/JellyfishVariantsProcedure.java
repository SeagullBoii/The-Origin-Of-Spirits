package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.entity.JellyfishEntity;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.HashMap;

public class JellyfishVariantsProcedure {
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
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure JellyfishVariants!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double Variant = 0;
		double Color = 0;
		if (entity instanceof JellyfishEntity.CustomEntity) {
			if (((JellyfishEntity.CustomEntity) entity).getColor() == 0) {
				Color = Math.ceil(16 * Math.random());
				((JellyfishEntity.CustomEntity) entity).setColor((int) Color);
			}
		}
		if (Color > 16) {
			Color = 0;
		}
	}
}
