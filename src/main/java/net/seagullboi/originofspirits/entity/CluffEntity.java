package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;


public class CluffEntity extends CreatureEntity implements IAngerable, IRangedAttackMob, IFlyingAnimal {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(CluffEntity.class, DataSerializers.BOOLEAN);
    private int attackTimer;
    private BlockPos boundOrigin;

    public CluffEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new CluffEntity.MoveHelperController(this);
        this.experienceValue = 3;
    }



    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new CluffEntity.MoveRandomGoal());
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, (float) 0.5));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 60, 10) {
            @Override
            public boolean shouldContinueExecuting() {
                return this.shouldExecute();
            }
        });
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 30.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D).createMutableAttribute(Attributes.FOLLOW_RANGE, 128).createMutableAttribute(Attributes.FLYING_SPEED,1);
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int time) {
    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return null;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {
    }

    @Override
    public void func_230258_H__() {
    }


    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        CluffCloudProjectile.shoot(this, target, 0);
        this.setAttackTimer(10);
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
    public void livingTick() {
        super.livingTick();

        if (this.attackTimer > 0) {
            this.setAttacking(true);
            --this.attackTimer;
        } else {
            this.setAttacking(false);
        }

        int fallTimer = 0;
        fallTimer++;
        if (fallTimer >= 60) {
            if (Math.random() <= 0.4) {
                this.setMotion(this.getMotion().getX(), -0.1, this.getMotion().getZ());
            }
            fallTimer = 0;
        }
    }

    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public void setAttackTimer(int timer) {
        this.attackTimer = timer;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isAttacking() {
        return this.dataManager.get(ATTACKING);
    }
    public void setAttacking(boolean attacking) {
        this.dataManager.set(ATTACKING, attacking);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(ATTACKING, false);
    }

    class MoveHelperController extends MovementController {
        public MoveHelperController(CluffEntity cluff) {
            super(cluff);
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.posX - CluffEntity.this.getPosX(), this.posY - CluffEntity.this.getPosY(), this.posZ - CluffEntity.this.getPosZ());
                double d0 = vector3d.length();
                if (d0 < CluffEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = MovementController.Action.WAIT;
                    CluffEntity.this.setMotion(CluffEntity.this.getMotion().scale(0.5D));
                } else {
                    CluffEntity.this.setMotion(CluffEntity.this.getMotion().add(vector3d.scale(this.speed * 0.05D / d0)));
                    if (CluffEntity.this.getAttackTarget() == null) {
                        Vector3d vector3d1 = CluffEntity.this.getMotion();
                        CluffEntity.this.rotationYaw = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        CluffEntity.this.renderYawOffset = CluffEntity.this.rotationYaw;
                    } else {
                        double d2 = CluffEntity.this.getAttackTarget().getPosX() - CluffEntity.this.getPosX();
                        double d1 = CluffEntity.this.getAttackTarget().getPosZ() - CluffEntity.this.getPosZ();
                        CluffEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        CluffEntity.this.renderYawOffset = CluffEntity.this.rotationYaw;
                    }
                }

            }
        }
    }

    class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !CluffEntity.this.getMoveHelper().isUpdating() && CluffEntity.this.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            BlockPos blockpos = CluffEntity.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = CluffEntity.this.getPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.add(CluffEntity.this.rand.nextInt(15) - 7, CluffEntity.this.rand.nextInt(11) - 5, CluffEntity.this.rand.nextInt(15) - 7);
                if (CluffEntity.this.world.isAirBlock(blockpos1)) {
                    CluffEntity.this.moveController.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);
                    if (CluffEntity.this.getAttackTarget() == null) {
                        CluffEntity.this.getLookController().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }

}
