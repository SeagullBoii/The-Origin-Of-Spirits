
package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.entity.renderer.HexayoshRenderer;
import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsEntitiesItemGroup;
import net.seagullboi.originofspirits.procedures.HexayoshSpawningConditionProcedure;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@OriginofspiritsModElements.ModElement.Tag
public class HexayoshEntity extends OriginofspiritsModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire()
			.size(0.8f, 1.4f)).build("hexayosh").setRegistryName("hexayosh");

	public HexayoshEntity(OriginofspiritsModElements instance) {
		super(instance, 757);
		FMLJavaModLoadingContext.get().getModEventBus().register(new HexayoshRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -4943985, -4283489, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab))
				.setRegistryName("hexayosh_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = new ResourceLocation("originofspirits:deceptive_islands").equals(event.getName());
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity, 40, 1, 1));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return

							HexayoshSpawningConditionProcedure.executeProcedure(Stream
									.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
											new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
									.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				});

	}


	private static class EntityAttributesRegisterHandler {
		@SubscribeEvent
		public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
			AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
			ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25);
			ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 40);
			ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
			ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
			event.put(entity, ammma.create());
		}
	}

	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}
		boolean isCharging;
		@OnlyIn(Dist.CLIENT)
		public boolean getIsCharging() {
			return this.isCharging;
		}
		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, HobayoshEntity.class, true, true));

		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:hexayosh_ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cow.step")), 0.15f, 1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:hexayosh_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:hexayosh_death"));
		}

		public net.minecraft.util.SoundEvent getChargeSound() {
			return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:hexayosh_charge"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}
		int chargeTimer;
		@Override
		public void livingTick() {
			super.livingTick();
			if (this.getAttackTarget() != null) {
				this.chargeTimer++;
			}
				if (this.chargeTimer >= 100) {
					double speed = 2;
					double Yaw = (this.rotationYaw);
					double Pitch = (this.rotationPitch);
					this.setPositionAndUpdate(this.getPosX(), this.getPosY() + 0.5, this.getPosZ());
					this.setMotion((speed * Math.cos((Yaw + 90) * Math.PI / 180)), (this.getMotion().getY()),
							(speed * Math.sin((Yaw + 90) * Math.PI / 180)));
					this.chargeTimer = 0;

				}
				if (this.chargeTimer == 95) {
					this.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 4, 20, (false), (false)));
					this.isCharging = true;
					world.playSound(null, new BlockPos((int) getPosX(), (int) getPosY(), (int) getPosZ()),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:hexayosh_charge")),
							SoundCategory.VOICE, (float) 1, (float) 1);
				} else { this.isCharging = false; }

		}
	}
}
