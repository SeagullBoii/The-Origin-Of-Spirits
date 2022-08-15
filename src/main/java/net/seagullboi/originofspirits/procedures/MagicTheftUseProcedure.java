package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.enchantment.MagicTheftEnchantment;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

import java.util.Map;
import java.util.HashMap;

public class MagicTheftUseProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityDeath(LivingDeathEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure MagicTheftUse!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency sourceentity for procedure MagicTheftUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation("originofspirits:magic_entities")).contains(entity.getType())) {
			if (EnchantmentHelper.getEnchantmentLevel(MagicTheftEnchantment.enchantment,
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) == 1) {
				if ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana < (sourceentity
								.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MaxMana) {
					{
						double _setval = ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana + 1);
						sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Mana = _setval;
							capability.syncPlayerVariables(sourceentity);
						});
					}
				}
			} else if (EnchantmentHelper.getEnchantmentLevel(MagicTheftEnchantment.enchantment,
					((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)) >= 2) {
				if ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana < (sourceentity
								.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MaxMana - 1) {
					{
						double _setval = ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana + 2);
						sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Mana = _setval;
							capability.syncPlayerVariables(sourceentity);
						});
					}
				} else if ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana < (sourceentity
								.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MaxMana) {
					{
						double _setval = ((sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana + 1);
						sourceentity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.Mana = _setval;
							capability.syncPlayerVariables(sourceentity);
						});
					}
				}
			}
		}
	}
}
