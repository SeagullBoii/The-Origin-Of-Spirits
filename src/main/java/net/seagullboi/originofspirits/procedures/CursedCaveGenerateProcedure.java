package net.seagullboi.originofspirits.procedures;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.CursedGeyserBlock;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

import java.util.Map;

public class CursedCaveGenerateProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency world for procedure CursedCaveGenerate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency x for procedure CursedCaveGenerate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency y for procedure CursedCaveGenerate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				OriginOfSpirits.LOGGER.warn("Failed to load dependency z for procedure CursedCaveGenerate!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (Blocks.CAVE_AIR == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			if (net.seagullboi.originofspirits.registry.TOOSBlocks.BLESSED_STONE.get() == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()
					|| TOOSBlocks.DECEPTONE.get() == (world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()) {
				if (0.05 >= Math.random()) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CursedGeyserBlock.block.getDefaultState(), 3);
				} else if (0.1 >= Math.random()) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
				}
				if (Math.random() < 0.5) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), TOOSBlocks.DECEPTONE.get().getDefaultState(), 3);
				}
			}
		}
		if (TOOSBlocks.BLESSED_STONE.get() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()) {
			if (Math.random() < 0.7) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TOOSBlocks.DECEPTONE.get().getDefaultState(), 3);
			}
		}
	}
}
