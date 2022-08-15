package net.seagullboi.originofspirits.entity.goals;

import net.seagullboi.originofspirits.entity.SummonableMonsterEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;

public class SummonedSoulFollowOwnerGoal extends Goal {
    private final SummonableMonsterEntity tamable;
    private LivingEntity owner;
    private final IWorldReader level;
    private final double speedModifier;
    private final PathNavigator navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private float oldWaterCost;
    private final boolean canFly;

    public SummonedSoulFollowOwnerGoal(SummonableMonsterEntity host, double speedModifier, float startDist, float stopDist, boolean canFly) {
        this.tamable = host;
        this.level = host.world;
        this.speedModifier = speedModifier;
        this.navigation = host.getNavigator();
        this.startDistance = startDist;
        this.stopDistance = stopDist;
        this.canFly = canFly;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(host.getNavigator() instanceof GroundPathNavigator) && !(host.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    public boolean shouldExecute() {
        LivingEntity livingentity = this.tamable.getOwner();
        if (livingentity == null) {
            return false;
        } else if (livingentity.isSpectator()) {
            return false;
        } else if (this.tamable.getDistanceSq(livingentity) < (double)(this.startDistance * this.startDistance)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    public boolean shouldContinueExecuting() {
        if (this.navigation.noPath()) {
            return false;
        } else {
            return !(this.tamable.getDistanceSq(this.owner) <= (double)(this.stopDistance * this.stopDistance));
        }
    }

    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tamable.getPathPriority(PathNodeType.WATER);
        this.tamable.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    public void resetTask() {
        this.owner = null;
        this.navigation.clearPath();
        this.tamable.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.tamable.getLookController().setLookPositionWithEntity(this.owner, 10.0F, (float)this.tamable.getVerticalFaceSpeed());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tamable.getLeashed() && !this.tamable.isPassenger()) {
                if (this.tamable.getDistanceSq(this.owner) >= 144.0D) {
                    this.tryToTeleportNearEntity();
                } else {
                    this.navigation.tryMoveToEntityLiving(this.owner, this.speedModifier);
                }

            }
        }
    }

    private void tryToTeleportNearEntity() {
        BlockPos blockpos = this.owner.getPosition();

        for(int i = 0; i < 10; ++i) {
            int j = this.getRandomNumber(-3, 3);
            int k = this.getRandomNumber(-1, 1);
            int l = this.getRandomNumber(-3, 3);
            boolean flag = this.tryToTeleportToLocation(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
            if (flag) {
                return;
            }
        }

    }
    private boolean tryToTeleportToLocation(int x, int y, int z) {
        if (Math.abs((double)x - this.owner.getPosX()) < 2.0D && Math.abs((double)z - this.owner.getPosZ()) < 2.0D) {
            return false;
        } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
            return false;
        } else {
            this.tamable.setLocationAndAngles((double)x + 0.5D, (double)y, (double)z + 0.5D, this.tamable.rotationYaw, this.tamable.rotationPitch);
            this.navigation.clearPath();
            return true;
        }
    }

    private boolean canTeleportTo(BlockPos blockPos) {
        /*PathNodeType pathnodetype = WalkNodeProcessor.getBlockPathTypeStatic(this.level, blockPos.mutable());
        if (pathnodetype != PathNodeType.WALKABLE) {
            return false;
        } else {
            BlockState blockstate = this.level.getBlockState(blockPos.below());
            if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos blockpos = blockPos.subtract(this.tamable.blockPosition());
                return this.level.noCollision(this.tamable, this.tamable.getBoundingBox().move(blockpos));
            }
        }*/
        BlockState blockState = this.tamable.getEntityWorld().getBlockState(blockPos.down());
        return blockState.canEntitySpawn(this.tamable.getEntityWorld(), blockPos.down(), this.tamable.getType()) &&
                this.tamable.getEntityWorld().isAirBlock(blockPos) && this.tamable.getEntityWorld().isAirBlock(blockPos.up());
    }

        private int getRandomNumber(int min, int max) {
            return this.tamable.getRNG().nextInt(max - min + 1) + min;
    }
}
