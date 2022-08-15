package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.MeteoritePebbleItem;
import net.seagullboi.originofspirits.block.MeteoritePebblesBlock;

import java.util.Map;

public class MeteoritePebblePlaceProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure MeteoritePebblePlace!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure MeteoritePebblePlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure MeteoritePebblePlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure MeteoritePebblePlace!");
			return;
		}
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency direction for procedure MeteoritePebblePlace!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure MeteoritePebblePlace!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Direction direction = (Direction) dependencies.get("direction");
		Entity entity = (Entity) dependencies.get("entity");
		BlockState size = Blocks.AIR.getDefaultState();
		if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MeteoritePebblesBlock.block) {
			if (entity.isSneaking()) {
				if (direction == Direction.UP) {
					if (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				} else if (direction == Direction.DOWN) {
					if (world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))) {
						world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				} else if (direction == Direction.NORTH) {
					if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1)))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				} else if (direction == Direction.SOUTH) {
					if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1)))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				} else if (direction == Direction.EAST) {
					if (world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))) {
						world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				} else if (direction == Direction.WEST) {
					if (world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z))) {
						world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == MeteoritePebbleItem.block) {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
							}
						} else {
							if (entity instanceof LivingEntity) {
								((LivingEntity) entity).swing(Hand.OFF_HAND, true);
							}
						}
						if (!(new Object() {
							public boolean checkGamemode(Entity _ent) {
								if (_ent instanceof ServerPlayerEntity) {
									return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
								} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
									NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
											.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
									return _npi != null && _npi.getGameType() == GameType.CREATIVE;
								}
								return false;
							}
						}.checkGamemode(entity))) {
							if (entity instanceof PlayerEntity) {
								ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
								((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
										((PlayerEntity) entity).container.func_234641_j_());
							}
						}
					}
				}
			} else {
				if (!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)) {
					if (world instanceof ServerWorld) {
						((World) world).getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
								"execute unless block ~ ~ ~ originofspirits:meteorite_pebbles[size=2] run clear @p originofspirits:meteorite_pebble 1");
					}
				}
				if (world instanceof ServerWorld) {
					((World) world).getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
							"execute if block ~ ~ ~ originofspirits:meteorite_pebbles[size=1] run setblock ~ ~ ~ originofspirits:meteorite_pebbles[size=2]");
				}
				if (world instanceof ServerWorld) {
					((World) world).getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
							"execute if block ~ ~ ~ originofspirits:meteorite_pebbles[size=0] run setblock ~ ~ ~ originofspirits:meteorite_pebbles[size=1]");
				}
				if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == MeteoritePebbleItem.block) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
				} else {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.OFF_HAND, true);
					}
				}
			}
		} else {
			if (direction == Direction.UP) {
				if (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			} else if (direction == Direction.DOWN) {
				if (world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			} else if (direction == Direction.NORTH) {
				if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1)))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			} else if (direction == Direction.SOUTH) {
				if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1)))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			} else if (direction == Direction.EAST) {
				if (world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			} else if (direction == Direction.WEST) {
				if (world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z))) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), MeteoritePebblesBlock.block.getDefaultState(), 3);
					if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == MeteoritePebbleItem.block) {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
						}
					} else {
						if (entity instanceof LivingEntity) {
							((LivingEntity) entity).swing(Hand.OFF_HAND, true);
						}
					}
					if (!(new Object() {
						public boolean checkGamemode(Entity _ent) {
							if (_ent instanceof ServerPlayerEntity) {
								return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
							} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
								NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
										.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
								return _npi != null && _npi.getGameType() == GameType.CREATIVE;
							}
							return false;
						}
					}.checkGamemode(entity))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(MeteoritePebbleItem.block);
							((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
									((PlayerEntity) entity).container.func_234641_j_());
						}
					}
				}
			}
		}
	}
}
