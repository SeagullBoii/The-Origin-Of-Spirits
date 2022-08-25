package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;
import net.seagullboi.originofspirits.item.CursedFlamethrowerProjectileItem;
import net.seagullboi.originofspirits.particle.CursedFlameParticleParticle;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;

import javax.annotation.Nonnull;
import java.util.Random;

public class BulletEntity extends AbstractArrowEntity {

    public static final DataParameter<Integer> NOGRAVTIMER = EntityDataManager.createKey(BulletEntity.class, DataSerializers.VARINT);
    public static final DataParameter<String> MATERIAL = EntityDataManager.createKey(BulletEntity.class, DataSerializers.STRING);

    LivingEntity owner;

    public BulletEntity(EntityType<? extends AbstractArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public BulletEntity(EntityType<? extends BulletEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }
    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getOwner()), (float) this.getDamage());
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte) 3);
            this.remove();
        }

    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround) {
            this.remove();
        }

        if (this.getNoGravTimer() > 0) {
            this.setNoGravTimer(this.getNoGravTimer() - 1);
        }
    }

    @Override
    protected void arrowHit(LivingEntity entity) {
        super.arrowHit(entity);
        entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
    }

    public static BulletEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback, int noGravTimer, float inaccuracy, String material) {
        BulletEntity bullet = new BulletEntity(TOOSEntityTypes.BULLET.get(), entity, world);
        bullet.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 1, inaccuracy);
        bullet.setSilent(true);
        bullet.setIsCritical(false);
        bullet.setDamage(damage);
        bullet.setKnockbackStrength(knockback);
        world.addEntity(bullet);
        bullet.setNoGravTimer(noGravTimer);
        bullet.setMaterial(material);
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();
        return bullet;
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    public boolean hasNoGravity() {
        return getNoGravTimer() > 0;
    }

    public LivingEntity getOwner() {
        return owner;
    }

    public void setOwner(LivingEntity entity) {
        owner = entity;
    }

    public int getNoGravTimer() {
        return this.dataManager.get(NOGRAVTIMER);
    }

    public void setNoGravTimer(int timer) {
        this.dataManager.set(NOGRAVTIMER, timer);
    }

    public String getMaterial() {
        return this.dataManager.get(MATERIAL);
    }

    public void setMaterial(String material) {
        this.dataManager.set(MATERIAL, material);
    }

    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        if (compoundNBT.contains("Material")) {
            this.setMaterial(compoundNBT.getString("Material"));
        }

        if (compoundNBT.contains("NoGravTimer")) {
            this.setNoGravTimer(compoundNBT.getInt("NoGravTimer"));
        }
    }

    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putString("Material", this.getMaterial());
        compoundNBT.putInt("NoGravTimer", this.getNoGravTimer());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(NOGRAVTIMER, 10);
        this.dataManager.register(MATERIAL, "iron");
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
