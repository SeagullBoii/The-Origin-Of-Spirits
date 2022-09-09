
package net.seagullboi.originofspirits.world.gen.structure;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlueberryStructureStructure {
	/*	private static Feature<NoFeatureConfig> feature = null;
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
					if ((random.nextInt(1000000) + 1) <= 100231) {
						int count = random.nextInt(8) + 6;
						for (int a = 0; a < count; a++) {
							int i = ci + random.nextInt(16);
							int k = ck + random.nextInt(16);
							int j = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
							j -= 1;
							BlockState blockAt = world.getBlockState(new BlockPos(i, j, k));
							boolean blockCriteria = blockAt.getBlock() == Blocks.GRASS_BLOCK;
							if (blockAt.getBlock() == Blocks.GRASS_PATH)
								blockCriteria = true;
							if (blockAt.getBlock() == Blocks.MYCELIUM)
								blockCriteria = true;
							if (blockAt.getBlock() == Blocks.DIRT)
								blockCriteria = true;
							if (blockAt.getBlock() == Blocks.COARSE_DIRT)
								blockCriteria = true;
							if (blockAt.getBlock() == Blocks.PODZOL)
								blockCriteria = true;
							if (!blockCriteria)
								continue;
							Rotation rotation = Rotation.values()[random.nextInt(3)];
							Mirror mirror = Mirror.values()[random.nextInt(2)];
							BlockPos spawnTo = new BlockPos(i + 0, j + 1, k + 0);
							int x = spawnTo.getX();
							int y = spawnTo.getY();
							int z = spawnTo.getZ();
							Template template = world.getWorld().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("originofspirits", "blueberrybush"));
							if (template == null)
								return false;
							template.func_237144_a_(world, spawnTo,
									new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
											.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK).setChunk(null).setIgnoreEntities(false),
									random);
						}
					}
					return true;
				}
			};
			configuredFeature = feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));
			event.getRegistry().register(feature.setRegistryName("blueberry_structure"));
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("originofspirits:blueberry_structure"), configuredFeature);
		}
	}

	@SubscribeEvent
	public static void addFeatureToBiomes(BiomeLoadingEvent event) {
		boolean biomeCriteria = new ResourceLocation("giant_spruce_taiga").equals(event.getName());
		if (new ResourceLocation("giant_spruce_taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("giant_spruce_taiga").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("giant_spruce_taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("giant_tree_taiga").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("giant_tree_taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("snowy_taiga_mountains").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("taiga").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("taiga_hills").equals(event.getName()))
			biomeCriteria = true;
		if (new ResourceLocation("taiga_mountains").equals(event.getName()))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;
		event.getGeneration().getFeatures(GenerationStage.Decoration.SURFACE_STRUCTURES).add(() -> configuredFeature);
	}*/
}
