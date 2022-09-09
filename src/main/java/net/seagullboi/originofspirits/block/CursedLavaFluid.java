package net.seagullboi.originofspirits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;

import java.util.function.Supplier;

public class CursedLavaFluid extends FlowingFluidBlock {
    public CursedLavaFluid(FlowingFluid fluidIn, Properties builder) {
        super(fluidIn, builder);
    }

    public CursedLavaFluid(Supplier<? extends FlowingFluid> supplier, Properties properties) {
        super(supplier, properties);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
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
