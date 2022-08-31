package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.seagullboi.originofspirits.registry.TOOSItems;

public class BarleyBlock extends CropsBlock {

	private static final VoxelShape AGESHAPE_0 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
	private static final VoxelShape AGESHAPE_1 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
	private static final VoxelShape AGESHAPE_2 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
	private static final VoxelShape AGESHAPE_3 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
	private static final VoxelShape AGESHAPE_4 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public BarleyBlock(Properties builder) {
		super(builder);
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return TOOSItems.BARLEY_SEEDS.get();
	}

	@Override
	public boolean isValidPosition(BlockState blockstate, IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		if (state.get(AGE) <= 1) {
			return AGESHAPE_0;
		} else if(state.get(AGE) <= 3) {
			return AGESHAPE_1;
		} else if(state.get(AGE) <= 5) {
			return AGESHAPE_2;
		} else if(state.get(AGE) == 6) {
			return AGESHAPE_3;
		} else {
			return AGESHAPE_4;
		}
	}
}
