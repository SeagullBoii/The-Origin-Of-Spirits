package net.seagullboi.originofspirits.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class CursedCloudBlockCollisionProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure CursedCloudBlockCollision!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!entity.isSneaking()) {
			entity.setMotion((entity.getMotion().getX()), 0, (entity.getMotion().getZ()));
		} else {
			entity.setMotion((entity.getMotion().getX()), 0.5, (entity.getMotion().getZ()));
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(CursedPotionEffect.potion, (int) 20, (int) 0, (false), (true)));
	}
}
