package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class CursedSparkATKCondProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure CursedSparkATKCond!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return !(entity instanceof MonsterEntity);
	}
}
