package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.IWorld;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSParticles;

import java.util.Map;

public class SimpleDashProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure SimpleDash!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure SimpleDash!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure SimpleDash!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure SimpleDash!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure SimpleDash!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double Pitch = 0;
		double speed = 0;
		double Yaw = 0;
		world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, (y + 0.5), z, 0, 0, 0);
		if (!(((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) == null)) {
			entity.getPersistentData().putDouble("dashtimer", (entity.getPersistentData().getDouble("dashtimer") + 1));
		}
		if (entity.getPersistentData().getDouble("dashtimer") >= 20) {
			speed = 0.5;
			Yaw = (entity.rotationYaw);
			Pitch = (entity.rotationPitch);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
			}
			entity.setMotion((speed * Math.cos((Yaw + 90) * Math.PI / 180)), (entity.getMotion().getY()),
					(speed * Math.sin((Yaw + 90) * Math.PI / 180)));
			entity.getPersistentData().putDouble("dashtimer", 0);
		}
	}
}
