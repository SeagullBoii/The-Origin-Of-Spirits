package net.seagullboi.originofspirits.block;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.KelpBlock;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class GlowkelpBlock extends KelpBlock {

    public GlowkelpBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) TOOSBlocks.GLOWKELP.get();
    }
}
