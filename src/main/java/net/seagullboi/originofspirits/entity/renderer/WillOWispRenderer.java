package net.seagullboi.originofspirits.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import net.seagullboi.originofspirits.entity.WillOWispEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class WillOWispRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(WillOWispEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelwillowisp(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/will_o_wisp.png");
					}
				};
			});
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/will_o_wisp.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 4.1.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelwillowisp extends EntityModel<WillOWispEntity.CustomEntity> {
		private final ModelRenderer body;
		private final ModelRenderer wing_right;
		private final ModelRenderer wing_left;
		private final ModelRenderer leg_right;
		private final ModelRenderer cube_r1;
		private final ModelRenderer leg_left;
		private final ModelRenderer cube_r2;

		public Modelwillowisp() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 14.25F, -1.5F);
			body.setTextureOffset(0, 2).addBox(-4.5F, -1.25F, -4.5F, 9.0F, 9.0F, 9.0F, 0.0F, false);
			body.setTextureOffset(0, 20).addBox(-4.5F, -5.25F, -4.5F, 9.0F, 4.0F, 9.0F, 0.0F, false);
			wing_right = new ModelRenderer(this);
			wing_right.setRotationPoint(3.5F, 3.75F, 4.5F);
			body.addChild(wing_right);
			wing_right.setTextureOffset(0, 37).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 0.0F, false);
			wing_left = new ModelRenderer(this);
			wing_left.setRotationPoint(-3.5F, 3.75F, 4.5F);
			body.addChild(wing_left);
			wing_left.setTextureOffset(0, 37).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 0.0F, true);
			leg_right = new ModelRenderer(this);
			leg_right.setRotationPoint(-0.5F, 6.75F, 0.5F);
			body.addChild(leg_right);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(3.0F, 0.0F, 0.0F);
			leg_right.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, -1.5708F, 0.0F);
			cube_r1.setTextureOffset(1, 51).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, 0.0F, false);
			leg_left = new ModelRenderer(this);
			leg_left.setRotationPoint(0.5F, 6.75F, 0.5F);
			body.addChild(leg_left);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(-3.0F, 0.0F, 0.0F);
			leg_left.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
			cube_r2.setTextureOffset(1, 51).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 5.0F, 0.0F, 0.0F, true);
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

		public void setRotationAngles(WillOWispEntity.CustomEntity e, float f, float f1, float f2, float f3, float f4) {
			int i = e.getAnimRotation();
			this.wing_right.rotateAngleY = -1.0F + 0.75F * MathHelper.func_233021_e_((float) i - 0.5f, 5.0F);
			this.wing_left.rotateAngleY = -this.wing_right.rotateAngleY;
			this.leg_left.rotateAngleY = -0.5F + 0.375F * MathHelper.func_233021_e_((float) i - 0.5f, 5.0F);
			this.leg_right.rotateAngleY = -this.leg_left.rotateAngleY;

		}
	}
}
