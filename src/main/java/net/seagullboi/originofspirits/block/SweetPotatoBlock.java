package net.seagullboi.originofspirits.block;

import net.seagullboi.originofspirits.registry.TOOSItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PotatoBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class SweetPotatoBlock extends PotatoBlock {

    private static final VoxelShape SHAPE1 = Block.makeCuboidShape(0, 0, 0 , 16, 4, 16);
    private static final VoxelShape SHAPE2 = Block.makeCuboidShape(0, 0, 0 , 16, 3, 16);
    private static final VoxelShape SHAPE3 = Block.makeCuboidShape(0, 0, 0 , 16, 10, 16);
    private static final VoxelShape SHAPE4 = Block.makeCuboidShape(0, 0, 0 , 16, 11, 16);


    public SweetPotatoBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return TOOSItems.SWEET_POTATO.get();
    }

    @Override
    public boolean isValidPosition(BlockState blockstate, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(AGE) == 0 || state.get(AGE) == 1) {
            return SHAPE1;
        } else if (state.get(AGE) == 2 || state.get(AGE) == 3) {
            return SHAPE2;
        } else if (state.get(AGE) >= 3 || state.get(AGE) <= 4) {
            return SHAPE3;
        }
        return SHAPE4;
    }
}
