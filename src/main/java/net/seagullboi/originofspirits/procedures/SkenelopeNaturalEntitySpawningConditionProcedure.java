package net.seagullboi.originofspirits.procedures;

import java.util.Map;

public class SkenelopeNaturalEntitySpawningConditionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (Math.random() < 0.1) {
			if (Math.random() < 0.1) {
				return true;
			}
		}
		return false;
	}
}
