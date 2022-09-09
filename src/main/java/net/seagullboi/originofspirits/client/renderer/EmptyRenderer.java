package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CursedLaserProjectile;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class EmptyRenderer extends EntityRenderer<AbstractArrowEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/empty.png");
    private static final RenderType RENDER_TYPE = RenderType.getEntityCutoutNoCull(TEXTURE_LOCATION);

    public EmptyRenderer(EntityRendererManager p_i46553_1_) {
        super(p_i46553_1_);
    }

    protected int getBlockLight(AbstractArrowEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }

    public void render(CursedLaserProjectile p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
    }

    @Override
    public ResourceLocation getEntityTexture(AbstractArrowEntity entity) {
        return TEXTURE_LOCATION;
    }

}