package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.registry.TOOSItems;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SacFrogEntity extends RabbitEntity {

    private int refillTimer = 0;
    private boolean hasSack = true;
    private boolean swimmingUp;
    private int jumpTicks;
    private int jumpDuration;
    private boolean wasOnGround;
    protected final SwimmerPathNavigator waterNavigator;
    protected final GroundPathNavigator groundNavigator;
    public static final DataParameter<Boolean> SACK = EntityDataManager.createKey(SacFrogEntity.class, DataSerializers.BOOLEAN);

    public SacFrogEntity(EntityType<? extends RabbitEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.jumpController = new SacFrogEntity.JumpHelperController(this);
        this.moveController = new SacFrogEntity.MoveHelperController(this);
        this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
        this.groundNavigator = new GroundPathNavigator(this, worldIn);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new SacFrogEntity.PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(TOOSItems.BARLEY_STACK.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new SacFrogEntity.GoToBeachGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new SacFrogEntity.GoToWaterGoal(this, 1.0D) {
            public boolean shouldExecute() {
            return world.isDaytime() && !world.isRaining();
         }
        });
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 20.0D).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D).createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
    }

    public boolean getSack() {
        return this.hasSack;
    }

    public int getRefillTimer() {
        return this.refillTimer;
    }

    public void setSack(boolean sack) {
        this.hasSack = sack;
    }

    public void setRefillTimer(int timer) {
        this.refillTimer = timer;
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Sack", this.getSack());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSack(compound.getBoolean("Sack"));
    }

    @Nullable
    @Override
    public RabbitEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
        SacFrogEntity sacFrogEntity = TOOSEntityTypes.SAC_FROG.get().create(p_241840_1_);
        if (Math.random() < 0.01) {
            sacFrogEntity.setGrowingAge(-23999);
        }
        if (sacFrogEntity != null) {
            sacFrogEntity.enablePersistence();
        }

        return sacFrogEntity;
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (this.getGrowingAge() == -24000) {
            BlockPos pos = new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());
            SacFrogEggsEntity eggs = TOOSEntityTypes.SAC_FROG_EGGS.get().create(world);
            eggs.setAge(5);
            assert eggs != null;
            eggs.moveToBlockPosAndAngles(pos, Math.round(Math.random() * 360), 0);
            world.addEntity(eggs);
            this.remove();
        }
        return spawnDataIn;
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == TOOSItems.BARLEY_STACK.get();
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.FALL)
            return false;
        /**
         *Spray
         */
        if (this.getSack()) {
            double x = this.getPosX();
            double y = this.getPosY();
            double z = this.getPosZ();
            Entity entity = this;
            Entity sourceentity = source.getTrueSource();

                if (world instanceof ServerWorld) {
                    ((ServerWorld) world).spawnParticle(ParticleTypes.SNEEZE, x, y, z, 14, 0.2, 0.2, 0.2, 0);
                }
                {
                    List<Entity> _entfound = world
                            .getEntitiesWithinAABB(Entity.class,
                                    new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing(_entcnd -> _entcnd.getDistanceSq(_x, _y, _z));
                                }
                            }.compareDistOf(x, y, z)).collect(Collectors.toList());
                    for (Entity entityiterator : _entfound) {
                        if (!(entity == entityiterator || entity instanceof LivingEntity)) {
                            if (!this.isChild()) {
                                ((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(Effects.POISON, 100, 1, (false), (true)));
                                    ((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 80, 0, (false), (true)));
                            } else {
                                ((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(Effects.POISON, 50, 0, (false), (true)));
                            }
                        }
                    }
                }
                if (world instanceof World && !world.isRemote()) {
                    world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
                            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.squirt")),
                            SoundCategory.VOICE, (float) 1, (float) 1);
                } else {
                    world.playSound(x, y, z,
                            ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.squirt")),
                            SoundCategory.VOICE, (float) 1, (float) 1, false);
                }

            this.setSack(false);
            this.setRefillTimer(0);
        }
        return super.attackEntityFrom(source, amount);
    }

    public void livingTick() {
        super.livingTick();
        if (this.jumpTicks != this.jumpDuration) {
            ++this.jumpTicks;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
        /**
         * Sack Refill
         */

        if (this.getRefillTimer() >= 100) {
            this.setSack(true);
            this.setRefillTimer(0);
        }

        /**
         * Cure Poison
         */
        if (this.isPotionActive(Effects.POISON)) {
            this.removePotionEffect(Effects.POISON);
        }
        if (this.isPotionActive(Effects.WEAKNESS)) {
            this.removePotionEffect(Effects.WEAKNESS);
        }


        if (this.isChild()) {
            if (this.getGrowingAge() <= -23000) {
                BlockPos pos = new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ());
                SacFrogEggsEntity eggs = TOOSEntityTypes.SAC_FROG_EGGS.get().create(world);
                eggs.setAge(5);
                assert eggs != null;
                eggs.moveToBlockPosAndAngles(pos, Math.round(Math.random() * 360), 0);
                world.addEntity(eggs);
                this.remove();
            }
        }
    }

    @Override
    public net.minecraft.util.SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:frog_ambient"));
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:frog_hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("originofspirits:frog_death"));
    }


    /**
     *Swimming Up Boolean
     */
    public void setSwimmingUp(boolean p_204713_1_) {
        this.swimmingUp = p_204713_1_;
    }

    private boolean isSwimmingUp() {
        if (this.swimmingUp) {
            return true;
        } else {
            LivingEntity livingentity = this.getAttackTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    /**
     * is Close to Path Target boolean
     */

    protected boolean isCloseToPathTarget() {
        Path path = this.getNavigator().getPath();
        if (path != null) {
            BlockPos blockpos = path.getTarget();
            if (blockpos != null) {
                double d0 = this.getDistanceSq(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                return d0 < 4.0D;
            }
        }

        return false;
    }


    /**
     * Movement
     */

    static class MoveHelperController extends MovementController {
        private final SacFrogEntity rabbit;
        private double nextJumpSpeed;

        public MoveHelperController(SacFrogEntity rabbit) {
            super(rabbit);
            this.rabbit = rabbit;
        }

        public void tick() {
            if (this.rabbit.onGround && !this.rabbit.isJumping && !((RabbitEntity.JumpHelperController)this.rabbit.jumpController).getIsJumping()) {
                this.rabbit.setMovementSpeed(0.0D);
            } else if (this.isUpdating()) {
                this.rabbit.setMovementSpeed(this.nextJumpSpeed);
            }



            super.tick();
        }

        /**
         * Sets the speed and location to move to
         */
        public void setMoveTo(double x, double y, double z, double speedIn) {
            if (this.rabbit.isInWater()) {
                speedIn = 1.5D;
            }

            super.setMoveTo(x, y, z, speedIn);
            if (speedIn > 0.0D) {
                this.nextJumpSpeed = speedIn;
            }

        }
    }

    /**
     *Go Up Goal
    */

    static class GoToBeachGoal extends MoveToBlockGoal {
        private final SacFrogEntity drowned;

        public GoToBeachGoal(SacFrogEntity p_i48911_1_, double p_i48911_2_) {
            super(p_i48911_1_, p_i48911_2_, 8, 2);
            this.drowned = p_i48911_1_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return super.shouldExecute() && !this.drowned.world.isDaytime() && this.drowned.isInWater() && this.drowned.getPosY() >= (double)(this.drowned.world.getSeaLevel() - 3);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting();
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            BlockPos blockpos = pos.up();
            return worldIn.isAirBlock(blockpos) && worldIn.isAirBlock(blockpos.up()) && worldIn.getBlockState(pos).canSpawnMobs(worldIn, pos, this.drowned);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.drowned.setSwimmingUp(false);
            this.drowned.navigator = this.drowned.groundNavigator;
            super.startExecuting();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            super.resetTask();
        }
    }

    /**
     * Go to Water Goal
     */

    static class GoToWaterGoal extends Goal {
        private final CreatureEntity entity;
        private double field_204731_b;
        private double field_204732_c;
        private double field_204733_d;
        private final double field_204734_e;
        private final World field_204735_f;

        public GoToWaterGoal(CreatureEntity p_i48910_1_, double p_i48910_2_) {
            this.entity = p_i48910_1_;
            this.field_204734_e = p_i48910_2_;
            this.field_204735_f = p_i48910_1_.world;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (!this.field_204735_f.isDaytime()) {
                return false;
            } else if (this.entity.isInWater()) {
                return false;
            } else {
                Vector3d vector3d = this.func_204729_f();
                if (vector3d == null) {
                    return false;
                } else {
                    this.field_204731_b = vector3d.x;
                    this.field_204732_c = vector3d.y;
                    this.field_204733_d = vector3d.z;
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return !this.entity.getNavigator().noPath();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.entity.getNavigator().tryMoveToXYZ(this.field_204731_b, this.field_204732_c, this.field_204733_d, this.field_204734_e);
        }

        @Nullable
        private Vector3d func_204729_f() {
            Random random = this.entity.getRNG();
            BlockPos blockpos = this.entity.getPosition();

            for(int i = 0; i < 10; ++i) {
                BlockPos blockpos1 = blockpos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
                if (this.field_204735_f.getBlockState(blockpos1).isIn(Blocks.WATER)) {
                    return Vector3d.copyCenteredHorizontally(blockpos1);
                }
            }

            return null;
        }
    }

    /**
     * Swim Up Goal
     */

    static class SwimUpGoal extends Goal {
        private final SacFrogEntity field_204736_a;
        private final double field_204737_b;
        private final int targetY;
        private boolean obstructed;

        public SwimUpGoal(SacFrogEntity p_i48908_1_, double p_i48908_2_, int p_i48908_4_) {
            this.field_204736_a = p_i48908_1_;
            this.field_204737_b = p_i48908_2_;
            this.targetY = p_i48908_4_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return !this.field_204736_a.world.isDaytime() && this.field_204736_a.isInWater() && this.field_204736_a.getPosY() < (double)(this.targetY - 2);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return this.shouldExecute() && !this.obstructed;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.field_204736_a.getPosY() < (double)(this.targetY - 1) && (this.field_204736_a.getNavigator().noPath() || this.field_204736_a.isCloseToPathTarget())) {
                Vector3d vector3d = RandomPositionGenerator.findRandomTargetBlockTowards(this.field_204736_a, 4, 8, new Vector3d(this.field_204736_a.getPosX(), this.targetY - 1, this.field_204736_a.getPosZ()));
                if (vector3d == null) {
                    this.obstructed = true;
                    return;
                }

                this.field_204736_a.getNavigator().tryMoveToXYZ(vector3d.x, vector3d.y, vector3d.z, this.field_204737_b);
            }

        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.field_204736_a.setSwimmingUp(true);
            this.obstructed = false;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.field_204736_a.setSwimmingUp(false);
        }
    }

    /**
     * Panic
     */
    static class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        private final SacFrogEntity rabbit;

        public PanicGoal(SacFrogEntity rabbit, double speedIn) {
            super(rabbit, speedIn);
            this.rabbit = rabbit;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            super.tick();
            this.rabbit.setMovementSpeed(this.speed);
        }
    }
}
