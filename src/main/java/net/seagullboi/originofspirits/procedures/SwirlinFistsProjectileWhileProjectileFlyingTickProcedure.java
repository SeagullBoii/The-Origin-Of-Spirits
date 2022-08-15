package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class SwirlinFistsProjectileWhileProjectileFlyingTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure SwirlinFistsProjectileWhileProjectileFlyingTick!");
			return;
		}
		if (dependencies.get("imediatesourceentity") == null) {
			if (!dependencies.containsKey("imediatesourceentity"))
				OriginOfSpirits.LOGGER
						.warn("Failed to load dependency imediatesourceentity for procedure SwirlinFistsProjectileWhileProjectileFlyingTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity imediatesourceentity = (Entity) dependencies.get("imediatesourceentity");
		imediatesourceentity.getPersistentData().putDouble("deathTimer", (entity.getPersistentData().getDouble("deathTimer") + 1));
		if (imediatesourceentity.getPersistentData().getDouble("deathTimer") == 15) {
			if (!imediatesourceentity.world.isRemote())
				imediatesourceentity.remove();
		}
	}
}
