package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.TallTrumpetCoralTopBlock;
import net.seagullboi.originofspirits.block.TallTrumpetCoralMiddleBlock;
import net.seagullboi.originofspirits.block.TallTrumpetCoralBottomBlock;

import java.util.Map;

public class TallTrumpetCoralBottomNeighbourBlockChangesProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure TallTrumpetCoralBottomNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure TallTrumpetCoralBottomNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure TallTrumpetCoralBottomNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure TallTrumpetCoralBottomNeighbourBlockChanges!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (!(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)).isSolid()
				|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == TallTrumpetCoralBottomBlock.block
				|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == TallTrumpetCoralMiddleBlock.block
				|| (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == TallTrumpetCoralTopBlock.block)) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
		}
	}
}
