
package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.util.*;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.entity.renderer.SummonedSoulRenderer;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.Random;

@OriginofspiritsModElements.ModElement.Tag
public class SummonedSoulEntity extends OriginofspiritsModElements.ModElement {
	public static EntityType entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1f))
					.build("summoned_soul").setRegistryName("summoned_soul");

	public SummonedSoulEntity(OriginofspiritsModElements instance) {
		super(instance, 867);
		FMLJavaModLoadingContext.get().getModEventBus().register(new SummonedSoulRenderer.ModelRegisterHandler());
		FMLJavaModLoadingContext.get().getModEventBus().register(new EntityAttributesRegisterHandler());
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> entity);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	private static class EntityAttributesRegisterHandler {
		@SubscribeEvent
		public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
			AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
			ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4);
			ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 30);
			ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
			ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
			ammma = ammma.createMutableAttribute(Attributes.FLYING_SPEED, 0.4);
			event.put(entity, ammma.create());
		}
	}

	public static class CustomEntity extends TameableEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}
		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1.3D, 10.0F, 2.0F, false));
			this.goalSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
			this.goalSelector.addGoal(1, new OwnerHurtTargetGoal(this));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.5D, true));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vector3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vector3d(dir_x, dir_y, dir_z);
				}
			});
		}

		private PlayerEntity summoner;
		private boolean hasLimitedLife = true;
		private int lifeTime = 1000;
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

		public LivingEntity getSummoner() {
			return this.summoner;
		}

		public void setSummoner(PlayerEntity mobEntity) {
			this.summoner = mobEntity;
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:wisp_idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:wisp_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:wisp_death"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			if (source.isExplosion())
				return false;
			if (source == DamageSource.WITHER)
				return false;
			if (source.getDamageType().equals("witherSkull"))
				return false;
			if (source == DamageSource.DROWN) { return false; }
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ActionResultType func_230254_b_(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
			Item item = itemstack.getItem();
			if (itemstack.getItem() instanceof SpawnEggItem) {
				retval = super.func_230254_b_(sourceentity, hand);
			} else if (this.world.isRemote()) {
				retval = (this.isTamed() && this.isOwner(sourceentity) || this.isBreedingItem(itemstack))
						? ActionResultType.func_233537_a_(this.world.isRemote())
						: ActionResultType.PASS;
			} else {
				if (this.isTamed()) {
					if (this.isOwner(sourceentity)) {
						if (item.isFood() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal((float) item.getFood().getHealing());
							retval = ActionResultType.func_233537_a_(this.world.isRemote());
						} else if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
							this.consumeItemFromStack(sourceentity, itemstack);
							this.heal(4);
							retval = ActionResultType.func_233537_a_(this.world.isRemote());
						} else {
							retval = super.func_230254_b_(sourceentity, hand);
						}
					}
				} else if (this.isBreedingItem(itemstack)) {
					this.consumeItemFromStack(sourceentity, itemstack);
					if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
						this.setTamedBy(sourceentity);
						this.world.setEntityState(this, (byte) 7);
					} else {
						this.world.setEntityState(this, (byte) 6);
					}
					this.enablePersistence();
					retval = ActionResultType.func_233537_a_(this.world.isRemote());
				} else {
					retval = super.func_230254_b_(sourceentity, hand);
					if (retval == ActionResultType.SUCCESS || retval == ActionResultType.CONSUME)
						this.enablePersistence();
				}
			}
			return retval;
		}

		@Override
		public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageable) {
			CustomEntity retval = (CustomEntity) entity.create(serverWorld);
			retval.onInitialSpawn(serverWorld, serverWorld.getDifficultyForLocation(new BlockPos(retval.getPosition())), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			return false;
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		int dashtimer = 0;

		public int getDashtimer() {
			return dashtimer;
		}
		public void setDashtimer(int timer) {
			this.dashtimer = timer;
		}
		public void livingTick() {
			super.livingTick();
			// Dash
			if (this.getAttackTarget() != null) {
				this.setDashtimer(this.getDashtimer() + 1);
				double speed = 0.5;
				double Yaw = this.rotationYaw;
				double Pitch = this.rotationPitch;
				if (this.getDashtimer() >= 30) {
					this.setMotion((speed * Math.cos((Yaw + 90) * Math.PI / 180)), (this.getMotion().getY()),
							(speed * Math.sin((Yaw + 90) * Math.PI / 180)));
					this.setDashtimer(0);
				}
			}
			// Limited Life
			if (this.hasLimitedLife) {
				this.lifeTime--;
				if(this.lifeTime <= 0) {
					this.onKillCommand();
				}
			}
			this.setNoGravity(true);
		}
		public void tick() {
			super.tick();
			if (this.getSummoner() != null) {
				this.setTamed(true);
				this.setTamedBy((PlayerEntity) this.getSummoner());
				if (this.getSummoner().getShouldBeDead()) {
					this.onKillCommand();
				}
			} else {
				this.onKillCommand();
			}
			if (this.getAttackTarget() == this.getSummoner()) {
				this.setAttackTarget(null);
			}
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
		public void onDeath(DamageSource cause) {
			System.out.println("Spririt Died" + cause);
		}
	}
}
