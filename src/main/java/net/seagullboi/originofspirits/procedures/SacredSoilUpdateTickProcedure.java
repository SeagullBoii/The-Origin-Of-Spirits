package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.seagullboi.originofspirits.block.SacredGrassBlockBlock;
import net.seagullboi.originofspirits.block.LushSacredGrassBlockBlock;
import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class SacredSoilUpdateTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure SacredSoilUpdateTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure SacredSoilUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure SacredSoilUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure SacredSoilUpdateTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))
				&& ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == SacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == SacredGrassBlockBlock.block)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SacredGrassBlockBlock.block.getDefaultState(), 3);
		} else if (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))
				&& ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == LushSacredGrassBlockBlock.block
						|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1))))
								.getBlock() == LushSacredGrassBlockBlock.block)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LushSacredGrassBlockBlock.block.getDefaultState(), 3);
		}
	}
}
