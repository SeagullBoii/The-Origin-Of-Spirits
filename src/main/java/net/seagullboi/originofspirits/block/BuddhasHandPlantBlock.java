
package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.Random;

	public class BuddhasHandPlantBlock extends Block {
		public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;
		private static final VoxelShape SMALL_SHAPE = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
		private static final VoxelShape MID_SHAPE = Block.makeCuboidShape(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
		private static final VoxelShape BIG_SHAPE = Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 16.0D, 16.0D);

		public BuddhasHandPlantBlock(Properties properties) {
			super(properties);
			this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
		}

		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(AGE);
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 0;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			if (state.get(AGE) == 0) {
				return SMALL_SHAPE;
			} else if (state.get(AGE) == 1) {
				return MID_SHAPE;
			} else {
				return state.get(AGE) < 3 ? BIG_SHAPE : super.getShape(state, world, pos, context);
			}
		}

		@Override
		public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
			return worldIn.getBlockState(pos.up()).getMaterial() == Material.LEAVES;
		}

		@Override
		public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos,
				BlockPos facingPos) {
			return !state.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		}

		@Override
		public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
			super.tick(state, worldIn, pos, random);
			if(state.get(AGE) < 3) {
				worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
			}
		}

		@Override
		public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult hit) {
			super.onBlockActivated(state, worldIn, pos, entity, hand, hit);
			if (state.get(AGE) == 3) {
				ItemEntity fruit = new ItemEntity(worldIn, (pos.getX() + 0.5), (pos.getY() + 0.5), (pos.getZ() + 0.5), new ItemStack(TOOSItems.BUDDHAS_HAND.get()));
				fruit.setPickupDelay(10);
				worldIn.addEntity(fruit);
				worldIn.setBlockState(pos, state.with(AGE, 0));
			}
			return ActionResultType.SUCCESS;
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(TOOSItems.BUDDHAS_HAND.get());
		}
	}
