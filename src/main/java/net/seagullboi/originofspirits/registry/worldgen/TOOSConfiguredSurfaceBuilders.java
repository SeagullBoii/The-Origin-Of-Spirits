package net.seagullboi.originofspirits.registry.worldgen;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class TOOSConfiguredSurfaceBuilders {

    //Overworld
    public static ConfiguredSurfaceBuilder<?> ABYSS = register("abyss_builder",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    TOOSBlocks.BLACK_SAND.get().getDefaultState(), // Surface Block
                    TOOSBlocks.BLACK_SAND.get().getDefaultState(), // Underground Block
                    TOOSBlocks.BLACK_SAND.get().getDefaultState() // Underwater Block
            )));

    //Sky Realm
    public static ConfiguredSurfaceBuilder<?> SACRED_PLAINS = register("sacred_plains_builder",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    TOOSBlocks.SACRED_GRASS.get().getDefaultState(), // Surface Block
                    TOOSBlocks.SACRED_SOIL.get().getDefaultState(), // Underground Block
                    TOOSBlocks.SACRED_SOIL.get().getDefaultState() // Underwater Block
            )));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(OriginOfSpirits.MOD_ID, name), csb);
    }
}
