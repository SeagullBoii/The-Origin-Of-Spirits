package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class ImmovablePearlSwimConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure ImmovablePearlSwimCondition!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isInWater()) {
			return true;
		}
		return false;
	}
}
