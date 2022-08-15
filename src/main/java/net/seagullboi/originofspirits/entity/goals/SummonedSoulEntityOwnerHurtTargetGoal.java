package net.seagullboi.originofspirits.entity.goals;

import net.seagullboi.originofspirits.entity.SummonableMonsterEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.EnumSet;

public class SummonedSoulEntityOwnerHurtTargetGoal extends TargetGoal {
    private final SummonableMonsterEntity tameAnimal;
    private LivingEntity ownerLastHurt;
    private int timestamp;

    public SummonedSoulEntityOwnerHurtTargetGoal(SummonableMonsterEntity p_i1668_1_) {
        super(p_i1668_1_, false);
        this.tameAnimal = p_i1668_1_;
        this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    public boolean shouldExecute() {
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity == null) {
            return false;
        } else {
            this.ownerLastHurt = livingentity.getLastAttackedEntity();
            int i = livingentity.getLastAttackedEntityTime();
            return i != this.timestamp && this.isSuitableTarget(this.ownerLastHurt, EntityPredicate.DEFAULT);
        }
    }

    public void startExecuting() {
        if (this.ownerLastHurt instanceof SummonableMonsterEntity) {
            if (((SummonableMonsterEntity) this.ownerLastHurt).getOwner() != this.tameAnimal.getOwner()) {
                this.goalOwner.setAttackTarget(this.ownerLastHurt);
            }
        } else {
            this.goalOwner.setAttackTarget(this.ownerLastHurt);
        }
        LivingEntity livingentity = this.tameAnimal.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastAttackedEntityTime();
        }

        super.startExecuting();
    }
}
