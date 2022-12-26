package net.seagullboi.originofspirits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.seagullboi.originofspirits.block.tile_entities.PressingMachineTileEntity;
import net.seagullboi.originofspirits.block.tile_entities.container.PressingMachineContainer;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class PressingMachineBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty PROGRESS = IntegerProperty.create("progress", 0, 8);

    public PressingMachineBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PROGRESS, 0).with(WATERLOGGED, false));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult pHit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
          //  NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());

            worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);

            return ActionResultType.CONSUME;
        }

    }

    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public void tick (BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(tileEntity instanceof PressingMachineTileEntity) {
            PressingMachineTileEntity pressingMachine = (PressingMachineTileEntity) tileEntity;
            if (worldIn.isBlockPowered(pos) && pressingMachine.inputs.contains(pressingMachine.itemHandler.getStackInSlot(0).getItem())) {
                if (pressingMachine.progressTimer <= 0) {
                    pressingMachine.progressIncreasing = true;
                } else if (pressingMachine.progressTimer >= 9) {
                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.5f, 2f);
                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 0.5f, 0.4f);
                    pressingMachine.craft(pos);
                    pressingMachine.progressIncreasing = false;
                    }
                if (pressingMachine.progressIncreasing) {
                    pressingMachine.progressTimer++;
                    worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.2f, 2f);
                }
            }
            if (pressingMachine.progressTimer <= 0) {
                pressingMachine.progressIncreasing = true;
            } else if (pressingMachine.progressTimer >= 10) {
                pressingMachine.craft(pos);
                pressingMachine.progressIncreasing = false;
            }
            if (!pressingMachine.progressIncreasing) {
                pressingMachine.progressTimer--;
                worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.2f, 2f);
            }
            if (pressingMachine.progressTimer <= 8) {
                worldIn.setBlockState(pos, state.with(PROGRESS, pressingMachine.progressTimer));
            } else {
                worldIn.setBlockState(pos, state.with(PROGRESS, 8));
            }

        }
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        double yPressShape = 13 - (state.get(PROGRESS) * 0.5);
        VoxelShape SHAPE = Stream.of(
                Block.makeCuboidShape(0, 0, 0, 16, 9, 16),
                Block.makeCuboidShape(0, 9.01, 0, 2, 16.01, 2),
                Block.makeCuboidShape(14, 9.01, 14, 16, 16.01, 16),
                Block.makeCuboidShape(0, 9.01, 14, 2, 16.01, 16),
                Block.makeCuboidShape(14, 9.01, 0, 16, 16.01, 2),
                Block.makeCuboidShape(1, yPressShape, 1, 15, yPressShape + 3, 15)

                ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

        return SHAPE;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.originofspirits.pressing_machine");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new PressingMachineContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PressingMachineTileEntity();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        boolean flag = context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER;
        return this.getDefaultState().with(WATERLOGGED, flag);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos,
                                          BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PROGRESS, WATERLOGGED);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            this.dropItems(worldIn, state, pos, tileEntity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }

        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }


    public void dropItems(World worldIn, BlockState state, BlockPos pos, TileEntity tileEntity) {
        double randPos = 0.5 + Math.random() / 2;

        if (tileEntity instanceof PressingMachineTileEntity) {
            PressingMachineTileEntity table = (PressingMachineTileEntity) tileEntity;

            if (worldIn instanceof World && !worldIn.isRemote()) {

                for (int count = 0; count < table.itemHandler.getStackInSlot(0).getCount(); count++) {
                    ItemEntity slotOne = new ItemEntity(worldIn, pos.getX() + randPos, pos.getY() + randPos, pos.getZ() + randPos, new ItemStack(table.itemHandler.getStackInSlot(0).getItem()));
                    slotOne.setPickupDelay(10);
                    worldIn.addEntity(slotOne);
                }
            }
        }
    }
}
