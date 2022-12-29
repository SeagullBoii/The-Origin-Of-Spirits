package net.seagullboi.originofspirits.world.gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.registry.worldgen.TOOSBiomes;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.OCEAN;
import static net.minecraftforge.common.BiomeDictionary.Type.WATER;

public class TOOSBiomeEvent {
    public static void generateBiomes() {
        addBiome(TOOSBiomes.ABYSS.get(), BiomeManager.BiomeType.COOL, 1, OCEAN, WATER);
        addBiome(TOOSBiomes.COLORFUL_ABYSS.get(), BiomeManager.BiomeType.COOL, 1, OCEAN, WATER);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));
        BiomeDictionary.addTypes(key, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
    }

    public static void registerBiomeDictionaries() {
        BiomeDictionary.addTypes(RegistryKey.getOrCreateKey(Registry.BIOME_KEY, WorldGenRegistries.BIOME.getKey(TOOSBiomes.SACRED_PLAINS.get())), BiomeDictionary.Type.PLAINS);
    }
}
