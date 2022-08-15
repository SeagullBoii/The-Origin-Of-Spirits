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

import net.seagullboi.originofspirits.item.WoodenStaffItem;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class WoodenStaffRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(WoodenStaffItem.arrow, renderManager -> new CustomRender(renderManager));
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class CustomRender extends EntityRenderer<WoodenStaffItem.ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("originofspirits:textures/empty.png");

		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}

		@Override
		public void render(WoodenStaffItem.ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
				IRenderTypeBuffer bufferIn, int packedLightIn) {
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modelbox_jellyfish();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(WoodenStaffItem.ArrowCustomEntity entity) {
			return texture;
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelbox_jellyfish extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer leg_front;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer leg_back;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer leg_right;
		private final ModelRenderer cube_r5;
		private final ModelRenderer leg_left;
		private final ModelRenderer cube_r6;

		public Modelbox_jellyfish() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 13.0F, 1.0F);
			body.setTextureOffset(18, 17).addBox(-3.0F, -10.0F, -4.0F, 7.0F, 1.0F, 7.0F, 0.0F, false);
			body.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -5.0F, 9.0F, 8.0F, 9.0F, 0.0F, false);
			leg_front = new ModelRenderer(this);
			leg_front.setRotationPoint(0.0F, -1.0F, -5.0F);
			body.addChild(leg_front);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 10.0F, 5.0F);
			leg_front.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
			cube_r1.setTextureOffset(0, 30).addBox(4.3F, -10.4F, -5.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 10.0F, 5.0F);
			leg_front.addChild(cube_r2);
			setRotationAngle(cube_r2, -1.5708F, 1.4399F, -1.5708F);
			cube_r2.setTextureOffset(0, 30).addBox(6.3F, -9.4F, -5.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			leg_back = new ModelRenderer(this);
			leg_back.setRotationPoint(0.0F, -1.0F, 4.0F);
			body.addChild(leg_back);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, 10.0F, -4.0F);
			leg_back.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, -1.5708F, 0.0F);
			cube_r3.setTextureOffset(0, 30).addBox(3.3F, -10.4F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 10.0F, -4.0F);
			leg_back.addChild(cube_r4);
			setRotationAngle(cube_r4, 1.5708F, -1.4399F, -1.5708F);
			cube_r4.setTextureOffset(0, 30).addBox(5.3F, -9.4F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			leg_right = new ModelRenderer(this);
			leg_right.setRotationPoint(5.0F, -1.0F, 0.0F);
			body.addChild(leg_right);
			leg_right.setTextureOffset(0, 30).addBox(-0.7F, -0.4F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(-5.0F, 10.0F, 0.0F);
			leg_right.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, 0.0F, -0.1309F);
			cube_r5.setTextureOffset(0, 30).addBox(6.2F, -9.3F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, false);
			leg_left = new ModelRenderer(this);
			leg_left.setRotationPoint(-4.0F, -1.0F, 0.0F);
			body.addChild(leg_left);
			leg_left.setTextureOffset(0, 30).addBox(0.7F, -0.4F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, true);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(4.0F, 10.0F, 0.0F);
			leg_left.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 0.0F, 0.1309F);
			cube_r6.setTextureOffset(0, 30).addBox(-5.2F, -9.4F, -6.0F, 0.0F, 13.0F, 11.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.leg_right.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.leg_back.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.leg_left.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
			this.leg_front.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}

}
