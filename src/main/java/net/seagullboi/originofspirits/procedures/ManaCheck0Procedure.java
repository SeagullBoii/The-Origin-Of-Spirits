package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

import java.util.Map;

public class ManaCheck0Procedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure ManaCheck0!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana >= 0) {
			return true;
		}
		return false;
	}
}
