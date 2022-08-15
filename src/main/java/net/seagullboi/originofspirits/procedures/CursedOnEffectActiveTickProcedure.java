package net.seagullboi.originofspirits.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.CursedSteelArmorItem;

import java.util.Map;

public class CursedOnEffectActiveTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure CursedOnEffectActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!EntityTypeTags.getCollection().getTagByID(new ResourceLocation("forge:cursed_mobs")).contains(entity.getType())) {
			entity.getPersistentData().putDouble("curseTime", (entity.getPersistentData().getDouble("curseTime") + 1));
			if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET) : ItemStack.EMPTY)
					.getItem() == CursedSteelArmorItem.boots
					&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
							.getItem() == CursedSteelArmorItem.legs
					&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY)
							.getItem() == CursedSteelArmorItem.body
					&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
							.getItem() == CursedSteelArmorItem.helmet) {
				if (entity.getPersistentData().getDouble("curseTime") == 8) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).attackEntityFrom(new DamageSource("cursed").setDamageBypassesArmor(), (float) 1);
					}
					((LivingEntity) entity).hurtResistantTime = 8;
					entity.getPersistentData().putDouble("curseTime", 0);
				}
			} else if (entity.getPersistentData().getDouble("curseTime") == 5) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).attackEntityFrom(new DamageSource("cursed").setDamageBypassesArmor(), (float) 1);
				}
				((LivingEntity) entity).hurtResistantTime = 5;
				entity.getPersistentData().putDouble("curseTime", 0);
			}
		}
	}
}
