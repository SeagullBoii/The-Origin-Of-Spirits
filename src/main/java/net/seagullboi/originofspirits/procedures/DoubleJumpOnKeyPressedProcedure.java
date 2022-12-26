package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.PuffyCloudItem;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

import java.util.Map;

public class DoubleJumpOnKeyPressedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure DoubleJumpOnKeyPressed!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure DoubleJumpOnKeyPressed!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure DoubleJumpOnKeyPressed!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure DoubleJumpOnKeyPressed!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure DoubleJumpOnKeyPressed!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot1).getItem() == PuffyCloudItem.block
				^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot2).getItem() == PuffyCloudItem.block
				^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot3).getItem() == PuffyCloudItem.block
				^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot4).getItem() == PuffyCloudItem.block
				^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot5).getItem() == PuffyCloudItem.block
				^ ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).MiscSlot6).getItem() == PuffyCloudItem.block) {
			if (!entity.isOnGround()) {
				if ((entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new OriginofspiritsModVariables.PlayerVariables())).DidDoubleJump == false) {
					{
						boolean _setval = (true);
						entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.DidDoubleJump = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					entity.setMotion((entity.getMotion().getX()), 0.5, (entity.getMotion().getZ()));
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, x, y, z, 10, 0.2, 0.2, 0.2, 0);
					}
					if (world instanceof World && !world.isRemote()) {
						world
								.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("entity.player.attack.sweep")),
										SoundCategory.PLAYERS, (float) 1, (float) 0);
					} else {
						((World) world).playSound(x, y, z,
								ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("entity.player.attack.sweep")),
								SoundCategory.PLAYERS, (float) 1, (float) 0, false);
					}
				}
			} else {
				{
					boolean _setval = (false);
					entity.getCapability(OriginofspiritsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.DidDoubleJump = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
