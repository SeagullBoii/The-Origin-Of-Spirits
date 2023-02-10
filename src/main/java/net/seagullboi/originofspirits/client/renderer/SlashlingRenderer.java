package net.seagullboi.originofspirits.client.renderer;

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

import net.seagullboi.originofspirits.entity.SlashlingEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class SlashlingRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(SlashlingEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelslashling(), 0.6f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/slashling.png");
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
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/slashling_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 4.1.4
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelslashling extends EntityModel<SlashlingEntity.CustomEntity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer headwear;
		private final ModelRenderer right_arm;
		private final ModelRenderer left_arm;
		private final ModelRenderer right_leg;
		private final ModelRenderer left_leg;

		public Modelslashling() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 7.0F, 0.0F);
			body.setTextureOffset(0, 16).addBox(-4.0F, -15.0F, -2.0F, 8.0F, 15.0F, 4.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -15.5F, 0.0F);
			body.addChild(head);
			head.setTextureOffset(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, -0.5F, false);
			headwear = new ModelRenderer(this);
			headwear.setRotationPoint(0.0F, 0.1F, 3.0F);
			head.addChild(headwear);
			setRotationAngle(headwear, 0.2618F, 0.0F, 0.0F);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-4.0F, -14.0F, 0.0F);
			body.addChild(right_arm);
			right_arm.setTextureOffset(0, 35).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 23.0F, 3.0F, 0.0F, false);
			right_arm.setTextureOffset(32, -12).addBox(-1.5F, 16.0F, -3.5F, 0.0F, 8.0F, 12.0F, 0.0F, true);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(4.0F, -14.0F, 0.0F);
			body.addChild(left_arm);
			left_arm.setTextureOffset(24, 16).addBox(0.0F, -1.0F, -1.5F, 3.0F, 23.0F, 3.0F, 0.0F, false);
			left_arm.setTextureOffset(32, -12).addBox(1.5F, 16.0F, -3.5F, 0.0F, 8.0F, 12.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(-2.0F, 7.0F, 0.0F);
			right_leg.setTextureOffset(36, 17).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 17.0F, 2.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(2.0F, 7.0F, 0.0F);
			left_leg.setTextureOffset(12, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 17.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(SlashlingEntity.CustomEntity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 / 4;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1 / 4;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 / 4;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1 / 4;
			this.body.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1 / 4;
			int i = e.getAttackTimer();
			if (i > 0) {
				this.right_arm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) i - 1.5f, 10.5F);
				this.left_arm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) i - 1.5f, 10.5F);
			}
		}
	}
}
