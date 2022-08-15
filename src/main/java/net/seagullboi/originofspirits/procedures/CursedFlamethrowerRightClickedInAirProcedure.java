package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.CursedFlamethrowerProjectileItem;

import java.util.Random;
import java.util.Map;

public class CursedFlamethrowerRightClickedInAirProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure CursedFlamethrowerRightClickedInAir!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			Entity _ent = entity;
			if (!_ent.world.isRemote()) {
				CursedFlamethrowerProjectileItem.shoot(_ent.world, (LivingEntity) entity, new Random(), (float) 1, (float) 7, (int) 0);
			}
		}
	}
}
