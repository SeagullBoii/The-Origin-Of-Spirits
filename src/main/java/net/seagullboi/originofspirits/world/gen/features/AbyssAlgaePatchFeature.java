
package net.seagullboi.originofspirits.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Mirror;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AbyssAlgaePatchFeature extends Feature<NoFeatureConfig> {
	String nbtFile;

	public AbyssAlgaePatchFeature(Codec<NoFeatureConfig> codec, String nbt) {
		super(codec);
		this.nbtFile = nbt;
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
		int centerX = (pos.getX() >> 4) << 4;
		int centerZ = (pos.getZ() >> 4) << 4;
		RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
		boolean dimensionCriteria = dimensionType == World.OVERWORLD;

		if (!dimensionCriteria) {
			return false;
		}

		if ((random.nextInt(1000000) + 1) <= 100000) {
			int count = random.nextInt(2) + 3;
			for (int a = 0; a < count; a++) {
				int x = centerX + random.nextInt(16);
				int z = centerZ + random.nextInt(16);
				int y = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
				y -= 1;
				Rotation rotation = Rotation.values()[random.nextInt(3)];
				Mirror mirror = Mirror.values()[random.nextInt(2)];
				BlockPos spawnTo = new BlockPos(x + 0, y + -4, z + 0);
				BlockState blockAt = world.getBlockState(new BlockPos(x, y, z));
				ArrayList<Block> validBlocks = new ArrayList<>(Arrays.asList(TOOSBlocks.ABYSSAL_STONE.get(), TOOSBlocks.PINK_SAND.get(), TOOSBlocks.WHITE_SAND.get(), TOOSBlocks.BLACK_SAND.get()));
				boolean blockCriteria = validBlocks.contains(blockAt.getBlock());

				if (!blockCriteria && y < 55) {
					continue;
				}

				Template template = world.getWorld().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation(OriginOfSpirits.MOD_ID, nbtFile));
				if (template == null) {
					return false;
				}
				template.func_237144_a_(world, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK).setChunk(null).setIgnoreEntities(false), random);
			}
		}
		return true;
	}
}
