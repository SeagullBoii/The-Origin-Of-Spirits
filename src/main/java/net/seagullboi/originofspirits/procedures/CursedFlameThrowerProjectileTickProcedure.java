package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSParticles;

import java.util.Map;

public class CursedFlameThrowerProjectileTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure CursedFlameThrowerProjectileTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure CursedFlameThrowerProjectileTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure CursedFlameThrowerProjectileTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure CursedFlameThrowerProjectileTick!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure CursedFlameThrowerProjectileTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		imediatesourceentity.getPersistentData().putDouble("deathTime", (imediatesourceentity.getPersistentData().getDouble("deathTime") + 1));
		if (imediatesourceentity.getPersistentData().getDouble("deathTime") >= 10) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
		}
		for (int index0 = 0; index0 < (int) (Math.log(Math.random() * 10)); index0++) {
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), (x + 0.1), y, z, 0, 0, 0);
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), (x - 0.1), y, z, 0, 0, 0);
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, y, (z - 0.1), 0, 0, 0);
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, y, (z + 0.1), 0, 0, 0);
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, (y + 0.1), z, 0, 0, 0);
			world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, (y - 0.1), z, 0, 0, 0);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, y, z, (int) 9, 0.5, 0.5, 0.5, 0.05);
			}
		}
	}
}
