package net.seagullboi.originofspirits.world.gen;

import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSConfiguredFeatures {
    //Flowers
    public static final ConfiguredFeature<?, ?> MAGIC_MAGNOLIA_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.MAGIC_MAGNOLIA.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(2).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2);
    public static final ConfiguredFeature<?, ?> BLESSED_ROSE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.BLESSED_ROSE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(10);
    public static final ConfiguredFeature<?, ?> CRYSTAL_LILY_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.CRYSTAL_LILY.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2);
    public static final ConfiguredFeature<?, ?> VIOLET_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.VIOLET_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
    public static final ConfiguredFeature<?, ?> RED_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.RED_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
    public static final ConfiguredFeature<?, ?> ORANGE_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.ORANGE_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
    public static final ConfiguredFeature<?, ?> PEACE_LILY_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.PEACE_LILY.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(2).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1);
    public static final ConfiguredFeature<?, ?> SACRED_VIOLET_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.SACRED_VIOLET.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(4).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5);
}
