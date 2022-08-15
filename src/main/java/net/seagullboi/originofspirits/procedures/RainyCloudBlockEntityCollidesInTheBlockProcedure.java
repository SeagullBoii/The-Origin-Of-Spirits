package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class RainyCloudBlockEntityCollidesInTheBlockProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure RainyCloudBlockEntityCollidesInTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!entity.isSneaking()) {
			entity.setMotion((entity.getMotion().getX()), 0, (entity.getMotion().getZ()));
		} else {
			entity.setMotion((entity.getMotion().getX()), 0.5, (entity.getMotion().getZ()));
		}
	}
}
