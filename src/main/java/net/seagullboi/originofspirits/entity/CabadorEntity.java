package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.api.IVerticalMount;
import net.seagullboi.originofspirits.item.BarleyStackItem;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SaddleItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CabadorEntity extends FlyingAnimalEntity implements IRideable, IFlyingAnimal, IVerticalMount {

    private static final DataParameter<Boolean> HAS_SADDLE = EntityDataManager.createKey(CabadorEntity.class, DataSerializers.BOOLEAN);
    private BlockPos boundOrigin;

    public CabadorEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new CabadorEntity.MoveHelperController(this);
        this.experienceValue = 3;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new CabadorEntity.MoveRandomGoal());
        this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(BarleyStackItem.block), false));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_SPEED, 1.5)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 8)
                .createMutableAttribute(Attributes.MAX_HEALTH, 30)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 32D);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity entity) {
        CabadorEntity cabador = TOOSEntityTypes.CABADOR.get().create(serverWorld);
        return cabador;
    }

    @Override
    public ActionResultType func_230254_b_(PlayerEntity entity, Hand resultType) {
        ItemStack itemstack = entity.getHeldItem(resultType);
        Item item = itemstack.getItem();

        if (this.world.isRemote) {
            boolean flag = this.isBreedingItem(itemstack);
            return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
        } else {
            if (entity.isCrouching()) {
                if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!entity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    this.heal(2f);
                    return ActionResultType.SUCCESS;

                } else if (item instanceof SaddleItem && !this.isChild()) {
                    if (!this.isSaddled()) {
                        this.setSaddled(true);
                        itemstack.shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.PASS;
                }
            } else {
                if (this.isSaddled()) {
                    entity.startRiding(this);
                }
            }
            return super.func_230254_b_(entity, resultType);
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        if(this.isSaddled() && this.getShouldBeDead()) {
            ItemEntity entityToSpawn = new ItemEntity(world, getPosX(), getPosY(), getPosZ(), new ItemStack(Items.SADDLE));
            entityToSpawn.setPickupDelay(10);
            world.addEntity(entityToSpawn);
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.getPersistentData().getInt("verticalMovement") == 1) {
            this.setMotion(this.getMotion().getX(), 0.3D, this.getMotion().getZ());
        } else if (this.getPersistentData().getInt("verticalMovement") == -1) {
            this.setMotion(this.getMotion().getX(), -0.3D, this.getMotion().getZ());
        }
    }

    @Override
    public void tick () {
    super.tick();

     if (this.isSaddled()) {
     this.ignoreFrustumCheck = true;
     }

     if (this.getPassengers().isEmpty()) {
     this.getPersistentData().putInt("verticalMovement", 0);
     }

     this.setNoGravity(true);
    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    public void writeAdditional (CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putBoolean("isSaddled", this.isSaddled());
    }

     public void readAdditional (CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        this.setSaddled(compoundNBT.getBoolean("isSaddled"));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HAS_SADDLE, false);
    }

    public boolean isSaddled() {
        return this.dataManager.get(HAS_SADDLE);
    }

    public void setSaddled(boolean saddled) {
        this.dataManager.set(HAS_SADDLE, saddled);
    }

    @Override
    public boolean shouldVerticalMove() {
        return false;
    }

    @Override
    public boolean boost() {
        return true;
    }

    @Override
    public void travelTowards(Vector3d travelVec) {

    }

    @Override
    public double getMountedYOffset() {
        return super.getMountedYOffset() - 0.2;
    }

    @Override
    public float getMountedSpeed() {
        return 1;
    }

    @Override
    protected boolean canBeRidden(Entity entity) {
        return super.canBeRidden(entity);
    }

    public boolean canBeSteered() {
        return true;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == BarleyStackItem.block;
    }

    @Override
    public void travel(Vector3d dir) {
        Entity entity = this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
        if (this.isBeingRidden()) {
            this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
            this.renderYawOffset = entity.rotationYaw;
            this.rotationYawHead = entity.rotationYaw;
            this.stepHeight = 1.0F;

            if (entity instanceof LivingEntity) {
                this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));

                float forward = ((LivingEntity) entity).moveForward;

                float strafe = ((LivingEntity) entity).moveStrafing;

                super.travel(new Vector3d(strafe, 0, forward));
            }

            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.getPosX() - this.prevPosX;
            double d0 = this.getPosZ() - this.prevPosZ;
            float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
            if (f1 > 1.0F)
                f1 = 1.0F;
            this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
            return;
        }
        this.stepHeight = 0.5F;
        this.jumpMovementFactor = 0.02F;

        super.travel(dir);
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
            return !CabadorEntity.this.getMoveHelper().isUpdating() && CabadorEntity.this.rand.nextInt(7) == 0;
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
            if (CabadorEntity.this.getPassengers().isEmpty()) {
                BlockPos blockpos = CabadorEntity.this.getBoundOrigin();
                if (blockpos == null) {
                    blockpos = CabadorEntity.this.getPosition();
                }

                for (int i = 0; i < 3; ++i) {
                    BlockPos blockpos1 = blockpos.add(CabadorEntity.this.rand.nextInt(15) - 7, CabadorEntity.this.rand.nextInt(11) - 5, CabadorEntity.this.rand.nextInt(15) - 7);
                    if (CabadorEntity.this.world.isAirBlock(blockpos1)) {
                        CabadorEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
                        if (CabadorEntity.this.getAttackTarget() == null) {
                            CabadorEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                        }
                        break;
                    }
                }
            }
        }
    }
}
