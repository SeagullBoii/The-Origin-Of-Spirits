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

import net.seagullboi.originofspirits.entity.ElectricEelEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ElectricEelRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(ElectricEelEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelelectric_eel(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						ElectricEelEntity.CustomEntity _eel = (ElectricEelEntity.CustomEntity) entity;
						return new ResourceLocation("originofspirits:textures/eel/electric_eel_" + String.valueOf(_eel.getVariant()) + ".png");
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
					.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/electric_eel_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.9.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelelectric_eel extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer jaw1;
		private final ModelRenderer jaw2;
		private final ModelRenderer tail1;
		private final ModelRenderer tail2;
		private final ModelRenderer tail3;
		public Modelelectric_eel() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 22.0F, -11.0F);
			head.setTextureOffset(0, 32).addBox(-0.1F, -4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
			jaw1 = new ModelRenderer(this);
			jaw1.setRotationPoint(0.0F, -1.0F, 0.0F);
			head.addChild(jaw1);
			jaw1.setTextureOffset(16, 16).addBox(-2.5F, -2.0F, -6.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);
			jaw2 = new ModelRenderer(this);
			jaw2.setRotationPoint(0.0F, 1.0F, 0.0F);
			head.addChild(jaw2);
			jaw2.setTextureOffset(21, 0).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 2.0F, 6.0F, 0.0F, false);
			tail1 = new ModelRenderer(this);
			tail1.setRotationPoint(0.0F, 21.0F, -11.0F);
			tail1.setTextureOffset(0, 5).addBox(-0.1F, -3.0F, 0.0F, 0.0F, 5.0F, 11.0F, 0.0F, false);
			tail1.setTextureOffset(0, 0).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 5.0F, 11.0F, 0.0F, false);
			tail1.setTextureOffset(0, 5).addBox(-0.1F, -1.0F, 0.0F, 0.0F, 5.0F, 11.0F, 0.0F, false);
			tail2 = new ModelRenderer(this);
			tail2.setRotationPoint(0.0F, 1.0F, 11.0F);
			tail1.addChild(tail2);
			tail2.setTextureOffset(26, 29).addBox(-0.1F, -4.0F, 0.0F, 0.0F, 4.0F, 6.0F, 0.0F, false);
			tail2.setTextureOffset(0, 21).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);
			tail2.setTextureOffset(26, 29).addBox(-0.1F, -1.0F, 0.0F, 0.0F, 4.0F, 6.0F, 0.0F, false);
			tail3 = new ModelRenderer(this);
			tail3.setRotationPoint(0.0F, 1.0F, 17.0F);
			tail1.addChild(tail3);
			tail3.setTextureOffset(0, 25).addBox(-0.1F, -3.0F, 0.0F, 0.0F, 2.0F, 7.0F, 0.0F, false);
			tail3.setTextureOffset(20, 25).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 6.0F, 0.0F, false);
			tail3.setTextureOffset(0, 25).addBox(-0.1F, -1.0F, 0.0F, 0.0F, 4.0F, 7.0F, 0.0F, false);
			tail3.setTextureOffset(0, 0).addBox(-0.1F, -2.0F, 7.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			tail1.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.jaw1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail1.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tail2.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.jaw2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.tail3.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}
}
