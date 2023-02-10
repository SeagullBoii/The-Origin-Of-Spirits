package net.seagullboi.originofspirits.registry.worldgen;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.world.gen.features.AbyssAlgaePatchFeature;
import net.seagullboi.originofspirits.world.gen.features.AlcyoneumPlantFeature;

public class TOOSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<Feature<NoFeatureConfig>> ALCYONEUM_BUSH = FEATURES.register("alcyoneum_bush", () -> new AlcyoneumPlantFeature(NoFeatureConfig.field_236558_a_, "alcyoneum_bush"));
    public static final RegistryObject<Feature<NoFeatureConfig>> BIG_ALCYONEUM = FEATURES.register("big_alcyoneum", () -> new AlcyoneumPlantFeature(NoFeatureConfig.field_236558_a_, "big_alcyoneum"));
    public static final RegistryObject<Feature<NoFeatureConfig>> DEEP_ALCYONEUM_BUSH = FEATURES.register("deep_alcyoneum_bush", () -> new AlcyoneumPlantFeature(NoFeatureConfig.field_236558_a_, "deep_alcyoneum_bush"));
    public static final RegistryObject<Feature<NoFeatureConfig>> DEEP_ALCYONEUM_CROWN = FEATURES.register("deep_alcyoneum_crown", () -> new AlcyoneumPlantFeature(NoFeatureConfig.field_236558_a_, "deep_alcyoneum_crown"));
    public static final RegistryObject<Feature<NoFeatureConfig>> DEEP_ALCYONEUM_CUP = FEATURES.register("deep_alcyoneum_cup", () -> new AlcyoneumPlantFeature(NoFeatureConfig.field_236558_a_, "deep_alcyoneum_cup"));
    public static final RegistryObject<Feature<NoFeatureConfig>> ABYSS_ALGAE_PATCH = FEATURES.register("abyss_algae_patch", () -> new AbyssAlgaePatchFeature(NoFeatureConfig.field_236558_a_, "abyssalgaepatch"));
    public static final RegistryObject<Feature<NoFeatureConfig>> ABYSS_ALGAE_PATCH_2 = FEATURES.register("abyss_algae_patch2", () -> new AbyssAlgaePatchFeature(NoFeatureConfig.field_236558_a_, "abyssalgaepatch2"));

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}
