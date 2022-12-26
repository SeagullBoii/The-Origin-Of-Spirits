package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

import java.util.Random;

public class BasicCaveGenBlock extends FlowerBlock {
    
    public BasicCaveGenBlock(Properties properties) {
        super(Effects.WITHER, 100, properties);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial() == Material.ROCK;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.CAVE;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        generateCave(pos.getX(), pos.getY(), pos.getZ(), worldIn, pos);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean moving) {
        super.onBlockAdded(state, worldIn, pos, oldState, moving);
        generateCave(pos.getX(), pos.getY(), pos.getZ(), worldIn, pos);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, neighborBlock, fromPos, isMoving);
        generateCave(pos.getX(), pos.getY(), pos.getZ(), worldIn, pos);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, worldIn, pos, entity);
        generateCave(pos.getX(), pos.getY(), pos.getZ(), worldIn, pos);
    }

    public void generateCave(int x, int y, int z, World worldIn, BlockPos pos) {
        int xSurface;
        int ySurface;
        int zSurface;

        zSurface = (-32);
        for (int index0 = 0; index0 < 64; index0++) {
            xSurface = (-32);
            for (int index1 = 0; index1 < 64; index1++) {
                ySurface = (-8);
                for (int index2 = 0; index2 < 16; index2++) {
                    ySurface++;

                    BlockPos blockPos = new BlockPos(x + xSurface, y + ySurface, z + zSurface);
                    if (worldIn.getBlockState(blockPos).getBlock() == Blocks.AIR || worldIn.getBlockState(blockPos).getBlock() == Blocks.CAVE_AIR) {
                        if (TOOSBlocks.BLESSED_STONE.get() == (worldIn.getBlockState(blockPos.down())).getBlock() || TOOSBlocks.DECEPTONE.get() == (worldIn.getBlockState(new BlockPos(x + xSurface,  y + ySurface - 1, z + zSurface))).getBlock()) {
                            if (0.05 >= Math.random()) {
                                worldIn.setBlockState(blockPos, TOOSBlocks.CURSED_FIRE.get().getDefaultState(), 3);
                            } else if (0.1 >= Math.random()) {
                                worldIn.setBlockState(blockPos, CursedGeyserBlock.block.getDefaultState(), 3);
                            }
                            if (Math.random() < 0.5) {
                                worldIn.setBlockState(new BlockPos(x + xSurface,  y + ySurface - 1, z + zSurface), TOOSBlocks.DECEPTONE.get().getDefaultState(), 3);
                            }
                        }
                    }
                    if (TOOSBlocks.BLESSED_STONE.get() == (worldIn.getBlockState(new BlockPos(x, y, z))).getBlock()) {
                        if (Math.random() < 0.7) {
                            worldIn.setBlockState(new BlockPos(x, y, z), TOOSBlocks.DECEPTONE.get().getDefaultState(), 3);
                        }
                    }
                }
                xSurface++;
            }
            zSurface = (zSurface + 1);
        }
       worldIn.setBlockState(new BlockPos(x, y, z), Blocks.CAVE_AIR.getDefaultState(), 3);
    }

}
