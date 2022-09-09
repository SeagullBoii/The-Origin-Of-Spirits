package net.seagullboi.originofspirits.world;

import net.seagullboi.originofspirits.api.WorldGenUtils;
import net.seagullboi.originofspirits.registry.worldgen.ModStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.ABYSS) || WorldGenUtils.getBiomeFromEvent(event, WorldGenUtils.COLORFUL_ABYSS)) {
            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();

            structures.add(() -> ModStructures.SURGEONFISH_SPAWNER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            structures.add(() -> ModStructures.JELLYFISH_SPAWNER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            structures.add(() -> ModStructures.BOX_JELLYFISH_SPAWNER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            structures.add(() -> ModStructures.ELECTRIC_EEL_SPAWNER.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
}
