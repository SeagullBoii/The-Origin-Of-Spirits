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
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import net.seagullboi.originofspirits.particle.CursedExplosionParticle;
import net.seagullboi.originofspirits.registry.TOOSEntityTypes;
import net.seagullboi.originofspirits.registry.TOOSParticles;

import javax.annotation.Nonnull;

public class CursedLaserProjectile extends AbstractArrowEntity {

    public static final DataParameter<Boolean> EXPLODES = EntityDataManager.createKey(CursedLaserProjectile.class, DataSerializers.BOOLEAN);
    public static final DataParameter<Boolean> MARKSMAN = EntityDataManager.createKey(CursedLaserProjectile.class, DataSerializers.BOOLEAN);
    private double damage = 9;
    public int lifespan = 0;
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

    public boolean getExplodes() {
        return this.dataManager.get(EXPLODES);
    }

    public void setExplodes(boolean explodes) {
        this.dataManager.set(EXPLODES, explodes);
    }

    public boolean getMarksman() {
        return this.dataManager.get(MARKSMAN);
    }

    public void setMarksman(boolean marksman) {
        this.dataManager.set(MARKSMAN, marksman);
    }

    public CursedLaserProjectile(EntityType<? extends AbstractArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public CursedLaserProjectile(EntityType<? extends CursedLaserProjectile> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getOwner()), (float) this.getDamage());
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
        if (entity instanceof PlayerEntity) {
            ((PlayerEntity) entity).getCooldownTracker().setCooldown(Items.SHIELD, 100);
        }

        if (this.getExplodes()) {
            this.explode(world, this);
        }
        System.out.println(this.getExplodes());
    }

    @Override
    protected void arrowHit(LivingEntity entity) {
        super.arrowHit(entity);
        entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
    }

    public void explode(World world, AbstractArrowEntity projectile) {
        world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1.4f, true);
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).spawnParticle(CursedExplosionParticle.particle, this.getPosX(), this.getPosY(), this.getPosZ(), 10, 0.3, 0.3, 0.3, 0);
        }
        world.createExplosion(owner, (int) this.getPosX(), (int) this.getPosY(), (int) this.getPosZ(), 3, Explosion.Mode.NONE);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();

        ++lifespan;
        if (lifespan >= 20) {
            this.remove();
        } else if (this.lifespan == 10 && this.getMarksman()) {
            this.setDamage(this.getDamage() + 2);
        }

        if (this.inGround) {
            System.out.println(this.getExplodes());
            if (this.getExplodes()) {
                this.explode(world, this);
            }
            this.remove();
        }

        double i = Math.random() / 2 ;

        world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), this.getPosX(), this.getPosY(), this.getPosZ() , 0, 0, 0);
        for (int index0 = 0; index0 < 5; index0++) {
            world.addParticle(TOOSParticles.CURSED_FLAME_PARTICLE.get(), this.getPosX() + i, this.getPosY() + i, this.getPosZ() + i, 0, 0, 0);
        }
    }



    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    protected IParticleData getParticle() {
        return TOOSParticles.CURSED_FLAME_PARTICLE.get();
    }

    public boolean hurt(DamageSource damageSource, float v) {
        return false;
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    protected boolean isFireballFiery() {
        return false;
    }

    protected float getWaterDrag() { return 0.99f; }

    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        if (compoundNBT.contains("Explodes")) {
            this.setExplodes(compoundNBT.getBoolean("Explodes"));
        }

        if (compoundNBT.contains("Marksman")) {
            this.setMarksman(compoundNBT.getBoolean("Marksman"));
        }
    }

    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putBoolean("Explodes", this.getExplodes());
        compoundNBT.putBoolean("Marksman", this.getMarksman());
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(EXPLODES, false);
        this.dataManager.register(MARKSMAN, false);
    }

    public static CursedLaserProjectile shoot(LivingEntity entity, LivingEntity target, int damage) {
        CursedLaserProjectile entityarrow = new CursedLaserProjectile(TOOSEntityTypes.CURSED_LASER.get(), entity, entity.world);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 2;
        double d1 = target.getPosX() - entity.getPosX();
        double d3 = target.getPosZ() - entity.getPosZ();
        entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.8F, 0.0F);
        entityarrow.setSilent(true);
        entityarrow.setDamage(damage);
        entity.world.addEntity(entityarrow);
        entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.HOSTILE, 1, 2);
        return entityarrow;
    }
}
