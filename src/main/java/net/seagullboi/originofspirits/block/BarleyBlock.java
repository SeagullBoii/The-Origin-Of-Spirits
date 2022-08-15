
package net.seagullboi.originofspirits.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsMiscItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@OriginofspiritsModElements.ModElement.Tag
public class BarleyBlock extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:barley")
	public static final Block block = null;

	public BarleyBlock(OriginofspiritsModElements instance) {
		super(instance, 719);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	public static class CustomBlock extends BushBlock implements IGrowable {
		public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
		private static final VoxelShape AGESHAPE_0 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
		private static final VoxelShape AGESHAPE_1 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
		private static final VoxelShape AGESHAPE_2 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
		private static final VoxelShape AGESHAPE_3 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
		private static final VoxelShape AGESHAPE_4 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);


		public CustomBlock() {
			super(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0f, 10f).setLightLevel(s -> 0).notSolid()
					.doesNotBlockMovement().setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
			setRegistryName("barley");
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
			if (state.get(AGE) == 0 || state.get(AGE) == 1) {
				return AGESHAPE_0;
			} else if(state.get(AGE) == 2 || state.get(AGE) == 3) {
				return AGESHAPE_1;
			} else if(state.get(AGE) == 4 || state.get(AGE) == 5) {
				return AGESHAPE_2;
			} else if(state.get(AGE) == 6) {
				return AGESHAPE_3;
			} else return AGESHAPE_4;
		}

		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(AGE);
		}

		public boolean ticksRandomly(BlockState state) {
			return state.get(AGE) < 7;
		}

		public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
			int i = state.get(AGE);
			if (i < 7 && worldIn.getLightSubtracted(pos.up(), 0) >= 9
					&& net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
				worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1)), 2);
				net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}

		public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
												 BlockRayTraceResult hit) {
			int i = state.get(AGE);
			boolean flag = i == 7;
			if (!flag && player.getHeldItem(handIn).getItem() == Items.BONE_MEAL) {
				return ActionResultType.PASS;
			} else {
				return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
			}
		}
		public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
			return state.get(AGE) < 7;
		}

		@Override
		public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
			return true;
		}

		@Override
		public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
			int i = Math.min(7, state.get(AGE) + 1);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
		}
		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
		@Override
		public boolean isValidPosition(BlockState blockstate, IWorldReader world, BlockPos pos) {
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			return (world.getBlockState(new BlockPos(x, y - 1, z))).getBlock() == Blocks.FARMLAND;
		}
	}
}
