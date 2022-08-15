package net.seagullboi.originofspirits.item;

import net.seagullboi.originofspirits.api.IReachWeapon;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public abstract class SpearItem extends SwordItem implements IReachWeapon {

    public SpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public double getReachDistance() {
        return 6;
    }
}
