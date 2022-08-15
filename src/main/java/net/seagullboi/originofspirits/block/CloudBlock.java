package net.seagullboi.originofspirits.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CloudBlock extends Block {

    public final boolean rain;

    public CloudBlock(Properties properties, boolean rain) {
        super(properties);
        this.rain = rain;
    }

    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (entityIn.isSneaking()) {
            entityIn.setMotion(entityIn.getMotion().getX(), 0.5, entityIn.getMotion().getZ());
        } else {
            entityIn.setMotion(entityIn.getMotion().getX(), 0, entityIn.getMotion().getZ());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);

        if (this.rain) {
            for (int i = 0; i < 8; i++) {
                double x = pos.getX() + rand.nextFloat();
                double y = pos.getY() + rand.nextFloat();
                double z = pos.getZ() + rand.nextFloat();

                float speed = (rand.nextFloat() - 0.5F) * 0.1F;

                worldIn.addParticle(ParticleTypes.RAIN, x, y, z, speed, speed, speed);
            }
        }
    }
}
