package net.seagullboi.originofspirits.client.renderer;

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

import net.seagullboi.originofspirits.entity.VindicatorChefEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class VindicatorChefRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(VindicatorChefEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelvindicator_chef(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/vindicator_chef.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelvindicator_chef extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer nose;
		private final ModelRenderer head;
		private final ModelRenderer chef_hat;
		private final ModelRenderer left_arm;
		private final ModelRenderer right_arm;
		private final ModelRenderer ladle;
		private final ModelRenderer left_leg;
		private final ModelRenderer right_leg;

		public Modelvindicator_chef() {
			textureWidth = 128;
			textureHeight = 128;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.setTextureOffset(0, 47).addBox(-4.0F, 0.1F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(0, 17).addBox(-4.0F, 0.1F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);
			nose = new ModelRenderer(this);
			nose.setRotationPoint(0.0F, -2.0F, 0.0F);
			body.addChild(nose);
			nose.setTextureOffset(0, 0).addBox(-1.0F, -0.9F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.addChild(head);
			head.setTextureOffset(28, 17).addBox(-4.0F, -9.9F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
			chef_hat = new ModelRenderer(this);
			chef_hat.setRotationPoint(0.0F, -30.0F, 0.0F);
			head.addChild(chef_hat);
			chef_hat.setTextureOffset(0, 0).addBox(-5.5F, 15.1F, -5.5F, 11.0F, 6.0F, 11.0F, 0.0F, false);
			chef_hat.setTextureOffset(19, 35).addBox(-4.5F, 20.1F, -4.5F, 9.0F, 3.0F, 9.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(4.0F, 2.0F, 0.0F);
			body.addChild(left_arm);
			setRotationAngle(left_arm, 0.0F, 0.0F, -0.0873F);
			left_arm.setTextureOffset(56, 31).addBox(-0.0087F, -1.9004F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-4.0F, 2.0F, 0.0F);
			body.addChild(right_arm);
			setRotationAngle(right_arm, 0.0F, 0.0F, 0.0873F);
			right_arm.setTextureOffset(44, 47).addBox(-3.9913F, -1.9004F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			ladle = new ModelRenderer(this);
			ladle.setRotationPoint(2.5F, 9.0F, 4.0F);
			right_arm.addChild(ladle);
			setRotationAngle(ladle, 1.5708F, -0.7854F, -1.5708F);
			ladle.setTextureOffset(84, 26).addBox(-1.0704F, -0.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-2.0704F, -0.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-3.0704F, -1.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-7.0704F, -5.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-8.0704F, -6.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-9.0704F, -7.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-10.0704F, -7.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-11.0704F, -7.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 26).addBox(-12.0704F, -8.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-1.0704F, -1.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-2.0704F, -2.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-6.0704F, -6.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-7.0704F, -7.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-12.0704F, -9.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-8.0704F, -8.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-12.0704F, -10.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-11.0704F, -11.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-2.0704F, -2.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-9.0704F, -11.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-8.0704F, -10.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 22).addBox(-10.0704F, -9.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-8.0704F, -9.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-10.0704F, -11.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-8.0704F, -7.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-10.0704F, -8.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-11.0704F, -8.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 20).addBox(-11.0704F, -9.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 18).addBox(-2.0704F, -1.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 18).addBox(-7.0704F, -6.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 18).addBox(-9.0704F, -9.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 18).addBox(-11.0704F, -10.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 16).addBox(-3.0704F, -2.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 16).addBox(-6.0704F, -5.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 16).addBox(-9.0704F, -8.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 16).addBox(-9.0704F, -10.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(84, 16).addBox(-10.0704F, -10.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 16).addBox(-5.0704F, -5.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 16).addBox(-3.0704F, -3.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 16).addBox(-4.0704F, -4.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 18).addBox(-5.0704F, -4.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 18).addBox(-4.0704F, -3.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 20).addBox(-4.0704F, -2.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 20).addBox(-6.0704F, -4.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			ladle.setTextureOffset(80, 20).addBox(-5.0704F, -3.9296F, 3.9913F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
			setRotationAngle(left_leg, 0.0F, 0.0F, -0.0436F);
			left_leg.setTextureOffset(28, 47).addBox(1.9956F, 0.0999F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
			setRotationAngle(right_leg, 0.0F, 0.0F, 0.0436F);
			right_leg.setTextureOffset(44, 0).addBox(-5.9956F, 0.0999F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.nose.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.nose.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}

}
