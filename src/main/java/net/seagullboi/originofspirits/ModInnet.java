
package net.seagullboi.originofspirits;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.world.biome.GlassDessertBiome;
import net.seagullboi.originofspirits.block.PinkSandstoneBlock;

@Mod.EventBusSubscriber(modid = "originofspirits", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModInnet {
	public ModInnet() {
	}

	public static SurfaceBuilder<SurfaceBuilderConfig> DESERT_SURFACE_BUILDER;

	@SubscribeEvent
	public static void RegistrySurfaceBuilder(RegistryEvent.Register<SurfaceBuilder<?>> event) {
		// Desert Builder
		DESERT_SURFACE_BUILDER = new DesertSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);
		DESERT_SURFACE_BUILDER.setRegistryName("originofspirits", "desert_surface_builder");
		event.getRegistry().register(DESERT_SURFACE_BUILDER);
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		new ModInnet();
	}

	@Mod.EventBusSubscriber
	private static class ForgeBusEvents {
		@SubscribeEvent
		public static void addFeatureToBiomes(BiomeLoadingEvent event) {
			if (event.getName().getPath().equals(GlassDessertBiome.biome.getRegistryName().getPath())) {
				event.getGeneration().withSurfaceBuilder(ModInnet.DESERT_SURFACE_BUILDER.func_242929_a(new SurfaceBuilderConfig(
						TOOSBlocks.PINK_SAND.get().getDefaultState(), net.seagullboi.originofspirits.registry.TOOSBlocks.PINK_SAND.get().getDefaultState(), PinkSandstoneBlock.block.getDefaultState())));
			}
		}

		@SubscribeEvent
		public static void serverLoad(FMLServerStartingEvent event) {
		}

		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public static void clientLoad(FMLClientSetupEvent event) {
		}
	}
}
