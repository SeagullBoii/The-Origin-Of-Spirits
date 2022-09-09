
package net.seagullboi.originofspirits.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;
import net.seagullboi.originofspirits.world.dimension.TheSkyRealmsDimension;
public class CursedFireBlock extends Block implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public CursedFireBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Vector3d offset = state.getOffset(world, pos);
		return VoxelShapes.or(makeCuboidShape(0, 0, 0, 16, 0.5, 16)).withOffset(offset.x, offset.y, offset.z);
	}

	@Override
	public boolean isValidPosition(BlockState blockstate, IWorldReader worldIn, BlockPos pos) {
		if (worldIn instanceof IWorld) {
			IWorld world = (IWorld) worldIn;
			return world.getBlockState(pos.down()).isSolidSide(world, pos.down(), Direction.UP);
		}
		return super.isValidPosition(blockstate, worldIn, pos);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;
		return this.getDefaultState().with(WATERLOGGED, flag);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos,
										  BlockPos facingPos) {
		if (state.get(WATERLOGGED)) {
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return !state.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
		return context.getItem().getItem() != this.asItem();
	}

	@Override
	public void onBlockAdded(BlockState blockstate, World world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onBlockAdded(blockstate, world, pos, oldState, moving);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (world instanceof World) {
			TheSkyRealmsDimension.portal.portalSpawn(world, pos.up());
		}
	}

	@Override
	public void onEntityCollision(BlockState blockstate, World world, BlockPos pos, Entity entity) {
		super.onEntityCollision(blockstate, world, pos, entity);
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).addPotionEffect(new EffectInstance(CursedPotionEffect.potion, 20, 0, false, true));
		}
		entity.setFire(3);
	}
}
