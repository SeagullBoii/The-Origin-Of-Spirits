package net.seagullboi.originofspirits.world.gen;

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

public class TOOSFlowerGeneration {
    public static void generateFlowers(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        List<Supplier<ConfiguredFeature<?, ?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

        if (types.contains(BiomeDictionary.Type.CONIFEROUS)) {
            base.add(() -> TOOSConfiguredFeatures.MAGIC_MAGNOLIA_CONFIG);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_FOREST) || WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.SACRED_PLAINS)) {
            base.add(() -> TOOSConfiguredFeatures.BLESSED_ROSE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.VIOLET_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.RED_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.ORANGE_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.PEACE_LILY_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.SACRED_VIOLET_CONFIG);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.PINK_DESERT)) {
            base.add(() -> TOOSConfiguredFeatures.CRYSTAL_LILY_CONFIG);
        }

        if (WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.DECEPTIVE_ISLANDS)) {
            base.add(() -> TOOSConfiguredFeatures.VIOLET_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.RED_PERIWINKLE_CONFIG);
            base.add(() -> TOOSConfiguredFeatures.ORANGE_PERIWINKLE_CONFIG);
        }
    }
}
