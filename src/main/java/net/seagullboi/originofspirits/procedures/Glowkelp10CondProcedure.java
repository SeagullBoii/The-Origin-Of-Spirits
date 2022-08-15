package net.seagullboi.originofspirits.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.seagullboi.originofspirits.OriginOfSpirits;

import java.util.Map;

public class Glowkelp10CondProcedure {

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure Glowkelp10Cond!");
			return false;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure Glowkelp10Cond!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure Glowkelp10Cond!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure Glowkelp10Cond!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		return (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 3), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 4), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 5), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 6), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 7), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 8), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 9), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 10), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 11), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 12), (int) z))).getFluidState().isSource()
				&& (world.getBlockState(new BlockPos((int) x, (int) (y + 14), (int) z))).getFluidState().isSource();
	}
}
