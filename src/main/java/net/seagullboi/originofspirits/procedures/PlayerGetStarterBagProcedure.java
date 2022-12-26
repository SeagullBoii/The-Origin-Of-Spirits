package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.HashMap;
import java.util.Map;

public class PlayerGetStarterBagProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
		//	executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure PlayerGetStarterBag!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure PlayerGetStarterBag!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (OriginofspiritsModVariables.WorldVariables.get(world).BagConfig) {
			if (!(entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new OriginofspiritsModVariables.PlayerVariables())).gotBag) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(TOOSItems.STARTER_BAG.get());
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
				{
					boolean _setval = (true);
					entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.gotBag = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
