package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.item.MagicalBraceletItem;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;
import java.util.HashMap;

public class ManaRegenerationProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure ManaRegeneration!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).ManaCooldown <= 0) {
			if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1).getItem() == MagicalBraceletItem.block
					^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2).getItem() == MagicalBraceletItem.block
					^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3).getItem() == MagicalBraceletItem.block
					^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4).getItem() == MagicalBraceletItem.block
					^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5).getItem() == MagicalBraceletItem.block
					^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6).getItem() == MagicalBraceletItem.block)) {
				if ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana < (entity
								.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MaxMana) {
					entity.getPersistentData().putDouble("manaregen", (entity.getPersistentData().getDouble("manaregen") + 1));
					entity.getPersistentData().putBoolean("manahealing", (true));
					if (entity.getPersistentData().getDouble("manaregen") >= 60) {
						entity.getPersistentData().putDouble("manaregen", 0);
						{
							double _setval = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new OriginofspiritsModVariables.PlayerVariables())).Mana + 1);
							entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Mana = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					}
				}
			}
		} else {
			{
				double _setval = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).ManaCooldown - 1);
				entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ManaCooldown = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
