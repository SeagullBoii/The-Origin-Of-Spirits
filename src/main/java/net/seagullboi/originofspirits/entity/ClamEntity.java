
package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Random;

public class ClamEntity extends MobEntity {
	public static final DataParameter<Boolean> PEARL = EntityDataManager.createKey(ClamEntity.class, DataSerializers.BOOLEAN);

	public ClamEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 100)
				.createMutableAttribute(ForgeMod.ENTITY_GRAVITY.get(), 1.25);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public net.minecraft.util.SoundEvent getHurtSound(DamageSource source) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public net.minecraft.util.SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.FALL)
			return false;
		if (source == DamageSource.DROWN)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Random random = new Random();
		if (random.nextInt(2) == 1) {
			this.setPearl(true);
		}

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
	
	public void setPearl(boolean bool) {
		this.dataManager.set(PEARL, bool);
	}

	public boolean getPearl() {
		return this.dataManager.get(PEARL);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(PEARL, false);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Pearl", this.getPearl());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setPearl(compound.getBoolean("Pearl"));
	}
}
