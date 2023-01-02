package net.seagullboi.originofspirits.world.biome.sky;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;

import java.util.function.Supplier;

public class SacredPlainsBiome {
    public static Biome makeSacredPlains(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);
        BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(-6696449).setWaterFogColor(-6696449).withSkyColor(7972607).withFoliageColor(-6684724).withGrassColor(-6684724).build();
        MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();

        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withClayDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationsettings$builder);

        return (new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.1f).scale(0f).temperature(0.5f)
                .downfall(0.5f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
                .withGenerationSettings(biomegenerationsettings$builder.build()).build());
    }
}
