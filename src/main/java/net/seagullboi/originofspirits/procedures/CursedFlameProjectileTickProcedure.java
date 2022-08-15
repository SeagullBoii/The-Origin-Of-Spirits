package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.particle.CursedFlameParticleParticle;

import java.util.Map;

public class CursedFlameProjectileTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure CursedFlameProjectileTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure CursedFlameProjectileTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure CursedFlameProjectileTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure CursedFlameProjectileTick!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure CursedFlameProjectileTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		imediatesourceentity.getPersistentData().putDouble("deathTime", (imediatesourceentity.getPersistentData().getDouble("deathTime") + 1));
		if (imediatesourceentity.getPersistentData().getDouble("deathTime") >= 8) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
		}
		world.addParticle(CursedFlameParticleParticle.particle, (x + 0.1), y, z, 0, 0, 0);
		world.addParticle(CursedFlameParticleParticle.particle, (x - 0.1), y, z, 0, 0, 0);
		world.addParticle(CursedFlameParticleParticle.particle, x, y, (z - 0.1), 0, 0, 0);
		world.addParticle(CursedFlameParticleParticle.particle, x, y, (z + 0.1), 0, 0, 0);
		world.addParticle(CursedFlameParticleParticle.particle, x, (y + 0.1), z, 0, 0, 0);
		world.addParticle(CursedFlameParticleParticle.particle, x, (y - 0.1), z, 0, 0, 0);
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(CursedFlameParticleParticle.particle, x, y, z, (int) 10, 0.5, 0.5, 0.5, 0.05);
		}
	}
}
