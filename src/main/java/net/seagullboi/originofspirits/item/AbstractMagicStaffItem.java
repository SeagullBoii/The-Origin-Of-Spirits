package net.seagullboi.originofspirits.item;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;
import net.seagullboi.originofspirits.util.GlobalVarUtil;

import java.util.Set;

public abstract class AbstractMagicStaffItem extends ToolItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.AIR);

    public AbstractMagicStaffItem(float attackDamageIn, float attackSpeedIn, IItemTier tier, Properties builderIn) {
        super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builderIn);
    }

    public void consumeMana(PlayerEntity entity, int manaConsumed, int manaCooldown) {
        if (manaConsumed <= GlobalVarUtil.getMana(entity)) {
                GlobalVarUtil.setMana(entity, GlobalVarUtil.getMana(entity) - manaConsumed);
                GlobalVarUtil.setManaCooldown(entity, manaCooldown);
        }
    }
}
