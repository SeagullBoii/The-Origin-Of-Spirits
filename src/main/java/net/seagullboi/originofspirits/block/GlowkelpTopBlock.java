package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.KelpTopBlock;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class GlowkelpTopBlock extends KelpTopBlock {
    public GlowkelpTopBlock(Properties builder) {
        super(builder);
    }

    @Override
    protected Block getBodyPlantBlock() {
        return TOOSBlocks.GLOWKELP_PLANT.get();
    }

}
