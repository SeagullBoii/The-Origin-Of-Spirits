package net.seagullboi.originofspirits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.seagullboi.originofspirits.util.GlobalVarUtil;

import java.util.Random;
import java.util.stream.Stream;

public class SpitePedestalBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SpitePedestalBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity entityIn, Hand hand, BlockRayTraceResult rayTraceResult) {
        sendMessage(worldIn, pos.getX(), pos.getY(), pos.getZ(), entityIn);
        return ActionResultType.SUCCESS;
    }

    public void sendMessage(World worldIn, double x, double y, double z, PlayerEntity entityIn) {
        if (worldIn instanceof ServerWorld) {
            String disableMessage = "tellraw @a {\"text\":\"Couldn't Handle The Heat?\",\"color\":\"dark_red\"}";
            String enableMessage = "tellraw @a {\"text\":\"Spite Is Enabled.\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[{\"text\":\"Have Fun >:)\",\"color\":\"dark_red\"}]}}";
            String dragonGrowl = "playsound minecraft:entity.ender_dragon.growl block @a ~ ~ ~ 1 0.5";
            String lightningStrike = "playsound minecraft:entity.lightning_bolt.thunder block @a ~ ~ ~ 1 0.5";
            CommandSource commandSource = new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) worldIn, 4, "", new StringTextComponent(""), worldIn.getServer(), null);


            if (GlobalVarUtil.getSpite(worldIn)) {
                if (worldIn instanceof  ServerWorld) {
                    worldIn.getServer().getCommandManager().handleCommand(commandSource.withFeedbackDisabled(), disableMessage);
                }
                GlobalVarUtil.setSpite(worldIn, !GlobalVarUtil.getSpite(worldIn));
            } else {
                worldIn.getServer().getCommandManager().handleCommand(commandSource.withFeedbackDisabled(), enableMessage);
                GlobalVarUtil.setSpite(worldIn, !GlobalVarUtil.getSpite(worldIn));
                if (worldIn instanceof ServerWorld) {
                    worldIn.getServer().getCommandManager().handleCommand(commandSource.withFeedbackDisabled(), dragonGrowl);
                    worldIn.getServer().getCommandManager().handleCommand(commandSource.withFeedbackDisabled(), lightningStrike);
                }

            }


            System.out.println(GlobalVarUtil.getSpite(worldIn));
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHAPE = Stream.of(
                Block.makeCuboidShape(4, 3, 4, 12, 7, 12),
                Block.makeCuboidShape(2, 7, 2, 14, 16, 14),
                Block.makeCuboidShape(0, 0, 0, 16, 3, 16)

        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

        return SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState blockstate, World world, BlockPos pos, Random random) {
        super.animateTick(blockstate, world, pos, random);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (GlobalVarUtil.getSpite(world)) {
            for (int l = 0; l < 1; l++) {
                double d0 = (x + random.nextFloat());
                double d1 = (y + random.nextFloat());
                double d2 = (z + random.nextFloat());
                double d3 = (random.nextFloat() - 0.5D) * 0.5D;
                double d4 = (random.nextFloat() - 0.5D) * 0.5D;
                double d5 = (random.nextFloat() - 0.5D) * 0.5D;
                world.addParticle(ParticleTypes.DAMAGE_INDICATOR, d0, d1, d2, d3, d4, d5);
            }
        }
    }
}
