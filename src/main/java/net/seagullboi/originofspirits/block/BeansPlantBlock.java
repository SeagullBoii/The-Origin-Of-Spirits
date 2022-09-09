
package net.seagullboi.originofspirits.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.item.BeansItem;

import java.util.Random;

@OriginofspiritsModElements.ModElement.Tag
public class BeansPlantBlock extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:beans_plant")
	public static final Block block = null;

	public BeansPlantBlock(OriginofspiritsModElements instance) {
		super(instance, 349);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	public static class CustomBlock extends BushBlock implements IGrowable {
		public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
		private static final VoxelShape BUSHLING_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
		private static final VoxelShape GROWING_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 10.0D, 15.0D);

		public CustomBlock() {
			super(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH));
			this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
			setRegistryName("beans_plant");
		}

		public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
			return new ItemStack(BeansItem.block);
		}

		public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
			if (state.get(AGE) == 0) {
				return BUSHLING_SHAPE;
			} else {
				return state.get(AGE) < 7 ? GROWING_SHAPE : super.getShape(state, worldIn, pos, context);
			}
		}

		/**
		 * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by
		 * ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
		 */
		public boolean ticksRandomly(BlockState state) {
			return state.get(AGE) < 7;
		}

		/**
		 * Performs a random tick on a block.
		 */
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

		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(AGE);
		}

		/**
		 * Whether this IGrowable can grow
		 */
		public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
			return state.get(AGE) < 7;
		}

		public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
			return true;
		}

		public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
			int i = Math.min(7, state.get(AGE) + 1);
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i)), 2);
		}
	}
}
