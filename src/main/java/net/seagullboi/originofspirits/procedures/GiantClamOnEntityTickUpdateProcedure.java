package net.seagullboi.originofspirits.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.potion.VenomPotionEffect;
import net.seagullboi.originofspirits.potion.DensePotionEffect;
import net.seagullboi.originofspirits.entity.ImmovablePearlEyeEntity;
import net.seagullboi.originofspirits.entity.GiantClamEntity;
import net.seagullboi.originofspirits.entity.ClamFangEntity;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

public class GiantClamOnEntityTickUpdateProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
			Entity entity = event.getEntityLiving();
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

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure GiantClamOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure GiantClamOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure GiantClamOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure GiantClamOnEntityTickUpdate!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure GiantClamOnEntityTickUpdate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double animtimer = 0;
		boolean pearlSpawned = false;
		if (entity instanceof GiantClamEntity.CustomEntity) {
			entity.getPersistentData().putDouble("animtimer", (entity.getPersistentData().getDouble("animtimer") + 1));
			if (entity.getPersistentData().getDouble("animtimer") >= 80) {
				entity.getPersistentData().putDouble("animtimer", 0);
			}
			if (entity.getPersistentData().getDouble("animtimer") == 40) {
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
								"replaceitem entity @s armor.head originofspirits:giant_clam_item{CustomModelData:2}");
					}
				}
				{
					Entity _ent = entity;
					if (!_ent.world.isRemote && _ent.world.getServer() != null) {
						_ent.world.getServer().getCommandManager().handleCommand(
								_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4), "data merge entity @s {Tags:[\"state:2\"]}");
					}
				}
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
						{
							Entity _ent = entity;
							if (!_ent.world.isRemote && _ent.world.getServer() != null) {
								_ent.world.getServer().getCommandManager().handleCommand(
										_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
										"replaceitem entity @s armor.head originofspirits:giant_clam_item{CustomModelData:1}");
							}
						}
						{
							Entity _ent = entity;
							if (!_ent.world.isRemote && _ent.world.getServer() != null) {
								_ent.world.getServer().getCommandManager().handleCommand(
										_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
										"data merge entity @s {Tags:[\"state:1\"]}");
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 9);
			}
			if (entity.getPersistentData().getDouble("animtimer") == 49) {
				{
					List<Entity> _entfound = world
							.getEntitiesWithinAABB(Entity.class,
									new AxisAlignedBB(x - (10 / 2d), y - (10 / 2d), z - (10 / 2d), x + (10 / 2d), y + (10 / 2d), z + (10 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator instanceof GiantClamEntity.CustomEntity
								|| entityiterator instanceof ImmovablePearlEyeEntity.CustomEntity)) {
							if (entityiterator instanceof LivingEntity) {
								((LivingEntity) entityiterator).attackEntityFrom(new DamageSource("clammed").setDamageBypassesArmor(), (float) 6);
							}
							if (OriginofspiritsModVariables.WorldVariables.get(world).Spite == true) {
								if (entityiterator instanceof LivingEntity)
									((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(DensePotionEffect.potion, (int) 120, (int) 0));
							} else if (Math.random() < 0.1) {
								if (entityiterator instanceof LivingEntity)
									((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(DensePotionEffect.potion, (int) 120, (int) 0));
							}
						}
					}
				}
				if (world instanceof ServerWorld) {
					((World) world).getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
							"summon area_effect_cloud ~ ~0.5 ~ {Particle:\"instant_effect\",Radius:0f,RadiusPerTick:2f,Duration:5}");
				}
				if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 50) {
					if (entity.getPersistentData().getBoolean("pSpawn") == false) {
						if (world instanceof ServerWorld) {
							Entity entityToSpawn = new ImmovablePearlEyeEntity.CustomEntity(ImmovablePearlEyeEntity.entity, (World) world);
							entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
							entityToSpawn.setRenderYawOffset((float) 0);
							entityToSpawn.setRotationYawHead((float) 0);
							entityToSpawn.setMotion(0, 0, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
										world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
										(ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
							((ImmovablePearlEyeEntity.CustomEntity) entityToSpawn).setOwner((LivingEntity) entity);
						}
						if (OriginofspiritsModVariables.WorldVariables.get(world).Spite == true) {
							if (entity instanceof LivingEntity)
								((LivingEntity) entity)
										.setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + 15));
						}
						entity.getPersistentData().putBoolean("pSpawn", (true));
					}
				}
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(DensePotionEffect.potion, (int) 1, (int) 0, (false), (true)));
			}
			if (OriginofspiritsModVariables.WorldVariables.get(world).Spite == true) {
				if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) <= 25) {
					if (entity.getPersistentData().getBoolean("eyeGet") == false) {
						if (entity instanceof LivingEntity)
							((LivingEntity) entity)
									.setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + 20));
						{
							Entity _ent = entity;
							if (!_ent.world.isRemote && _ent.world.getServer() != null) {
								_ent.world.getServer().getCommandManager().handleCommand(
										_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
										"replaceitem entity @s weapon.offhand originofspirits:giant_clam_item{CustomModelData:3}");
							}
						}
						{
							Entity _ent = entity;
							if (!_ent.world.isRemote && _ent.world.getServer() != null) {
								_ent.world.getServer().getCommandManager().handleCommand(
										_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
										"replaceitem entity @s weapon.mainhand originofspirits:giant_clam_item{CustomModelData:3}");
							}
						}
						entity.getPersistentData().putBoolean("eyeGet", (true));
					}
				}
			}
			if (entity.getPersistentData().getBoolean("eyeGet") == true) {
				entity.getPersistentData().putDouble("fangTimer", (entity.getPersistentData().getDouble("fangTimer") + 1));
				if (entity.getPersistentData().getDouble("fangTimer") >= 90) {
					{
						List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (15 / 2d), y - (15 / 2d), z - (15 / 2d), x + (15 / 2d), y + (15 / 2d), z + (15 / 2d)), null)
								.stream().sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
									}
								}.compareDistOf(x, y, z)).collect(Collectors.toList());
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator instanceof GiantClamEntity.CustomEntity
									|| entityiterator instanceof ImmovablePearlEyeEntity.CustomEntity)) {
								if (entityiterator instanceof LivingEntity || entityiterator instanceof PlayerEntity) {
									if (world instanceof ServerWorld) {
										Entity entityToSpawn = new ClamFangEntity.CustomEntity(ClamFangEntity.entity, (World) world);
										entityToSpawn.setLocationAndAngles((entityiterator.getPosX()), (entityiterator.getPosY()),
												(entityiterator.getPosZ()), (float) 0, (float) 0);
										entityToSpawn.setRenderYawOffset((float) 0);
										entityToSpawn.setRotationYawHead((float) 0);
										entityToSpawn.setMotion(0, 0, 0);
										if (entityToSpawn instanceof MobEntity)
											((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
													world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
													(ILivingEntityData) null, (CompoundNBT) null);
										world.addEntity(entityToSpawn);
									}
								}
							}
						}
					}
					entity.getPersistentData().putDouble("fangTimer", 0);
				}
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(VenomPotionEffect.potion);
			}
		}
	}
}
