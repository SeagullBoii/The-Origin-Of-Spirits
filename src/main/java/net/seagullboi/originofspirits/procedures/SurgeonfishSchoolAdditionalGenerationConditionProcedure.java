package net.seagullboi.originofspirits.procedures;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class SurgeonfishSchoolAdditionalGenerationConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure SurgeonfishSchoolAdditionalGenerationCondition!");
			return false;
		}
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		if (y <= 40) {
			return true;
		}
		return false;
	}
}
