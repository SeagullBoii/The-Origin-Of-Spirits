package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

import java.util.Map;

public class BaubleGUIThisGUIIsClosedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure BaubleGUIThisGUIIsClosed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
		if (!(((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6)
						.getItem() == ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6Backup).getItem())) {
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6);
				_setstack.setCount((int) ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6Count));
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
	}
}
