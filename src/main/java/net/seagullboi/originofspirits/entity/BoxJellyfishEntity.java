
package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.Conditions;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.client.renderer.BoxJellyfishRenderer;
import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsEntitiesItemGroup;
import net.seagullboi.originofspirits.potion.VenomPotionEffect;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.network.IPacket;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

@OriginofspiritsModElements.ModElement.Tag
public class BoxJellyfishEntity extends OriginofspiritsModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.WATER_CREATURE)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
			.size(0.6f, 0.5f)).build("box_jellyfish").setRegistryName("box_jellyfish");

	public BoxJellyfishEntity(OriginofspiritsModElements instance) {
		super(instance, 244);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BoxJellyfishRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -4396058, -2234893, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab))
				.setRegistryName("box_jellyfish_spawn_egg"));
	}

	@SubscribeEvent
	public void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = false;
			if (new ResourceLocation("originofspirits:abyss").equals(event.getName()))
				biomeCriteria = true;
			if (new ResourceLocation("originofspirits:colorful_abyss").equals(event.getName()))
				biomeCriteria = true;
			if (new ResourceLocation("originofspirits:shallow_abyss").equals(event.getName()))
				biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE).add(new MobSpawnInfo.Spawners(entity, 50, 4, 6));
	}


	@Override
	public void init(FMLCommonSetupEvent event) {
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				Conditions::AbyssFishCheck);
	}

	private static class EntityAttributesRegisterHandler {
		@SubscribeEvent
		public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
			AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
			ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1);
			ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 20);
			ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
			ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2);
			ammma = ammma.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 1);
			event.put(entity, ammma.create());
		}
	}

	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		private float animPos = 0F;
		private boolean isAnimIncreasing = true;
		private boolean isAnimDecreasing = false;

		public boolean getAnimIncrease() {
			return this.isAnimIncreasing;
		}

		public void setAnimIncrease(boolean increase) {
			this.isAnimIncreasing = increase;
		}

		public void setAnimDecrease(boolean decrease) {
			this.isAnimDecreasing = decrease;
		}

		public boolean getAnimDecrease() {
			return this.isAnimDecreasing;
		}

		public float getAnimPos() {
			return this.animPos;
		}

		public void setAnimPos(float anim) {
			this.animPos = anim;
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.setPathPriority(PathNodeType.WATER, 0);
			this.moveController = new MovementController(this) {
				@Override
				public void tick() {
					if (CustomEntity.this.isInWater())
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(0, 0.005, 0));
					if (this.action == MovementController.Action.MOVE_TO && !CustomEntity.this.getNavigator().noPath()) {
						double dx = this.posX - CustomEntity.this.getPosX();
						double dy = this.posY - CustomEntity.this.getPosY();
						double dz = this.posZ - CustomEntity.this.getPosZ();
						float f = (float) (MathHelper.atan2(dz, dx) * (double) (180 / Math.PI)) - 90;
						float f1 = (float) (this.speed * CustomEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
						CustomEntity.this.rotationYaw = this.limitAngle(CustomEntity.this.rotationYaw, f, 10);
						CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						CustomEntity.this.rotationYawHead = CustomEntity.this.rotationYaw;
						if (CustomEntity.this.isInWater()) {
							CustomEntity.this.setAIMoveSpeed((float) CustomEntity.this.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
							float f2 = -(float) (MathHelper.atan2(dy, MathHelper.sqrt(dx * dx + dz * dz)) * (180F / Math.PI));
							f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85, 85);
							CustomEntity.this.rotationPitch = this.limitAngle(CustomEntity.this.rotationPitch, f2, 5);
							float f3 = MathHelper.cos(CustomEntity.this.rotationPitch * (float) (Math.PI / 180.0));
							CustomEntity.this.setMoveForward(f3 * f1);
							CustomEntity.this.setMoveVertical((float) (f1 * dy));
						} else {
							CustomEntity.this.setAIMoveSpeed(f1 * 0.05F);
						}
					} else {
						CustomEntity.this.setAIMoveSpeed(0);
						CustomEntity.this.setMoveVertical(0);
						CustomEntity.this.setMoveForward(0);
					}
				}
			};
			this.navigator = new SwimmerPathNavigator(this, this.world);
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
			this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1, 40));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.WATER;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.slime.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.slime.death"));
		}

		public void tick() {
			super.tick();

			//Animation
			if(this.getAnimPos() >= 0.5F) {
				this.setAnimDecrease(true);
				this.setAnimIncrease(false);
			}

			if(this.getAnimPos() <= -0.25F) {
				this.setAnimIncrease(true);
				this.setAnimDecrease(false);
			}
			if(this.getAnimDecrease()) {
				this.setAnimPos(this.getAnimPos() - 0.03125F);
			}
			if(this.getAnimIncrease()) {
				this.setAnimPos(this.getAnimPos() + 0.03125F);
			}
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public boolean attackEntityAsMob(Entity entityIn) {
			boolean flag = super.attackEntityAsMob(entityIn);
			if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof LivingEntity) {
				float f = this.world.getDifficultyForLocation(this.getPosition()).getAdditionalDifficulty();
				((LivingEntity)entityIn).addPotionEffect(new EffectInstance(VenomPotionEffect.potion, 60 * (int)f));
			}

			return flag;
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader world) {
			return world.checkNoEntityCollision(this);
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}
	}
}
