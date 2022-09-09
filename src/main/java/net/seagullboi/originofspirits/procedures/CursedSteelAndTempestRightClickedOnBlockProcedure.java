package net.seagullboi.originofspirits.procedures;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.item.TheSkyRealmsItem;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CursedSteelAndTempestRightClickedOnBlockProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
			PlayerEntity entity = event.getPlayer();
			if (event.getHand() != entity.getActiveHand()) {
				return;
			}
			double i = event.getPos().getX();
			double j = event.getPos().getY();
			double k = event.getPos().getZ();
			IWorld world = event.getWorld();
			BlockState state = world.getBlockState(event.getPos());
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("direction", event.getFace());
			dependencies.put("blockstate", state);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency direction for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure CursedSteelAndTempestRightClickedOnBlock!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Direction direction = (Direction) dependencies.get("direction");
		Entity entity = (Entity) dependencies.get("entity");
		double generator_distance = 0;
		if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == TheSkyRealmsItem.block
				|| ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == TheSkyRealmsItem.block) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					if (direction == Direction.UP) {
						if (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))
								&& world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolidSide(world,
										new BlockPos((int) x, (int) y, (int) z), Direction.UP)) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					} else if (direction == Direction.DOWN) {
						if (world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))
								&& world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolidSide(world,
										new BlockPos((int) x, (int) y, (int) z), Direction.DOWN)) {
							world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					} else if (direction == Direction.NORTH) {
						if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1)))
								&& world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1))).isSolidSide(world,
										new BlockPos((int) x, (int) (y - 1), (int) (z - 1)), Direction.UP)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					} else if (direction == Direction.SOUTH) {
						if (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1)))
								&& world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1))).isSolidSide(world,
										new BlockPos((int) x, (int) (y - 1), (int) (z + 1)), Direction.UP)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					} else if (direction == Direction.EAST) {
						if (world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))
								&& world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z)).isSolidSide(world,
										new BlockPos((int) (x + 1), (int) (y - 1), (int) z), Direction.UP)) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					} else if (direction == Direction.WEST) {
						if (world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z))
								&& world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z)).isSolidSide(world,
										new BlockPos((int) (x - 1), (int) (y - 1), (int) z), Direction.UP)) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z),  TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
							if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == TheSkyRealmsItem.block) {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemMainhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							} else {
								if (entity instanceof LivingEntity) {
									((LivingEntity) entity).swing(Hand.OFF_HAND, true);
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
									{
										ItemStack _ist = ((entity instanceof LivingEntity)
												? ((LivingEntity) entity).getHeldItemOffhand()
												: ItemStack.EMPTY);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("item.flintandsteel.use")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 2);
		}
	}
}
