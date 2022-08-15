package net.seagullboi.originofspirits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class FlyingAnimalEntity extends AnimalEntity implements IFlyingAnimal {

    public BlockPos boundOrigin;

    protected FlyingAnimalEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void readAdditional(CompoundNBT pCompound) {
        super.readAdditional(pCompound);
        if (pCompound.contains("BoundX")) {
            this.boundOrigin = new BlockPos(pCompound.getInt("BoundX"), pCompound.getInt("BoundY"), pCompound.getInt("BoundZ"));
        }
    }

    public void writeAdditional(CompoundNBT pCompound) {
        super.writeAdditional(pCompound);
        if (this.boundOrigin != null) {
            pCompound.putInt("BoundX", this.boundOrigin.getX());
            pCompound.putInt("BoundY", this.boundOrigin.getY());
            pCompound.putInt("BoundZ", this.boundOrigin.getZ());
        }
    }

    public void setBoundOrigin(@Nullable BlockPos pBoundOrigin) {
        this.boundOrigin = pBoundOrigin;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public boolean hasNoGravity() {
        return true;
    }

    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    public class MoveHelperController extends MovementController {
        public MoveHelperController(FlyingAnimalEntity floatingMob) {
            super(floatingMob);
        }

        public void tick() {
            if (this.action == Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.posX - FlyingAnimalEntity.this.getPosX(), this.posY - FlyingAnimalEntity.this.getPosY(), this.posZ - FlyingAnimalEntity.this.getPosZ());
                double d0 = vector3d.length();
                if (d0 < FlyingAnimalEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = Action.WAIT;
                    FlyingAnimalEntity.this.setMotion(FlyingAnimalEntity.this.getMotion().scale(0.5D));
                } else {
                    FlyingAnimalEntity.this.setMotion(FlyingAnimalEntity.this.getMotion().add(vector3d.scale(this.speed * 0.05D / d0)));
                    if (FlyingAnimalEntity.this.getAttackTarget() == null) {
                        Vector3d vector3d1 = FlyingAnimalEntity.this.getMotion();
                        FlyingAnimalEntity.this.rotationYaw = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                        FlyingAnimalEntity.this.renderYawOffset = FlyingAnimalEntity.this.rotationYaw;
                    } else {
                        double d2 = FlyingAnimalEntity.this.getAttackTarget().getPosX() - FlyingAnimalEntity.this.getPosX();
                        double d1 = FlyingAnimalEntity.this.getAttackTarget().getPosZ() - FlyingAnimalEntity.this.getPosZ();
                        FlyingAnimalEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                        FlyingAnimalEntity.this.renderYawOffset = FlyingAnimalEntity.this.rotationYaw;
                    }
                }
            }
        }
    }
}
