package net.seagullboi.originofspirits.entity.goals;

import net.seagullboi.originofspirits.entity.SummonableMonsterEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.EnumSet;

public class SummonedSoulOwnerHurtByTargeGoal extends TargetGoal {
    private final SummonableMonsterEntity tameAnimal;
    private LivingEntity ownerLastHurtBy;
    private int timestamp;

    public SummonedSoulOwnerHurtByTargeGoal(SummonableMonsterEntity p_i1667_1_) {
        super(p_i1667_1_, false);
        this.tameAnimal = p_i1667_1_;
        this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean shouldExecute() {
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity == null) {
            return false;
        } else {
            this.ownerLastHurtBy = livingentity.getRevengeTarget();
            int i = livingentity.getRevengeTimer();
            return i != this.timestamp && this.isSuitableTarget(this.ownerLastHurtBy, EntityPredicate.DEFAULT);
        }
    }

    public void startExecuting() {
        if (this.ownerLastHurtBy instanceof SummonableMonsterEntity) {
            if (((SummonableMonsterEntity) this.ownerLastHurtBy).getOwner() != this.tameAnimal.getOwner()) {
                this.goalOwner.setAttackTarget(this.ownerLastHurtBy);
            }
        } else {
            this.goalOwner.setAttackTarget(this.ownerLastHurtBy);
        }
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getRevengeTimer();
        }

        super.startExecuting();
    }
}
