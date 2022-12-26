package net.seagullboi.originofspirits.procedures;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorld;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.potion.FairyBloodPotionEffect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class FairyBloodOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure FairyBloodOnEffectActiveTick!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure FairyBloodOnEffectActiveTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == CursedPotionEffect.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement advancement = ((ServerPlayerEntity) entity).server.getAdvancementManager()
						.getAdvancement(new ResourceLocation("originofspirits:player_cancels_curse_effect"));
				AdvancementProgress advancementProgress = ((ServerPlayerEntity) entity).getAdvancements().getProgress(advancement);
				if (!advancementProgress.isDone()) {
					Iterator _iterator = advancementProgress.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(advancement, _criterion);
					}
				}
			}
		}

		if (entity instanceof LivingEntity) {


			LivingEntity livingEntity = (LivingEntity) entity;
			if (livingEntity.isPotionActive(CursedPotionEffect.potion)) {
				livingEntity.removePotionEffect(CursedPotionEffect.potion);
			}
			if (livingEntity.isPotionActive(FairyBloodPotionEffect.potion)) {
				livingEntity.removePotionEffect(FairyBloodPotionEffect.potion);
			}

			if (entity instanceof ServerPlayerEntity) {
				Advancement advancement = ((ServerPlayerEntity) entity).server.getAdvancementManager().getAdvancement(new ResourceLocation("originofspirits:player_cancels_curse_effect"));
				AdvancementProgress advancementProgress = ((ServerPlayerEntity) entity).getAdvancements().getProgress(advancement);
				if (!advancementProgress.isDone()) {
					Iterator _iterator = advancementProgress.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(advancement, _criterion);
					}
				}
			}
		}
	}
}
