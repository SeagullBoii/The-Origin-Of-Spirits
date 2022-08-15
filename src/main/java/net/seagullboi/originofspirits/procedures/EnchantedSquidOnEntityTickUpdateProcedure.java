package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class EnchantedSquidOnEntityTickUpdateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure EnchantedSquidOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double speed = 0;
		double Yaw = 0;
		entity.getPersistentData().putDouble("IdleTimer", (entity.getPersistentData().getDouble("IdleTimer") + 1));
		if (entity.getPersistentData().getDouble("IdleTimer") == 100) {
			if (Math.random() < 0.7) {
				speed = 0.2;
				entity.rotationYaw = (float) ((Math.random() * 100 + entity.rotationYaw));
				entity.setRenderYawOffset(entity.rotationYaw);
				entity.prevRotationYaw = entity.rotationYaw;
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
					((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
					((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
				}
				entity.rotationPitch = (float) ((Math.random() * 100 + entity.rotationPitch));
				Yaw = (entity.rotationYaw);
				for (int index0 = 0; index0 < (int) (100); index0++) {
					entity.setMotion((speed * Math.cos(Yaw + 90 + Math.PI / 180)), (entity.getMotion().getY()),
							(speed * Math.sin(Yaw + 90 + Math.PI / 180)));
				}
			}
			entity.getPersistentData().putDouble("IdleTimer", 0);
		}
	}
}
