
package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class JellyfishEntity extends WaterMonsterEntity {
	public static final DataParameter<Integer> COLOR = EntityDataManager.createKey(JellyfishEntity.class, DataSerializers.VARINT);
	private float animPos = 0F;
	private boolean isAnimIncreasing = true;
	private boolean isAnimDecreasing = false;
	float waterYaw = 0;
	int attackTimer = 0;

	public JellyfishEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

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

	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1, 40));
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 2)
				.createMutableAttribute(Attributes.MAX_HEALTH, 20)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1)
				.createMutableAttribute(ForgeMod.SWIM_SPEED.get(), 0.4)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 1);
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.slime.hurt"));
	}

	@Override
	public net.minecraft.util.SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.slime.death"));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.DROWN)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		int color = (int) Math.ceil(16 * Math.random());
		this.setColor(color);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}


	public void livingTick() {
		super.livingTick();

		if (this.isInWater()) {
			waterYaw = this.rotationYaw;
		} else {
			this.rotationYaw = waterYaw;
		}

		if (this.attackTimer > 0) {
			this.attackTimer--;
		}

		if (this.getColor() <= 0 || this.getColor() > 16) {
			int color = (int) Math.ceil(16 * Math.random());
			this.setColor(color);
		}
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		super.applyEntityCollision(entityIn);
		if (!(this instanceof BoxJellyfishEntity)) {
			if (this.attackTimer == 0) {
				if (entityIn instanceof LivingEntity && !(entityIn instanceof JellyfishEntity)) {
					if (!(entityIn instanceof PlayerEntity)) {
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 150, 0));
						entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
						this.attackTimer = 20;
					} else {
						if (!((PlayerEntity) entityIn).isCreative() || entityIn.isSpectator()) {
							((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 150, 0));
							entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
							this.attackTimer = 20;
						}
					}
				}
			}
		}
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
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean isNotColliding(IWorldReader worldReader) {
		return worldReader.checkNoEntityCollision(this, VoxelShapes.create(this.getBoundingBox()));
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	public void setColor(int varIn) {
		this.dataManager.set(COLOR, varIn);
	}

	public int getColor() {
		return this.dataManager.get(COLOR);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(COLOR, 0);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("Color", this.getColor());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setColor(compound.getInt("Color"));
	}

}

