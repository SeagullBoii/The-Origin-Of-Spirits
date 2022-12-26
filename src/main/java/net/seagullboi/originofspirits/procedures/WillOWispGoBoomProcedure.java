package net.seagullboi.originofspirits.procedures;

import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CursedFireProjectilesEntity;
import net.seagullboi.originofspirits.entity.WillOWispEntity;
import net.seagullboi.originofspirits.particle.CursedLavaParticleParticle;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.registry.TOOSParticles;

import java.util.HashMap;
import java.util.Map;

public class WillOWispGoBoomProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityAttacked(LivingAttackEvent event) {
			if (event != null && event.getEntity() != null) {
				Entity entity = event.getEntity();
				Entity sourceentity = event.getSource().getTrueSource();
				Entity imediatesourceentity = event.getSource().getImmediateSource();
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				double amount = event.getAmount();
				World world = entity.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("amount", amount);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("sourceentity", sourceentity);
				dependencies.put("imediatesourceentity", imediatesourceentity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure WillOWispGoBoom!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure WillOWispGoBoom!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure WillOWispGoBoom!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure WillOWispGoBoom!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency entity for procedure WillOWispGoBoom!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency sourceentity for procedure WillOWispGoBoom!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (sourceentity instanceof WillOWispEntity.CustomEntity) {
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:wisp_explode")),
						SoundCategory.VOICE, (float) 1, (float) 1);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:wisp_explode")),
						SoundCategory.VOICE, (float) 1, (float) 1, false);
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
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x, (y + 0.6), z, (int) 10, 0.2, 0.2, 0.2, 0);
					}
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(CursedLavaParticleParticle.particle, x, (y + 0.6), z, (int) 10, 0.2, 0.2, 0.2, 0);
					}
					for (int index0 = 0; index0 < (int) (3); index0++) {
						if (world instanceof ServerWorld) {
							Entity entityToSpawn = new CursedFireProjectilesEntity.CustomEntity(CursedFireProjectilesEntity.entity, (World) world);
							((MobEntity) entityToSpawn).setAttackTarget(((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null));
							entityToSpawn.setLocationAndAngles(x, (y + 0.6), z, (float) 0, (float) 0);
							entityToSpawn.setRenderYawOffset((float) 0);
							entityToSpawn.setRotationYawHead((float) 0);
							entityToSpawn.setMotion((Math.random() - 0.2), 0, (Math.random() - 0.2));
							((MobEntity) entityToSpawn).setAttackTarget(((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null));
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world,
										world.getDifficultyForLocation(entityToSpawn.getPosition()), SpawnReason.MOB_SUMMONED,
										null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
					if (!sourceentity.world.isRemote())
						sourceentity.remove();
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) 6);
			entity.setFire((int) 5);
			if (Math.random() < 0.5) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(CursedPotionEffect.potion, (int) 60, (int) 0, (false), (true)));
			}
		}
	}
}
