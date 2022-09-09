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

import net.seagullboi.originofspirits.entity.ElectricSurgeonfishEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ElectricSurgeonfishRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(ElectricSurgeonfishEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelsurgeonfish(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/surgeonfish.png");
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
					.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/surgeonfish_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelsurgeonfish extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer mouth1;
		private final ModelRenderer mouth2;
		private final ModelRenderer body1;
		private final ModelRenderer body2;
		private final ModelRenderer left_fin;
		private final ModelRenderer right_fin;
		private final ModelRenderer body_sub_2;
		private final ModelRenderer fin_top2;
		private final ModelRenderer tail;
		private final ModelRenderer fin_top1;
		private final ModelRenderer fin_left;

		public Modelsurgeonfish() {
			textureWidth = 32;
			textureHeight = 32;
			head = new ModelRenderer(this);
			head.setRotationPoint(-0.5F, 22.0F, -4.0F);
			mouth1 = new ModelRenderer(this);
			mouth1.setRotationPoint(0.5F, -0.9F, 0.4F);
			head.addChild(mouth1);
			setRotationAngle(mouth1, 0.3927F, 0.0F, 0.0F);
			mouth1.setTextureOffset(14, 12).addBox(-2.01F, -1.1627F, -2.8633F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			mouth2 = new ModelRenderer(this);
			mouth2.setRotationPoint(0.5F, 1.1F, 0.4F);
			head.addChild(mouth2);
			setRotationAngle(mouth2, 2.618F, 0.0F, 0.0F);
			mouth2.setTextureOffset(13, 0).addBox(-2.01F, -0.9038F, -0.5359F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			body1 = new ModelRenderer(this);
			body1.setRotationPoint(-0.5F, 22.0F, -4.0F);
			body1.setTextureOffset(0, 11).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 4.0F, 0.0F, false);
			body2 = new ModelRenderer(this);
			body2.setRotationPoint(0.1F, 0.0F, 4.0F);
			body1.addChild(body2);
			body2.setTextureOffset(0, 0).addBox(-1.6F, -2.0F, 0.0F, 3.0F, 4.0F, 7.0F, 0.0F, false);
			left_fin = new ModelRenderer(this);
			left_fin.setRotationPoint(-0.1F, 1.0F, 1.0F);
			body2.addChild(left_fin);
			setRotationAngle(left_fin, 0.0F, 0.0F, 0.7854F);
			left_fin.setTextureOffset(0, 4).addBox(1.0F, -1.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
			right_fin = new ModelRenderer(this);
			right_fin.setRotationPoint(-0.1F, 1.0F, 1.0F);
			body2.addChild(right_fin);
			setRotationAngle(right_fin, 0.0F, 0.0F, -0.7854F);
			body_sub_2 = new ModelRenderer(this);
			body_sub_2.setRotationPoint(0.0F, 0.0F, 0.0F);
			right_fin.addChild(body_sub_2);
			body_sub_2.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
			fin_top2 = new ModelRenderer(this);
			fin_top2.setRotationPoint(0.4F, -2.0F, 0.0F);
			body2.addChild(fin_top2);
			fin_top2.setTextureOffset(10, 5).addBox(-0.5F, -1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(-0.1F, 0.0F, 7.0F);
			body2.addChild(tail);
			tail.setTextureOffset(0, 14).addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);
			fin_top1 = new ModelRenderer(this);
			fin_top1.setRotationPoint(0.5F, -2.0F, 4.0F);
			body1.addChild(fin_top1);
			fin_top1.setTextureOffset(0, 0).addBox(-0.5F, -1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, false);
			fin_left = new ModelRenderer(this);
			fin_left.setRotationPoint(0.5F, 3.8373F, 3.2367F);
			body1.addChild(fin_left);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			body1.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.tail.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.body1.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.body2.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}

}
