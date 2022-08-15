package net.seagullboi.originofspirits.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class VenomOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure VenomOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putDouble("venomtimer", (entity.getPersistentData().getDouble("venomtimer") + 1));
		if (entity.getPersistentData().getDouble("venomspeeduptimer") <= 3) {
			if (entity.getPersistentData().getDouble("venomtimer") >= 30) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 1);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		} else if (entity.getPersistentData().getDouble("venomspeeduptimer") <= 7) {
			if (entity.getPersistentData().getDouble("venomtimer") >= 25) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 1);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		} else if (entity.getPersistentData().getDouble("venomspeeduptimer") <= 11) {
			if (entity.getPersistentData().getDouble("venomtimer") >= 20) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 1);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		} else if (entity.getPersistentData().getDouble("venomspeeduptimer") <= 15) {
			if (entity.getPersistentData().getDouble("venomtimer") >= 15) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 1);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		} else if (entity.getPersistentData().getDouble("venomspeeduptimer") <= 19) {
			if (entity.getPersistentData().getDouble("venomtimer") >= 10) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 1);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		} else {
			if (entity.getPersistentData().getDouble("venomtimer") >= 10) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("venom").setDamageBypassesArmor(), (float) 2);
				}
				entity.getPersistentData().putDouble("venomspeeduptimer", (entity.getPersistentData().getDouble("venomspeeduptimer") + 1));
				entity.getPersistentData().putDouble("venomtimer", 0);
			}
		}
	}
}
