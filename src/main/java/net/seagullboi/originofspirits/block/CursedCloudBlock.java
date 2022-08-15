package net.seagullboi.originofspirits.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.potion.CursedPotionEffect;

public class CursedCloudBlock extends CloudBlock {

    private final Effect effect;

    public CursedCloudBlock(Properties properties, boolean rain, Effect damagingEffect) {
        super(properties, rain);
        this.effect = damagingEffect;
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollision(state, worldIn, pos, entityIn);
        if (entityIn.isSneaking()) {
            entityIn.setMotion(entityIn.getMotion().getX(), 0.5, entityIn.getMotion().getZ());
        } else {
            entityIn.setMotion(entityIn.getMotion().getX(), 0, entityIn.getMotion().getZ());
        }

        if (entityIn instanceof LivingEntity) {
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(CursedPotionEffect.potion, 20, 0, false, true));
        }
    }
}
