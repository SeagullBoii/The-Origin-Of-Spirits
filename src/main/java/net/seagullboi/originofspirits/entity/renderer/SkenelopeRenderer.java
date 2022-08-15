package net.seagullboi.originofspirits.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.seagullboi.originofspirits.entity.SkenelopeEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class SkenelopeRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(SkenelopeEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelskentilope(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {

						return new ResourceLocation("originofspirits:textures/skentelope.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelskentilope extends EntityModel<Entity> {
		private final ModelRenderer neck;
		private final ModelRenderer head;
		private final ModelRenderer left_ear;
		private final ModelRenderer left_horn;
		private final ModelRenderer right_ear;
		private final ModelRenderer right_horn;
		private final ModelRenderer mouth;
		private final ModelRenderer headpiece;
		private final ModelRenderer body;
		private final ModelRenderer tail;
		private final ModelRenderer front_left_leg;
		private final ModelRenderer front_right_leg;
		private final ModelRenderer back_left_leg;
		private final ModelRenderer back_right_leg;

		public Modelskentilope() {
			textureWidth = 64;
			textureHeight = 64;
			neck = new ModelRenderer(this);
			neck.setRotationPoint(0.0F, 7.0F, -9.9F);
			setRotationAngle(neck, 0.1309F, 0.0F, 0.0F);
			neck.setTextureOffset(1, 36).addBox(-2.0F, -12.0F, -2.1F, 4.0F, 12.0F, 6.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -12.0F, 0.4F);
			neck.addChild(head);
			setRotationAngle(head, -0.1309F, 0.0F, 0.0F);
			left_ear = new ModelRenderer(this);
			left_ear.setRotationPoint(0.0F, 5.0F, -1.0F);
			head.addChild(left_ear);
			left_ear.setTextureOffset(19, 16).addBox(-2.5F, -12.0F, 3.99F, 2.0F, 3.0F, 1.0F, 0.0F, false);
			left_horn = new ModelRenderer(this);
			left_horn.setRotationPoint(-2.0F, -9.0F, 0.0F);
			left_ear.addChild(left_horn);
			setRotationAngle(left_horn, 0.0F, -0.1309F, 0.0F);
			left_horn.setTextureOffset(23, 13).addBox(-0.5F, -11.0F, 1.99F, 0.0F, 10.0F, 9.0F, 0.0F, true);
			right_ear = new ModelRenderer(this);
			right_ear.setRotationPoint(0.0F, 5.0F, -1.0F);
			head.addChild(right_ear);
			right_ear.setTextureOffset(19, 16).addBox(0.5F, -12.0F, 3.99F, 2.0F, 3.0F, 1.0F, 0.0F, false);
			right_horn = new ModelRenderer(this);
			right_horn.setRotationPoint(2.0F, -9.0F, 0.0F);
			right_ear.addChild(right_horn);
			setRotationAngle(right_horn, 0.0F, 0.1309F, 0.0F);
			right_horn.setTextureOffset(23, 13).addBox(0.5F, -11.0F, 1.99F, 0.0F, 10.0F, 9.0F, 0.0F, false);
			mouth = new ModelRenderer(this);
			mouth.setRotationPoint(0.0F, 6.0F, -1.0F);
			head.addChild(mouth);
			mouth.setTextureOffset(0, 25).addBox(-3.0F, -11.0F, -7.0F, 6.0F, 5.0F, 5.0F, 0.0F, false);
			headpiece = new ModelRenderer(this);
			headpiece.setRotationPoint(0.0F, 6.0F, -1.0F);
			head.addChild(headpiece);
			headpiece.setTextureOffset(0, 0).addBox(-3.0F, -12.0F, -2.0F, 6.0F, 6.0F, 7.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 11.0F, 6.0F);
			body.setTextureOffset(0, 32).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, 0.05F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 3.0F, 11.0F);
			tail.setTextureOffset(0, 19).addBox(-1.5F, 1.0F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
			front_left_leg = new ModelRenderer(this);
			front_left_leg.setRotationPoint(-4.0F, 14.0F, -9.1F);
			front_left_leg.setTextureOffset(48, 21).addBox(5.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, 0.0F, true);
			front_right_leg = new ModelRenderer(this);
			front_right_leg.setRotationPoint(4.0F, 14.0F, -9.1F);
			front_right_leg.setTextureOffset(48, 21).addBox(-9.0F, -1.0F, -1.9F, 4.0F, 11.0F, 4.0F, 0.0F, false);
			back_left_leg = new ModelRenderer(this);
			back_left_leg.setRotationPoint(-4.0F, 14.0F, 8.0F);
			back_left_leg.setTextureOffset(48, 21).addBox(5.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, 0.0F, true);
			back_right_leg = new ModelRenderer(this);
			back_right_leg.setRotationPoint(4.0F, 14.0F, 8.0F);
			back_right_leg.setTextureOffset(48, 21).addBox(-9.0F, -1.0F, -1.0F, 4.0F, 11.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			float f = 1;
			if (this.isChild) {
				f = (float) ((double) f * 0.7D);
			}
			matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
			matrixStack.scale(f, f, f);
			neck.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			tail.render(matrixStack, buffer, packedLight, packedOverlay);
			front_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			front_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			back_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			back_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.front_right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.back_right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.front_left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.back_left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
