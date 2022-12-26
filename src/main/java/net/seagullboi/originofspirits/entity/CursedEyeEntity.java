package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.seagullboi.originofspirits.particle.CursedLavaParticleParticle;
import net.seagullboi.originofspirits.registry.TOOSParticles;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class CursedEyeEntity extends MonsterEntity {

    private BlockPos boundOrigin;
    public int attackTimer = 100;
    public boolean attacking = false;
    public double movementSpeed = 0.3D;
    public double flyingSpeed = 1;
    public double distance = 0;

    public CursedEyeEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new FlyingMovementController(this, 10, true);
        this.navigator = new FlyingPathNavigator(this, this.world);
        this.experienceValue = 3;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //  this.goalSelector.addGoal(0, new CursedEyeEntity.FlyingAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
        this.goalSelector.addGoal(2, new CursedEyeEntity.WanderGoal());
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D).createMutableAttribute(Attributes.FOLLOW_RANGE, 32).createMutableAttribute(Attributes.FLYING_SPEED,1).createMutableAttribute(Attributes.ATTACK_DAMAGE, 0);
    }

    @Override
    public void livingTick() {
        super.livingTick();

        double x = this.getPosX();
        double y = this.getPosY();
        double z = this.getPosZ();
        double xTrace = Math.sin(Math.toRadians(this.rotationYaw + 180)) / 2;
        double yTrace = Math.sin(Math.toRadians(0 - this.rotationPitch)) / 2;
        double zTrace = Math.cos(Math.toRadians(this.rotationYaw)) / 2;

        if (this.getAttackTarget() != null) {
            this.attackTimer++;
        }

        if (this.attackTimer > 80 || this.getAttackTarget() == null) {
            this.attackTimer = 0;
        }

        System.out.println(attackTimer);

        if (!world.getBlockState(new BlockPos(x + xTrace * distance, y + yTrace * distance + 0.25, z + zTrace * distance)).isSolid()) {
            distance++;
        } else {
            distance = 0;
        }

        if (distance > 15) {
            distance = 0;
        }

        if (this.getAttackTimer() > 40) {

            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                for (int i = 0; i < distance; i++)
                serverWorld.spawnParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x + xTrace * i, y + yTrace * i + 0.25, z + zTrace * i, 1, 0, 0, 0, 0.01);

            }

        }


        if (this.attackTimer >= 70) {
            attack(x + xTrace * distance, y + yTrace * distance + 0.25, z + zTrace * distance);
            world.addParticle(CursedLavaParticleParticle.particle, x + xTrace * distance, y + yTrace * distance + 0.25, z + zTrace * distance, 0, 0, 0);
            if (distance < 13) {
                world.addParticle(CursedLavaParticleParticle.particle, x + xTrace * (distance + 1), y + yTrace * (distance + 1) + 0.25, z + zTrace * (distance + 1), 0, 0, 0);
            } else if (distance < 12) {
                world.addParticle(CursedLavaParticleParticle.particle, x + xTrace * (distance + 2), y + yTrace * (distance + 2) + 0.25, z + zTrace * (distance + 2), 0, 0, 0);
            }
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                serverWorld.playSound(null, x, y, z, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE, 1, 1.7f);
                serverWorld.playSound(null, x, y, z, SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.HOSTILE, 0.5f, 1.7f);
            }
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

    private void announceAttack(double distance, double x, double y, double z, double xTrace, double yTrace, double zTrace) {
        world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x + xTrace * distance, y + yTrace * distance + 0.25, z + zTrace * distance, 0, 0, 0);
        if (distance < 13) {
            world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x + xTrace * (distance + 1), y + yTrace * (distance + 1) + 0.25, z + zTrace * (distance + 1), 0, 0, 0);
        } else if (distance < 12) {
            world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), x + xTrace * (distance + 2), y + yTrace * (distance + 2) + 0.25, z + zTrace * (distance + 2), 0, 0, 0);
        }
    }

    public void attack(double xTrace, double yTrace, double zTrace) {
        AxisAlignedBB axis = new AxisAlignedBB(xTrace - (2 / 2), yTrace - (2 / 2), zTrace - (2 / 2), xTrace + (2 / 2), yTrace + (2 / 2), zTrace + (2 / 2));

        List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, axis, null).stream().sorted(new Object() {
            Comparator<Entity> compareDistOf(double cx, double cy, double cz) {
                return Comparator.comparing(comparedEntity -> comparedEntity.getDistanceSq(cx, cy, cz));
            }
        }.compareDistOf(xTrace, yTrace, zTrace)).collect(Collectors.toList());

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
    public float getEyeHeight(Pose pose) {
        return 0.5f;
    }

    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        if (source == DamageSource.DRAGON_BREATH)
            return false;
        return super.attackEntityFrom(source, amount);
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    public void setAttackTimer(int timer) {
        this.attackTimer = timer;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }


    class MoveHelperController extends MovementController {
        public MoveHelperController(CursedEyeEntity eye) {
            super(eye);
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.posX - CursedEyeEntity.this.getPosX(), this.posY - CursedEyeEntity.this.getPosY(), this.posZ - CursedEyeEntity.this.getPosZ());
                double d0 = vector3d.length();
                if (d0 < CursedEyeEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = MovementController.Action.WAIT;
                    CursedEyeEntity.this.setMotion(CursedEyeEntity.this.getMotion().scale(0.5D));
                } else {
                    CursedEyeEntity.this.setMotion(CursedEyeEntity.this.getMotion().add(vector3d.scale(this.speed * 0.05D / d0)));
                    if (CursedEyeEntity.this.getAttackTarget() == null) {
                        Vector3d vector3d1 = CursedEyeEntity.this.getMotion();
                        CursedEyeEntity.this.rotationYaw = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        CursedEyeEntity.this.renderYawOffset = CursedEyeEntity.this.rotationYaw;
                    } else {
                        double d2 = CursedEyeEntity.this.getAttackTarget().getPosX() - CursedEyeEntity.this.getPosX();
                        double d1 = CursedEyeEntity.this.getAttackTarget().getPosZ() - CursedEyeEntity.this.getPosZ();
                        CursedEyeEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        CursedEyeEntity.this.renderYawOffset = CursedEyeEntity.this.rotationYaw;
                    }
                }

            }
        }
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            return CursedEyeEntity.this.navigator.noPath() && CursedEyeEntity.this.rand.nextInt(10) == 0;
        }

        public boolean shouldContinueExecuting() {
            return CursedEyeEntity.this.navigator.hasPath();
        }

        public void startExecuting() {
            Vector3d vector3d = this.getRandomLocation();
            if (vector3d != null) {
                CursedEyeEntity.this.navigator.setPath(CursedEyeEntity.this.navigator.getPathToPos(new BlockPos(vector3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vector3d getRandomLocation() {
            Vector3d vector3d;
            vector3d = CursedEyeEntity.this.getLook(0.0F);
            int i = 8;
            Vector3d vector3d2 = RandomPositionGenerator.findAirTarget(CursedEyeEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return vector3d2 != null ? vector3d2 : RandomPositionGenerator.findGroundTarget(CursedEyeEntity.this, 8, 4, -2, vector3d, (float)Math.PI / 2F);
        }
    }

    public static class FlyingAttackGoal extends Goal {

        CursedEyeEntity entity;
        public FlyingAttackGoal(CursedEyeEntity eye) {
            this.entity = eye;
        }

        {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            return entity.getAttackTarget() != null && !entity.getMoveHelper().isUpdating();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return entity.getMoveHelper().isUpdating() && entity.getAttackTarget() != null
                    && entity.getAttackTarget().isAlive();
        }

        @Override
        public void startExecuting() {
            LivingEntity livingentity = entity.getAttackTarget();
            Vector3d vec3d = livingentity.getEyePosition(1);
            entity.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
        }

        @Override
        public void tick() {
            LivingEntity livingentity = entity.getAttackTarget();
            if (entity.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                entity.attackEntityAsMob(livingentity);
            } else {
                double d0 = entity.getDistanceSq(livingentity);
                if (d0 < 16) {
                    Vector3d vec3d = livingentity.getEyePosition(1);
                    entity.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
                }
            }
        }
    }
}
