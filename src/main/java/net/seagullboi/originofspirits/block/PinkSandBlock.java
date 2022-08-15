package net.seagullboi.originofspirits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.seagullboi.originofspirits.registry.TOOSBlocks;

public class PinkSandBlock extends Block {
    private final int dustColor;

    public PinkSandBlock(int dustColorIn, Properties properties) {
        super(properties);
        this.dustColor = dustColorIn;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        if (!EntityTypeTags.getCollection().getTagByID(new ResourceLocation("originofspirits:glass_desert_mobs")).contains(entityIn.getType()) && worldIn.getBlockState(pos.down()).isAir()) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);

                FallingBlockEntity sand = new FallingBlockEntity(worldIn, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, TOOSBlocks.PINK_SAND.get().getDefaultState());
                sand.fallTime = 1;
                worldIn.addEntity(sand);
            }
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState state, IBlockReader reader, BlockPos pos) {
        return this.dustColor;
    }
}
