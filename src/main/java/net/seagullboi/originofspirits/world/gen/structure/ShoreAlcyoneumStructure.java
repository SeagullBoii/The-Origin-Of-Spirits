
package net.seagullboi.originofspirits.world.gen.structure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.World;
import net.minecraft.world.ISeedReader;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Mirror;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.procedures.BigAlcyoneumAdditionalGenerationConditionProcedure;
import net.seagullboi.originofspirits.registry.TOOSBlocks;


import java.util.stream.Stream;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

@Mod.EventBusSubscriber
public class ShoreAlcyoneumStructure {
	private static Feature<NoFeatureConfig> feature = null;
	private static ConfiguredFeature<?, ?> configuredFeature = null;

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class FeatureRegisterHandler {
		@SubscribeEvent
		public static void registerFeature(RegistryEvent.Register<Feature<?>> event) {
			feature = new Feature<NoFeatureConfig>(NoFeatureConfig.field_236558_a_) {
				@Override
				public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
					int ci = (pos.getX() >> 4) << 4;
					int ck = (pos.getZ() >> 4) << 4;
					RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
					boolean dimensionCriteria = dimensionType == World.OVERWORLD;
					if (!dimensionCriteria)
						return false;
					if ((random.nextInt(1000000) + 1) <= 500000) {
						int count = random.nextInt(2) + 1;
						for (int a = 0; a < count; a++) {
							int i = ci + random.nextInt(16);
							int k = ck + random.nextInt(16);
							int j = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
							j -= 1;
							BlockState blockAt = world.getBlockState(new BlockPos(i, j, k));
							boolean blockCriteria = blockAt.getBlock() == net.seagullboi.originofspirits.registry.TOOSBlocks.BLACK_SAND.get();
							if (blockAt.getBlock() == net.seagullboi.originofspirits.registry.TOOSBlocks.WHITE_SAND.get())
								blockCriteria = true;
							if (blockAt.getBlock() == net.seagullboi.originofspirits.registry.TOOSBlocks.ABYSSAL_STONE.get())
								blockCriteria = true;
							if (blockAt.getBlock() == TOOSBlocks.PINK_SAND.get())
								blockCriteria = true;
							if (!blockCriteria)
								continue;
							Rotation rotation = Rotation.values()[random.nextInt(3)];
							Mirror mirror = Mirror.values()[random.nextInt(2)];
							BlockPos spawnTo = new BlockPos(i + 0, j + -4, k + 0);
							int x = spawnTo.getX();
							int y = spawnTo.getY();
							int z = spawnTo.getZ();
							if (!BigAlcyoneumAdditionalGenerationConditionProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("y", y))
									.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll)))
								continue;
							Template template = world.getWorld().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("originofspirits", "shore_alcyoneum"));
							if (template == null)
								return false;
							template.func_237144_a_(world, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
									.addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK).setChunk(null).setIgnoreEntities(false),
									random);
						}
					}
					return true;
				}
			};
			configuredFeature = feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
			event.getRegistry().register(feature.setRegistryName("shore_alcyoneum"));
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("originofspirits:shore_alcyoneum"), configuredFeature);
		}
	}

	@SubscribeEvent
	public static void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = new ResourceLocation("originofspirits:colorful_abyss").equals(event.getName());
		if (!biomeCriteria)
			return;
		event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() -> configuredFeature);
	}
}
