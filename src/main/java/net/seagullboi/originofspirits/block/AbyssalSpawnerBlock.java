package net.seagullboi.originofspirits.block;

import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.seagullboi.originofspirits.entity.BoxJellyfishEntity;
import net.seagullboi.originofspirits.entity.ElectricEelEntity;
import net.seagullboi.originofspirits.entity.ElectricSurgeonfishEntity;
import net.seagullboi.originofspirits.entity.JellyfishEntity;
import net.seagullboi.originofspirits.particle.ImmovablePearlSparkParticle;
import net.seagullboi.originofspirits.util.AbyssalSpawnerEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class AbyssalSpawnerBlock extends Block implements IWaterLoggable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty ENTITY_SPAWN = EnumProperty.create("entity", AbyssalSpawnerEntities.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public int spawnTimer = 0;

    public AbyssalSpawnerBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(ENTITY_SPAWN, AbyssalSpawnerEntities.NULL).with(ACTIVE, Boolean.valueOf(false)).with(WATERLOGGED, true));
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean moving) {
        super.onBlockAdded(state, worldIn, pos, oldState, moving);
        if (state.get(ENTITY_SPAWN) == AbyssalSpawnerEntities.NULL) {
            if (Math.random() < 0.25) {
                worldIn.setBlockState(pos, state.with(ENTITY_SPAWN, AbyssalSpawnerEntities.ELECTRIC_EEL), 3);
            } else if (Math.random() < 0.5) {
                worldIn.setBlockState(pos, state.with(ENTITY_SPAWN, AbyssalSpawnerEntities.ELECTRIC_SURGEONFISH), 3);
            } else if (Math.random() < 0.75) {
                worldIn.setBlockState(pos, state.with(ENTITY_SPAWN, AbyssalSpawnerEntities.JELLYFISH), 3);
            } else {
                worldIn.setBlockState(pos, state.with(ENTITY_SPAWN, AbyssalSpawnerEntities.BOX_JELLYFISH), 3);
            }

        }

        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1);

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity entity, Hand hand,
                                             BlockRayTraceResult hit) {
        super.onBlockActivated(state, worldIn, pos, entity, hand, hit);

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        if (OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing) {
            worldIn.setBlockState(pos, state.with(ACTIVE, true), 3);
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, 20);
        } else if(state.get(ACTIVE) && !OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing) {
            worldIn.setBlockState(pos, state.with(ACTIVE, false), 3);
        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.tick(state, worldIn, pos, random);

        if (OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing) {
            worldIn.setBlockState(pos, state.with(ACTIVE, true), 3);
        } else {
            worldIn.setBlockState(pos, state.with(ACTIVE, false), 3);
        }

        if (state.get(ACTIVE)) {
            this.setSpawnTimer(this.getSpawnTimer() + 1);
        }

        if (this.getSpawnTimer() >= 60) {

            BlockPos pos1 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
            BlockPos pos2 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
            BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
            BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);

            Entity electricEel = new ElectricEelEntity.CustomEntity(ElectricEelEntity.entity, worldIn);
            Entity electricSurgeonfish = new ElectricSurgeonfishEntity.CustomEntity(ElectricSurgeonfishEntity.entity, worldIn);
            Entity jellyfish = new JellyfishEntity.CustomEntity(JellyfishEntity.entity, worldIn);
            Entity boxJellyfish = new BoxJellyfishEntity.CustomEntity(BoxJellyfishEntity.entity, worldIn);

            if (state.get(ENTITY_SPAWN) == AbyssalSpawnerEntities.ELECTRIC_EEL) {
                if (Math.random() < 0.16 && worldIn.getFluidState(pos.up()).isSource()) {
                    electricEel.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.32 && worldIn.getFluidState(pos.down()).isSource()) {
                    electricEel.setLocationAndAngles(pos.getX(), pos.getY() - 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.48 && worldIn.getFluidState(pos1).isSource()) {
                    electricEel.setLocationAndAngles(pos.getX() + 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.64 && worldIn.getFluidState(pos2).isSource()) {
                    electricEel.setLocationAndAngles(pos.getX() - 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.80 && worldIn.getFluidState(pos3).isSource()) {
                    electricEel.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() + 1, worldIn.getRandom().nextFloat() * 360F, 0);
                } else {
                    if (worldIn.getFluidState(pos4).isSource())
                    electricEel.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() - 1, worldIn.getRandom().nextFloat() * 360F, 0);
                }

                worldIn.addEntity(electricEel);

            } else if (state.get(ENTITY_SPAWN) == AbyssalSpawnerEntities.ELECTRIC_SURGEONFISH) {
                if (Math.random() < 0.16 && worldIn.getFluidState(pos.up()).isSource()) {
                    electricSurgeonfish.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.32 && worldIn.getFluidState(pos.down()).isSource()) {
                    electricSurgeonfish.setLocationAndAngles(pos.getX(), pos.getY() - 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.48 && worldIn.getFluidState(pos1).isSource()) {
                    electricSurgeonfish.setLocationAndAngles(pos.getX() + 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.64 && worldIn.getFluidState(pos2).isSource()) {
                    electricSurgeonfish.setLocationAndAngles(pos.getX() - 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.80 && worldIn.getFluidState(pos3).isSource()) {
                    electricSurgeonfish.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() + 1, worldIn.getRandom().nextFloat() * 360F, 0);
                } else {
                    if (worldIn.getFluidState(pos4).isSource())
                        electricSurgeonfish.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() - 1, worldIn.getRandom().nextFloat() * 360F, 0);
                }

                worldIn.addEntity(electricSurgeonfish);

            } else if (state.get(ENTITY_SPAWN) == AbyssalSpawnerEntities.JELLYFISH) {
                if (Math.random() < 0.16 && worldIn.getFluidState(pos.up()).isSource()) {
                    jellyfish.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.32 && worldIn.getFluidState(pos.down()).isSource()) {
                    jellyfish.setLocationAndAngles(pos.getX(), pos.getY() - 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.48 && worldIn.getFluidState(pos1).isSource()) {
                    jellyfish.setLocationAndAngles(pos.getX() + 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.64 && worldIn.getFluidState(pos2).isSource()) {
                    jellyfish.setLocationAndAngles(pos.getX() - 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.80 && worldIn.getFluidState(pos3).isSource()) {
                    jellyfish.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() + 1, worldIn.getRandom().nextFloat() * 360F, 0);
                } else {
                    if (worldIn.getFluidState(pos4).isSource())
                        jellyfish.setLocationAndAngles(pos.getX() , pos.getY(), pos.getZ() - 1, worldIn.getRandom().nextFloat() * 360F, 0);
                }

                worldIn.addEntity(jellyfish);

            } else if (state.get(ENTITY_SPAWN) == AbyssalSpawnerEntities.BOX_JELLYFISH) {
                if (Math.random() < 0.16 && worldIn.getFluidState(pos.up()).isSource()) {
                    boxJellyfish.setLocationAndAngles(pos.getX(), pos.getY() + 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.32 && worldIn.getFluidState(pos.down()).isSource()) {
                    boxJellyfish.setLocationAndAngles(pos.getX(), pos.getY() - 1, pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.48 && worldIn.getFluidState(pos1).isSource()) {
                    boxJellyfish.setLocationAndAngles(pos.getX() + 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.64 && worldIn.getFluidState(pos2).isSource()) {
                    boxJellyfish.setLocationAndAngles(pos.getX() - 1, pos.getY(), pos.getZ(), worldIn.getRandom().nextFloat() * 360F, 0);
                } else  if (Math.random() < 0.80 && worldIn.getFluidState(pos3).isSource()) {
                    boxJellyfish.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() + 1, worldIn.getRandom().nextFloat() * 360F, 0);
                } else {
                    if (worldIn.getFluidState(pos4).isSource())
                        boxJellyfish.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() - 1, worldIn.getRandom().nextFloat() * 360F, 0);
                }

                worldIn.addEntity(boxJellyfish);

            }
            this.setSpawnTimer(0);
        }

        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 20);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState blockstate, World world, BlockPos pos, Random random) {
        super.animateTick(blockstate, world, pos, random);
        PlayerEntity entity = Minecraft.getInstance().player;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (blockstate.get(ACTIVE)) {
                for (int l = 0; l < 4; ++l) {
                    double d0 = (x + random.nextFloat());
                    double d1 = (y + random.nextFloat());
                    double d2 = (z + random.nextFloat());
                    double d3 = (random.nextFloat() - 0.5D) * 0.5D;
                    double d4 = (random.nextFloat() - 0.5D) * 0.5D;
                    double d5 = (random.nextFloat() - 0.5D) * 0.5D;
                    world.addParticle(ImmovablePearlSparkParticle.particle, d0, d1, d2, d3, d4, d5);
                }
        }
    }

    public int getSpawnTimer() {
        return spawnTimer;
    }

    public void setSpawnTimer(int spawnTimer) {
        this.spawnTimer = spawnTimer;
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
        builder.add(ACTIVE, ENTITY_SPAWN, WATERLOGGED);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, IBlockDisplayReader world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.BLOCK;
    }

}
