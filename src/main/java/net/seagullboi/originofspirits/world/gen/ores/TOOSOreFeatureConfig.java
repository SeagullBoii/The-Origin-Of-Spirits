package net.seagullboi.originofspirits.world.gen.ores;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSOreFeatureConfig extends OreFeatureConfig {

    public TOOSOreFeatureConfig(RuleTest p_i241989_1_, BlockState state, int size) {
        super(p_i241989_1_, state, size);
    }

    public static final class FillerBlockType {
        public static final RuleTest BLACK_SAND = new BlockMatchRuleTest(TOOSBlocks.BLACK_SAND.get());
        public static final RuleTest AIR = new BlockMatchRuleTest(Blocks.AIR);
        public static final RuleTest BLESSED_STONE = new BlockMatchRuleTest(TOOSBlocks.BLESSED_STONE.get());
    }
}
