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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.client.model.ElectricEelEntityModel;
import net.seagullboi.originofspirits.entity.ElectricEelEntity;

@OnlyIn(Dist.CLIENT)
public class ElectricEelEntityRenderer extends MobRenderer<ElectricEelEntity, ElectricEelEntityModel<ElectricEelEntity>> {
	protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/electric_eel/electric_eel_");
	protected static final ResourceLocation GLOW = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/electric_eel/electric_eel_glow.png");

	public ElectricEelEntityRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ElectricEelEntityModel<>(), 0.7F);
		this.addLayer(new GlowingLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ElectricEelEntity entity) {
		return new ResourceLocation(TEXTURE.toString() + entity.getVariant() + ".png");
	}

	protected void applyRotations(ElectricEelEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate(0.1F, 0.1F, -0.1F);
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}

	}

	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(GLOW));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}
}
