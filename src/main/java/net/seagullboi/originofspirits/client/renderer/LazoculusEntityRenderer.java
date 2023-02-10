package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.client.model.LazoculusEntityModel;
import net.seagullboi.originofspirits.entity.LazoculusEntity;

public class LazoculusEntityRenderer extends MobRenderer<LazoculusEntity, LazoculusEntityModel<LazoculusEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/lazoculus/lazoculus.png");
    protected static final ResourceLocation GLOW = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/lazoculus/lazoculus_glow.png");

    public LazoculusEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LazoculusEntityModel<>(), 0.7F);
        this.addLayer(new GlowingLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(LazoculusEntity entity) {
        return TEXTURE;
    }

    private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

        public GlowingLayer(IEntityRenderer<T, M> entityRendererIn) {
            super(entityRendererIn);
        }

        @Override
        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(GLOW));
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }
}