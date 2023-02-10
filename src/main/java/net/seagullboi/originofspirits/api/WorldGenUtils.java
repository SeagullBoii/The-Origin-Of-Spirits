package net.seagullboi.originofspirits.api;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;

/**
 * @credit Bioplethora - [missing link]
 */

public class WorldGenUtils {
    public static final String SACRED_PLAINS = getCustomBiome("sacred_plains");
    public static final String SACRED_FOREST = getCustomBiome("sacred_forest");
    public static final String PINK_DESERT = getCustomBiome("pink_desert");
    public static final String HOLY_MARSH = getCustomBiome("holy_marsh");
    public static final String DECEPTIVE_ISLANDS = getCustomBiome("deceptive_islands");
    public static final String ABYSS = getCustomBiome("abyss");
    public static final String COLORFUL_ABYSS = getCustomBiome("colorful_abyss");
    public static final String DARK_OAK_FOREST = getCustomBiome("dark_forest");
    public static final String WARM_OCEAN = getVanillaBiome("warm_ocean");
    public static final String DEEP_WARM_OCEAN = getVanillaBiome("deep_warm_ocean");
    public static final String SWAMP = getVanillaBiome("swamp");

    public static String getVanillaBiome(String biomeId) {
        return "minecraft:" + biomeId;
    }

    public static String getCustomBiome(String biomeId) {
        return OriginOfSpirits.MOD_ID + ":" + biomeId;
    }

    public static String getCustomDimension(String biomeId) {
        return OriginOfSpirits.MOD_ID + ":" + biomeId;
    }

    public static boolean getBiomeFromEvent(BiomeLoadingEvent event, String biome) {
        return new ResourceLocation(biome).equals(event.getName());
    }

    public static boolean getBiomeFromEvent(BiomeLoadingEvent event, RegistryObject<Biome> biome) {
        return new ResourceLocation(biome.getId().toString()).equals(event.getName());
    }

    public static boolean isSkyRealm(BiomeLoadingEvent event) {
        return WorldGenUtils.getBiomeFromEvent(event, SACRED_FOREST) || WorldGenUtils.getBiomeFromEvent(event, SACRED_FOREST) || WorldGenUtils.getBiomeFromEvent(event, DECEPTIVE_ISLANDS)
                || WorldGenUtils.getBiomeFromEvent(event, PINK_DESERT) || getBiomeFromEvent(event, HOLY_MARSH);
    }

}
