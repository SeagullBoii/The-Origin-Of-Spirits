package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.particle.GelParticleParticle;

import java.util.Map;

public class GelGunWhileProjectileFlyingTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure GelGunWhileProjectileFlyingTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure GelGunWhileProjectileFlyingTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure GelGunWhileProjectileFlyingTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure GelGunWhileProjectileFlyingTick!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency imediatesourceentity for procedure GelGunWhileProjectileFlyingTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		imediatesourceentity.getPersistentData().putDouble("deathTime", (imediatesourceentity.getPersistentData().getDouble("deathTime") + 1));
		if (imediatesourceentity.getPersistentData().getDouble("deathTime") >= 4) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
		}
		world.addParticle(GelParticleParticle.particle, (x + 0.1), y, z, 0, 0, 0);
		world.addParticle(GelParticleParticle.particle, (x - 0.1), y, z, 0, 0, 0);
		world.addParticle(GelParticleParticle.particle, x, y, (z - 0.1), 0, 0, 0);
		world.addParticle(GelParticleParticle.particle, x, y, (z + 0.1), 0, 0, 0);
		world.addParticle(GelParticleParticle.particle, x, (y + 0.1), z, 0, 0, 0);
		world.addParticle(GelParticleParticle.particle, x, (y - 0.1), z, 0, 0, 0);
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(GelParticleParticle.particle, x, y, z, (int) 5, 0.1, 0.1, 0.1, 0);
		}
	}
}
