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

import net.seagullboi.originofspirits.item.SwirlinYoyoProjectilesItem;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class SwirlinYoyoProjectilesRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(SwirlinYoyoProjectilesItem.arrow, renderManager -> new CustomRender(renderManager));
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class CustomRender extends EntityRenderer<SwirlinYoyoProjectilesItem.ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("originofspirits:textures/swirlin_yoyo.png");

		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(SwirlinYoyoProjectilesItem.ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
				IRenderTypeBuffer bufferIn, int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modelswirlin_yoyo();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(SwirlinYoyoProjectilesItem.ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 4.2.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelswirlin_yoyo extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer hand_left;
		private final ModelRenderer hand_right;
		private final ModelRenderer string;

		public Modelswirlin_yoyo() {
			textureWidth = 32;
			textureHeight = 32;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 20.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 0.0F, false);
			body.setTextureOffset(0, 22).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 5.0F, 5.0F, 0.0F, false);
			hand_left = new ModelRenderer(this);
			hand_left.setRotationPoint(-2.0F, 0.0F, 0.0F);
			body.addChild(hand_left);
			hand_left.setTextureOffset(10, 10).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, 0.0F, false);
			hand_left.setTextureOffset(0, 0).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, false);
			hand_right = new ModelRenderer(this);
			hand_right.setRotationPoint(2.0F, 0.0F, 0.0F);
			body.addChild(hand_right);
			hand_right.setTextureOffset(10, 10).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, 0.0F, true);
			hand_right.setTextureOffset(0, 0).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, true);
			string = new ModelRenderer(this);
			string.setRotationPoint(0.0F, 22.5F, 0.0F);
			string.setTextureOffset(27, 24).addBox(-0.5F, 0.5F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
			string.setTextureOffset(27, 24).addBox(-0.5F, 7.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			string.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.body.rotateAngleX = f2;
		}
	}

}
