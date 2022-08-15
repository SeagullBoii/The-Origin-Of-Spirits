package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SacFrogEggsEntity extends CreatureEntity {

    private float xRotation = 0;
    private boolean xRotIncreasing = false;
    private boolean rotFast = false;
    private boolean rotSlow = false;
    private static final DataParameter<Integer> AGE = EntityDataManager.createKey(SacFrogEggsEntity.class, DataSerializers.VARINT);
    int age = this.dataManager.get(AGE);

    public SacFrogEggsEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }


    public int getAge() {
        return this.dataManager.get(AGE);
    }

    public void setAge(int ageNumber) {
        this.dataManager.set(AGE, ageNumber);
    }

    public float getXRotation() {
        return this.getXRotation();
    }

    public void setXRotation(float rotation) {
        this.xRotation = rotation;
    }

    public boolean getRotIncreasing() {
        return this.xRotIncreasing;
    }

    public void setRotIncreasing(boolean increasing) {
        this.xRotIncreasing = increasing;
    }

    public void tick() {
        super.tick();
        age++;
        this.dataManager.set(AGE, age);
        if (this.getAge() >= 4800) {
            for (int index0 = 0; index0 < (5); index0++) {
                BlockPos blockPos = new BlockPos(getPosX() * Math.random() / 2, getPosY() * Math.random() / 2, getPosZ() * Math.random() / 2);
                SacFrogEntity frog = TOOSEntityTypes.SAC_FROG.get().create(world);
                assert frog != null;
                frog.moveToBlockPosAndAngles(blockPos, this.getRotationYawHead(), 0);
                frog.setChild(true);
                frog.setGrowingAge(-10000);
                world.addEntity(frog);
                world.playSound(getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 1, 1, true);
                this.remove();
            }
        }
      this.clearActivePotions();
    }

    @Override
    public void onDeath(DamageSource source) {
        double x = this.getPosX();
        double y = this.getPosY();
        double z = this.getPosZ();
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
                        ((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(Effects.POISON, 50, 0, (false), (true)));

            }
        }
        if (world instanceof World && !world.isRemote()) {
            world.playSound(null, new BlockPos((int) x, (int) y, (int) z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.squirt")), SoundCategory.VOICE, (float) 1, (float) 1);
        } else {
            world.playSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.squid.squirt")), SoundCategory.VOICE, (float) 1, (float) 1, false);
        }
    }

        @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getImmediateSource() instanceof PotionEntity)
            return false;
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }



    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(AGE, 0);
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        compound.putInt("Age", this.getAge());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        this.setAge(compound.getInt("Age"));
    }

}
