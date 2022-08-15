package net.seagullboi.originofspirits.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class SummonableMonsterEntity extends MonsterEntity {

    private LivingEntity owner;
    private boolean hasLimitedLife;
    private boolean explodeOnExpiry;
    private int lifeLimitBeforeDeath;
    private int limitedLifeTicks = 0;

    public SummonableMonsterEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public LivingEntity getOwner() {
        return this.owner;
    }

    public void setOwner(LivingEntity mobEntity) {
        this.owner = mobEntity;
    }

    public void writeAdditional(CompoundNBT compoundNBT) {
        super.writeAdditional(compoundNBT);
        compoundNBT.putBoolean("HasLifeLimit", this.hasLimitedLife);
        if (this.hasLimitedLife) {
            compoundNBT.putInt("LifeTicks", this.limitedLifeTicks);
            compoundNBT.putInt("LifeLimit", this.lifeLimitBeforeDeath);
            compoundNBT.putBoolean("ExplodeOnExpiry", this.explodeOnExpiry);
        }
    }

    public void readAdditional(CompoundNBT compoundNBT) {
        super.readAdditional(compoundNBT);
        this.setHasLimitedLife(compoundNBT.getBoolean("HasLifeLimit"));
        this.setLimitedLifeTicks(compoundNBT.getInt("LifeTicks"));
        this.setLifeLimitBeforeDeath(compoundNBT.getInt("LifeLimit"));
        this.setExplodeOnExpiry(compoundNBT.getBoolean("ExplodeOnExpiry"));
    }

    public void setLimitedLifeTicks(int limitedLifeTicks) {
        this.limitedLifeTicks = limitedLifeTicks;
    }

    public void setHasLimitedLife(boolean hasLimitedLife) {
        this.hasLimitedLife = hasLimitedLife;
    }

    public void setExplodeOnExpiry(boolean explodeOnExpiry) {
        this.explodeOnExpiry = explodeOnExpiry;
    }

    public void setLifeLimitBeforeDeath(int lifeLimitBeforeDeath) {
        this.lifeLimitBeforeDeath = lifeLimitBeforeDeath;
    }

    public void tick() {

    }
}