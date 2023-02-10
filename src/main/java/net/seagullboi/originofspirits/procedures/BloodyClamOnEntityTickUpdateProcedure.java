package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.AgeableEntity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.ElectricEelEntity;

import java.util.Map;

public class BloodyClamOnEntityTickUpdateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure BloodyClamOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isOnGround()) {
			if (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof ElectricEelEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof CreatureEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof AgeableEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof AmbientEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof AnimalEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof PlayerEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof MobEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof FlyingEntity
					|| ((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof WaterMobEntity) {
				entity.setMotion((entity.getMotion().getX()), 0.5, (entity.getMotion().getZ()));
			}
		}
	}
}
