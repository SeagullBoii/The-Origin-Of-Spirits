package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CabadorEntity;

import java.util.Map;

public class UpwardMountMovementOnKeyPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies){
			if(dependencies.get("entity") == null) {
				if(!dependencies.containsKey("entity"))
					OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure UpwardMountMovementOnKeyPressed!");
				return;
			}
				Entity entity = (Entity) dependencies.get("entity");
		if (entity.getRidingEntity() instanceof CabadorEntity) {(entity.getRidingEntity()).getPersistentData().putDouble("verticalMovement", 1);}
	}
}
