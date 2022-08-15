package net.seagullboi.originofspirits.procedures;

import java.util.Map;

public class TempestRunnerSpawnCondtionProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (Math.random() < 0.1) {
			if (Math.random() < 0.3) {
				return true;
			}
		}
		return false;
	}
}
