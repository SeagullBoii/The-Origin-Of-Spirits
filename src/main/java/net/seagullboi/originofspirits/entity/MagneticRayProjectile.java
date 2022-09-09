package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.seagullboi.originofspirits.particle.MagneticProjectileParticle;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;

import javax.annotation.Nonnull;

public class MagneticRayProjectile extends AbstractArrowEntity {

    int damage = 6;
    int timer = 40;
    LivingEntity owner;

    public MagneticRayProjectile(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MagneticRayProjectile(EntityType<? extends AbstractArrowEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public MagneticRayProjectile(EntityType<? extends AbstractArrowEntity> type, LivingEntity shooter, World worldIn) {
        super(type, shooter, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround) {
            this.remove();
        }

        if (this.timer > 0) {
            this.timer--;
        } else {
            this.remove();
        }

        world.addParticle(MagneticProjectileParticle.particle, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected float getWaterDrag() {
        return 0.99f;
    }

    @Override
    protected void arrowHit(LivingEntity entity) {
        super.arrowHit(entity);

        if (entity.hurtTime > 0) {
            this.remove();
            entity.hurtTime = 0;
        }

        entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
    }

    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        Entity entity = result.getEntity();
        if (entity.hurtResistantTime >= 8) {
          //  entity.hurtResistantTime = 0;
        }
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }


    public static void spreadProjectiles(LivingEntity entity, float spread, int projectileCount, double damage) {
        float yaw = entity.rotationYaw;

        shoot(entity.world, entity, 1, damage, 0, 0);
        entity.rotationYaw = yaw + spread / 2;
        shoot(entity.world, entity, 1, damage - 1, 0, 0);
        entity.rotationYaw = yaw + spread;
        shoot(entity.world, entity, 1, damage - 1, 0, 0);
        entity.rotationYaw = yaw + spread * 2 - spread / 2;
        shoot(entity.world, entity, 1, damage - 2, 0, 0);
        entity.rotationYaw = yaw + spread * 2;
        shoot(entity.world, entity, 1, damage - 2, 0, 0);

        entity.rotationYaw = yaw - spread / 2;
        shoot(entity.world, entity, 1, damage - 1, 0, 0);
        entity.rotationYaw = yaw - spread;
        shoot(entity.world, entity, 1, damage - 1, 0, 0);
        entity.rotationYaw = yaw - spread * 2 + spread / 2;
        shoot(entity.world, entity, 1, damage - 2, 0, 0);
        entity.rotationYaw = yaw - spread * 2;
        shoot(entity.world, entity, 1, damage - 2, 0, 0);

        entity.rotationYaw = yaw;
    }

    public static MagneticRayProjectile shoot(World world, LivingEntity entity, float power, double damage, int knockback,float inaccuracy) {
        MagneticRayProjectile ray = new MagneticRayProjectile(TOOSEntityTypes.MAGNETIC_RAY.get(), entity, world);
        ray.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 1, inaccuracy);
        ray.setSilent(true);
        ray.setIsCritical(false);
        ray.setDamage(damage);
        ray.setKnockbackStrength(knockback);
        world.addEntity(ray);
        return ray;
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    public LivingEntity getOwner() {
        return owner;
    }

    public void setOwner(LivingEntity entity) {
        owner = entity;
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
