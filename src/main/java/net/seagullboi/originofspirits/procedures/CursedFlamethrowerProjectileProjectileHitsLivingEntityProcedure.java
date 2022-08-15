package net.seagullboi.originofspirits.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class CursedFlamethrowerProjectileProjectileHitsLivingEntityProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER
						.warn("Failed to load dependency entity for procedure CursedFlamethrowerProjectileProjectileHitsLivingEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (Math.random() < 0.1) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(CursedPotionEffect.potion, (int) 30, (int) 0, (false), (true)));
		}
		entity.setFire((int) 5);
	}
}
