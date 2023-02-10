package net.seagullboi.originofspirits.registry.worldgen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.seagullboi.originofspirits.api.WorldGenUtils;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class TOOSFeatureGeneration {
    public static void generateFeatures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Supplier<ConfiguredFeature<?, ?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

        if (types.contains(BiomeDictionary.Type.CONIFEROUS)) {
            base.add(() -> TOOSConfiguredFeatures.Configs.MAGIC_MAGNOLIA_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.BLUEBERRY_BUSH_PATCH);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_FOREST) || WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_PLAINS)) {
            base.add(() -> TOOSConfiguredFeatures.Configs.BLESSED_ROSE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.VIOLET_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.RED_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.ORANGE_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.PEACE_LILY_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.Configs.SACRED_VIOLET_CONFIG);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.PINK_DESERT)) {
            base.add(() -> TOOSConfiguredFeatures.Configs.CRYSTAL_LILY_CONFIG);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.DECEPTIVE_ISLANDS)) {
            base.add(() -> TOOSConfiguredFeatures.Configs.CURSED_CAVE_GEN);
            base.add(() -> TOOSConfiguredFeatures.Configs.CURSED_LAVA_LAKE);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.ABYSS) || WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.COLORFUL_ABYSS)) {
            base.add(() -> TOOSConfiguredFeatures.Configs.ALCYONEUM_BUSH);
            base.add(() -> TOOSConfiguredFeatures.Configs.BIG_ALCYONEUM);
            base.add(() -> TOOSConfiguredFeatures.Configs.DEEP_ALCYONEUM_BUSH);
            base.add(() -> TOOSConfiguredFeatures.Configs.DEEP_ALCYONEUM_CROWN);
            base.add(() -> TOOSConfiguredFeatures.Configs.DEEP_ALCYONEUM_CUP);
            base.add(() -> TOOSConfiguredFeatures.Configs.ABYSS_ALGAE_PATCH);
            base.add(() -> TOOSConfiguredFeatures.Configs.ABYSS_ALGAE_PATCH_2);

        }

    }
}
