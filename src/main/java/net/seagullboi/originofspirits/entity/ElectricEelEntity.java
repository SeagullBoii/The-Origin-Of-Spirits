
package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.particle.ElectricSparksParticle;
import net.seagullboi.originofspirits.particle.ElectricityParticleParticle;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ElectricEelEntity extends WaterMonsterEntity {
	public static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(ElectricEelEntity.class, DataSerializers.VARINT);
	public static final DataParameter<Boolean> EXPLODING = EntityDataManager.createKey(ElectricEelEntity.class, DataSerializers.BOOLEAN);
	int explosionTimer = 20;

	public ElectricEelEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
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

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MOVEMENT_SPEED, 1)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3)
				.createMutableAttribute(Attributes.MAX_HEALTH, 20)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1)
				.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 1)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32D);
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public net.minecraft.util.SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_TROPICAL_FISH_AMBIENT;
	}

	@Override
	public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.ENTITY_TROPICAL_FISH_HURT;
	}

	@Override
	public net.minecraft.util.SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_TROPICAL_FISH_DEATH;
	}

	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (inWater) {
			this.setExploding(true);
		}
		return super.attackEntityAsMob(entityIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.DROWN)
			return false;
		if (source == DamageSource.LIGHTNING_BOLT)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Random random = new Random();

		if (random.nextInt(10) == 1) {
			this.setVariant(5);
		} else {
			this.setVariant(random.nextInt(4) + 1);
		}

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.getExploding()) {
			this.explosionTimer--;
			this.setNoAI(true);
			if (world instanceof ServerWorld)
			((ServerWorld) world).spawnParticle(ElectricSparksParticle.particle, getPosX(), getPosY(), getPosZ(), 5, 0.1, 0.1, 0.1, 0.5);
		}

		if (this.explosionTimer == 19) {
			world.playSound(null, getPosX(), getPosY(), getPosZ(), TOOSSoundEvents.ELECTRICAL_SHOCK.get(), SoundCategory.HOSTILE, 1, 1);
		}

		if (explosionTimer == 0) {
			world.playSound(null, getPosX(), getPosY(), getPosZ(), TOOSSoundEvents.ELECTRICAL_SHOCK_EXPLOSION.get(), SoundCategory.HOSTILE, 1, 1);
			this.explode();
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ElectricityParticleParticle.particle, getPosX(), getPosY(), getPosZ(), 20, 0.1, 0.1, 0.1, 0.5);
				this.remove();
			}
		}
	}

	@Override
	public void tick() {
		super.tick();
		Random random = new Random();
		if (this.getVariant() > 5 || this.getVariant() <= 0) {
			this.setVariant(random.nextInt(4) + 1);
		}
	}

	public void explode() {
		AxisAlignedBB axis = new AxisAlignedBB(this.getPosX() - 4, this.getPosY() - 4, this.getPosZ() - 4, this.getPosX() + 4, this.getPosY() + 4, this.getPosZ() + 4);

		List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, axis, null).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double cx, double cy, double cz) {
				return Comparator.comparing(comparedEntity -> comparedEntity.getDistanceSq(cx, cy, cz));
			}
		}.compareDistOf(this.getPosX(), this.getPosY(), this.getPosZ())).collect(Collectors.toList());

		for (Entity entityIterator : entityList) {
			if (entityIterator instanceof LivingEntity)
				if (entityIterator != this && entityIterator.getRidingEntity() != this && this.getRidingEntity() != entityIterator)
					entityIterator.attackEntityFrom(DamageSource.causeMobDamage(this), 10);
			if (entityIterator instanceof PlayerEntity) {
				((PlayerEntity) entityIterator).disableShield(true);
			}
		}
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean isNotColliding(IWorldReader worldreader) {
		return worldreader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	public void setVariant(int varIn) {
		this.dataManager.set(VARIANT, varIn);
	}

	public int getVariant() {
		return this.dataManager.get(VARIANT);
	}

	public void setExploding(boolean exploding) {
		this.dataManager.set(EXPLODING, exploding);
	}

	public boolean getExploding() {
		return this.dataManager.get(EXPLODING);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(VARIANT, 0);
		this.dataManager.register(EXPLODING, false);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("Variant", this.getVariant());
		compound.putBoolean("Exploding", this.getExploding());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setVariant(compound.getInt("Variant"));
		this.setExploding(compound.getBoolean("Exploding"));
	}
}
