package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class TOOSGrassBlock extends Block {

    public Block dirt;

    public TOOSGrassBlock(Properties properties, Block dirtBlock) {
        super(properties);
        this.dirt = dirtBlock;
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!isGrassCondition(state, worldIn, pos) || !isNotUnderwater(worldIn, pos)) {
            if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            worldIn.setBlockState(pos, dirt.getDefaultState());
        } else {
            if (worldIn.getLight(pos.up()) >= 9) {
                BlockState blockstate = this.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).isIn(dirt) && isNotUnderwater(worldIn, blockpos) && !worldIn.getBlockState(blockpos.up()).isSolid()) {
                        worldIn.setBlockState(blockpos, blockstate);
                    }
                }
            }

        }
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = this.getDefaultState();

        label48:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.down()).isIn(this) || worldIn.getBlockState(blockpos1).hasOpaqueCollisionShape(worldIn, blockpos1)) {
                    continue label48;
                }
            }

            BlockState blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.isIn(blockstate.getBlock()) && rand.nextInt(10) == 0) {
                ((IGrowable)blockstate.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
            }

            if (blockstate2.isAir()) {
                BlockState blockstate1;
                if (rand.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                    FlowersFeature flowersfeature = (FlowersFeature)configuredfeature.feature;
                    blockstate1 = flowersfeature.getFlowerToPlace(rand, blockpos1, configuredfeature.getConfig());
                } else {
                    blockstate1 = blockstate;
                }

                if (blockstate1.isValidPosition(worldIn, blockpos1)) {
                    worldIn.setBlockState(blockpos1, blockstate1, 3);
                }
            }
        }

    }

    private static boolean isGrassCondition(BlockState state, IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        if (!state.isSolid()) {
            return true;
        } else {
            int i = LightEngine.func_215613_a(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
            return i < worldReader.getMaxLightLevel();
        }
    }

    private static boolean isNotUnderwater(IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }
}
