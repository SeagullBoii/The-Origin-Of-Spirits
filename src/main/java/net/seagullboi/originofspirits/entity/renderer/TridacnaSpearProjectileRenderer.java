package net.seagullboi.originofspirits.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import net.seagullboi.originofspirits.item.TridacnaSpearProjectileItem;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class TridacnaSpearProjectileRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(TridacnaSpearProjectileItem.arrow, renderManager -> new CustomRender(renderManager));
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class CustomRender extends EntityRenderer<TridacnaSpearProjectileItem.ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("originofspirits:textures/tridacna_spear_entity.png");

		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(TridacnaSpearProjectileItem.ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
				IRenderTypeBuffer bufferIn, int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modeltridacna_spear();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(TridacnaSpearProjectileItem.ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 4.0.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modeltridacna_spear extends EntityModel<Entity> {
		private final ModelRenderer tridacna_spear;

		public Modeltridacna_spear() {
			textureWidth = 64;
			textureHeight = 64;
			tridacna_spear = new ModelRenderer(this);
			tridacna_spear.setRotationPoint(0.0F, 24.0F, 0.4F);
			tridacna_spear.setTextureOffset(35, 35).addBox(1.0F, -7.0F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(27, 9).addBox(-1.0F, -40.0F, -0.9F, 1.0F, 40.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(31, 11).addBox(0.0F, -39.0F, -0.9F, 1.0F, 38.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(23, 11).addBox(-2.0F, -39.0F, -0.9F, 1.0F, 38.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(19, 13).addBox(-3.0F, -37.0F, -0.9F, 1.0F, 15.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(35, 13).addBox(1.0F, -37.0F, -0.9F, 1.0F, 15.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 14).addBox(-4.0F, -35.0F, -0.9F, 1.0F, 10.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(39, 14).addBox(2.0F, -35.0F, -0.9F, 1.0F, 10.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(39, 28).addBox(3.0F, -33.0F, -0.9F, 1.0F, 7.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 28).addBox(-5.0F, -33.0F, -0.9F, 1.0F, 7.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 36).addBox(-6.0F, -32.0F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(39, 36).addBox(4.0F, -32.0F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 41).addBox(-7.0F, -32.0F, -0.9F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(40, 41).addBox(5.0F, -32.0F, -0.9F, 1.0F, 3.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(19, 29).addBox(-3.0F, -19.0F, -0.9F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(35, 29).addBox(1.0F, -19.0F, -0.9F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 25).addBox(-4.0F, -17.0F, -0.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(39, 25).addBox(2.0F, -17.0F, -0.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(15, 25).addBox(-4.0F, -6.0F, -0.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(40, 25).addBox(2.0F, -6.0F, -0.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			tridacna_spear.setTextureOffset(19, 35).addBox(-3.0F, -7.0F, -0.9F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			tridacna_spear.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}

}
