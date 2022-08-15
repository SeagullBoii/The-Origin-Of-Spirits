package net.seagullboi.originofspirits.entity;

import net.seagullboi.originofspirits.particle.CluffParticleParticle;
import net.seagullboi.originofspirits.particle.CursedFlameParticleParticle;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class CluffCloudProjectile extends AbstractArrowEntity {

    private double damage = 0;
    LivingEntity owner;

    public LivingEntity getOwner() {
        return owner;
    }

    public void setOwner(LivingEntity entity) {
        owner = entity;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double dmg) {
        damage = dmg;
    }

    public CluffCloudProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public CluffCloudProjectile(EntityType<? extends CluffCloudProjectile> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getOwner()), (float) this.getDamage());
        entity.setMotion(entity.getMotion().getX(), 1, entity.getMotion().getZ());
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected void arrowHit(LivingEntity entity) {
        super.arrowHit(entity);
        entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.inGround) {
            this.remove();
        }

        double i = Math.random() / 2 ;

        world.addParticle(CluffParticleParticle.particle, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
        for (int index0 = 0; index0 < 5; index0++) {
            world.addParticle(ParticleTypes.CLOUD, this.getPosX() + i, this.getPosY() + i, this.getPosZ() + i, 0, 0, 0);
        }
    }



    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected IParticleData getParticle() {
        return CursedFlameParticleParticle.particle;
    }

    public boolean hurt(DamageSource damageSource, float v) {
        return false;
    }

    protected boolean isFireballFiery() {
        return false;
    }

    protected float getWaterDrag() { return 0.99f; }

    public static CluffCloudProjectile shoot(LivingEntity entity, LivingEntity target, int damage) {
        CluffCloudProjectile entityarrow = new CluffCloudProjectile(TOOSEntityTypes.CLUFF_CLOUD.get(), entity, entity.world);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 2;
        double d1 = target.getPosX() - entity.getPosX();
        double d3 = target.getPosZ() - entity.getPosZ();
        entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.8F, 0.0F);
        entityarrow.setSilent(true);
        entityarrow.setDamage(0);
        entity.world.addEntity(entityarrow);
        entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.HOSTILE, 1, 0);
        return entityarrow;
    }
}
