package net.seagullboi.originofspirits.world.biome.overworld;

import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.seagullboi.originofspirits.events.TOOSSoundEvents;

import java.util.function.Supplier;

public class AbyssBiome {
    public static Biome makeAbyss(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(-12887422).setWaterFogColor(-15590378)
                .withSkyColor(7972607).withFoliageColor(10387789).withGrassColor(9470285)
                .setMusic(new BackgroundMusicSelector(TOOSSoundEvents.VOID.get(), 3600, 3600, true))
                .build();

        biomegenerationsettings$builder.withStructure(StructureFeatures.SHIPWRECK);
        biomegenerationsettings$builder.withStructure(StructureFeatures.OCEAN_RUIN_COLD);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new ProbabilityConfig(0.3F)).func_242731_b(3).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withOceanCavesAndCanyons(biomegenerationsettings$builder);

        DefaultBiomeFeatures.withBatsAndHostiles(mobspawninfo$builder);
        DefaultBiomeFeatures.withWarmOceanMobs(mobspawninfo$builder, 5, 1);
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.DROWNED, 5, 1, 1));

        return (new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.OCEAN).depth(-1.99f).scale(0.3f).temperature(0.5f)
                .downfall(0.5f).setEffects(effects).withMobSpawnSettings(mobspawninfo$builder.copy())
                .withGenerationSettings(biomegenerationsettings$builder.build()).build());
    }
}
