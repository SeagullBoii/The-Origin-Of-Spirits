package net.seagullboi.originofspirits.world.gen.features;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.BlueberryBushBlock;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSConfiguredFeatures {

    public static class States {
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState BLUEBERRY_BUSH = TOOSBlocks.BLUEBERRY_BUSH.get().getDefaultState().with(BlueberryBushBlock.AGE, Integer.valueOf(3));
        protected static final BlockState CURSED_LAVA = TOOSBlocks.CURSED_LAVA_BLOCK.get().getDefaultState();
    }

    public static class Configs {
        public static final ConfiguredFeature<?, ?> MAGIC_MAGNOLIA_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.MAGIC_MAGNOLIA.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(2).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2);
        public static final ConfiguredFeature<?, ?> BLESSED_ROSE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.BLESSED_ROSE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(10);
        public static final ConfiguredFeature<?, ?> CRYSTAL_LILY_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.CRYSTAL_LILY.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2);
        public static final ConfiguredFeature<?, ?> VIOLET_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.VIOLET_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
        public static final ConfiguredFeature<?, ?> RED_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.RED_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
        public static final ConfiguredFeature<?, ?> ORANGE_PERIWINKLE_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.ORANGE_PERIWINKLE.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(3);
        public static final ConfiguredFeature<?, ?> PEACE_LILY_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.PEACE_LILY.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(2).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1);
        public static final ConfiguredFeature<?, ?> SACRED_VIOLET_CONFIG = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.SACRED_VIOLET.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(5);
        public static final ConfiguredFeature<?, ?> BLUEBERRY_BUSH_PATCH = Feature.FLOWER.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.BLUEBERRY_BUSH.get().getDefaultState().with(BlueberryBushBlock.AGE, 3)), SimpleBlockPlacer.PLACER)).tries(5).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1);
        public static final ConfiguredFeature<?, ?> CURSED_CAVE_GEN = Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(TOOSBlocks.CURSED_CAVE_GENERATOR.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(6).build()).withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8, 0, 5)));
        public static final ConfiguredFeature<?, ?> CURSED_LAVA_LAKE = Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(States.CURSED_LAVA)).withPlacement(Placement.LAVA_LAKE.configure(new ChanceConfig(80)));
    }

  //  public static ConfiguredFeature<?, ?> TEST_FEATURE;


    public static void registerConfigs() {
        register("blueberry_bush_patch", TOOSConfiguredFeatures.Configs.BLUEBERRY_BUSH_PATCH.range(256).square().chance(100));
        register("cursed_cave_generation", TOOSConfiguredFeatures.Configs.CURSED_CAVE_GEN);
        register("cursed_lava_lake", TOOSConfiguredFeatures.Configs.CURSED_LAVA_LAKE);
    }

    private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> feature) {
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OriginOfSpirits.MOD_ID, name), feature);
    }

    private static ConfiguredFeature<?, ?> registerFeature(final String name, ConfiguredFeature<?, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(OriginOfSpirits.MOD_ID, name), feature);
    }

}
