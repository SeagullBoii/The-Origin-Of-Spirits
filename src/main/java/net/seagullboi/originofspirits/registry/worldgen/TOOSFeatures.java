package net.seagullboi.originofspirits.registry.worldgen;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, OriginOfSpirits.MOD_ID);

   // public static final RegistryObject<Feature<NoFeatureConfig>> TEST_FEATURE = FEATURES.register("test_feature", () -> new TestFeature(NoFeatureConfig.field_236558_a_));

    public static void register(IEventBus bus) {
        FEATURES.register(bus);
    }
}
