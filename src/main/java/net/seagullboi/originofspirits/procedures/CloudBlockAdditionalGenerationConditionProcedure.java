package net.seagullboi.originofspirits.procedures;

import java.util.Map;

public class CloudBlockAdditionalGenerationConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (Math.random() < 0.4) {
			return true;
		}
		return false;
	}
}
