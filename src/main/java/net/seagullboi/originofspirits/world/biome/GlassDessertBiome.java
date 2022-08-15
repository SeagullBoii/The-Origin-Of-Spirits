
package net.seagullboi.originofspirits.world.biome;

import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

@OriginofspiritsModElements.ModElement.Tag
public class GlassDessertBiome extends OriginofspiritsModElements.ModElement {
	public static Biome biome;

	public GlassDessertBiome(OriginofspiritsModElements instance) {
		super(instance, 154);
		FMLJavaModLoadingContext.get().getModEventBus().register(new BiomeRegisterHandler());
	}

	private static class BiomeRegisterHandler {
		@SubscribeEvent
		public void registerBiomes(RegistryEvent.Register<Biome> event) {
			if (biome == null) {
				BiomeAmbience effects = new BiomeAmbience.Builder().setFogColor(12638463).setWaterColor(-6696449).setWaterFogColor(-6696449)
						.withSkyColor(7972607).withFoliageColor(-6684724).withGrassColor(-6684717).build();
				BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
						.withSurfaceBuilder(SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(net.seagullboi.originofspirits.registry.TOOSBlocks.PINK_SAND.get().getDefaultState(),
								TOOSBlocks.PINK_SAND.get().getDefaultState(), net.seagullboi.originofspirits.registry.TOOSBlocks.PINK_SAND.get().getDefaultState())));
				DefaultBiomeFeatures.withCavesAndCanyons(biomeGenerationSettings);
				DefaultBiomeFeatures.withOverworldOres(biomeGenerationSettings);
				DefaultBiomeFeatures.withFrozenTopLayer(biomeGenerationSettings);
				MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().isValidSpawnBiomeForPlayer();
				biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).category(Biome.Category.DESERT).depth(0f).scale(2f).temperature(0.5f)
						.downfall(0.5f).setEffects(effects).withMobSpawnSettings(mobSpawnInfo.copy())
						.withGenerationSettings(biomeGenerationSettings.build()).build();
				event.getRegistry().register(biome.setRegistryName("originofspirits:glass_dessert"));
			}
		}
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}
}
