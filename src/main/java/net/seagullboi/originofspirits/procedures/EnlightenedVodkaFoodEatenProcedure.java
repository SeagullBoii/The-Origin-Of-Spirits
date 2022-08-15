package net.seagullboi.originofspirits.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.potion.EnlightenedPotionEffect;
import net.seagullboi.originofspirits.potion.DrunkPotionEffect;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class EnlightenedVodkaFoodEatenProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure EnlightenedVodkaFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(DrunkPotionEffect.potion, (int) 80, (int) 0, (false), (true)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 80, (int) 0, (false), (true)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(EnlightenedPotionEffect.potion, (int) 400, (int) 0, (false), (true)));
	}
}
