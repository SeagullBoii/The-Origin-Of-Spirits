package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class LazoculusEntity extends TOOSMonsterEntity implements IRangedAttackMob {

    public LazoculusEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.8, 20));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 60, 10) {
            @Override
            public boolean shouldContinueExecuting() {
                return this.shouldExecute();
            }
        });
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 40.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D).createMutableAttribute(Attributes.FOLLOW_RANGE, 64);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        CursedLaserProjectile.shoot(this, target, 9);
        this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.HOSTILE, 1, 2, true);
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    public void livingTick() {
        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

        super.livingTick();

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.FALL)
            return false;

        return super.attackEntityFrom(source, amount);
    }
}
