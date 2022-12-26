
package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSItems;

@OriginofspiritsModElements.ModElement.Tag
public class BeansPlantBlock extends CropsBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
	private static final VoxelShape BUSHLING_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape GROWING_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D);

	public BeansPlantBlock(Properties builder) {
		super(builder);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (state.get(AGE) == 0) {
			return BUSHLING_SHAPE;
		} else {
			return state.get(AGE) < 7 ? GROWING_SHAPE : super.getShape(state, worldIn, pos, context);
		}
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return TOOSItems.BEANS.get();
	}
}
