package net.seagullboi.originofspirits.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.potion.DrunkPotionEffect;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.Collection;

public class DrunkOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure DrunkOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("drunkTimer", (entity.getPersistentData().getDouble("drunkTimer") + 1));
		if (entity.getPersistentData().getDouble("drunkTimer") >= 20) {
			if (Math.random() < 0.25) {
				entity.setMotion((0.2 + new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == DrunkPotionEffect.potion)
									return effect.getAmplifier();
							}
						}
						return 0;
					}
				}.check(entity) / 10), (entity.getMotion().getY()), (entity.getMotion().getZ()));
			} else if (Math.random() < 0.5) {
				entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (0.2 + new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == DrunkPotionEffect.potion)
									return effect.getAmplifier();
							}
						}
						return 0;
					}
				}.check(entity) / 10));
			} else if (Math.random() < 0.75) {
				entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), ((0.2 + new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == DrunkPotionEffect.potion)
									return effect.getAmplifier();
							}
						}
						return 0;
					}
				}.check(entity) / 10) * (-1)));
			} else if (Math.random() < 1) {
				entity.setMotion(((0.2 + new Object() {
					int check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == DrunkPotionEffect.potion)
									return effect.getAmplifier();
							}
						}
						return 0;
					}
				}.check(entity) / 10) * (-1)), (entity.getMotion().getY()), (entity.getMotion().getZ()));
			}
			entity.getPersistentData().putDouble("drunkTimer", 0);
		}
	}
}
