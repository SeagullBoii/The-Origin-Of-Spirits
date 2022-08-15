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

import net.seagullboi.originofspirits.entity.CapybaraEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class CapybaraRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(CapybaraEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelcapybara(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/capybara_2.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelcapybara extends EntityModel<Entity> {
		private final ModelRenderer standin;
		private final ModelRenderer adult_standing;
		private final ModelRenderer body;
		private final ModelRenderer rotation;
		private final ModelRenderer head;
		private final ModelRenderer ear2;
		private final ModelRenderer ear1;
		private final ModelRenderer leg1;
		private final ModelRenderer leg2;
		private final ModelRenderer leg3;
		private final ModelRenderer leg4;
		private final ModelRenderer baby_standing;
		private final ModelRenderer body_baby;
		private final ModelRenderer rotation2;
		private final ModelRenderer head_baby;
		private final ModelRenderer ear_baby;
		private final ModelRenderer ear_baby2;
		private final ModelRenderer leg_baby;
		private final ModelRenderer leg_baby2;
		private final ModelRenderer leg_baby3;
		private final ModelRenderer leg_baby4;
		private final ModelRenderer sit;
		private final ModelRenderer adult_sitting;
		private final ModelRenderer body_sit;
		private final ModelRenderer rotation_sit;
		private final ModelRenderer head_sit;
		private final ModelRenderer ear_sit;
		private final ModelRenderer ear_sit2;
		private final ModelRenderer leg_sit1;
		private final ModelRenderer leg_sit2;
		private final ModelRenderer leg_sit3;
		private final ModelRenderer leg_sit4;
		private final ModelRenderer baby_sit;
		private final ModelRenderer body_baby_sit;
		private final ModelRenderer rotation_sit2;
		private final ModelRenderer head_baby_sit;
		private final ModelRenderer ear_baby_sit;
		private final ModelRenderer ear_baby_sit2;
		private final ModelRenderer leg_baby_sit;
		private final ModelRenderer leg_baby_sit2;
		private final ModelRenderer leg_baby_sit3;
		private final ModelRenderer leg_baby_sit4;
		public Modelcapybara() {
			textureWidth = 128;
			textureHeight = 128;
			standin = new ModelRenderer(this);
			standin.setRotationPoint(0.0F, 24.0F, 0.0F);
			adult_standing = new ModelRenderer(this);
			adult_standing.setRotationPoint(1.0F, 0.0F, 0.0F);
			standin.addChild(adult_standing);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, -18.0F, 1.0F);
			adult_standing.addChild(body);
			rotation = new ModelRenderer(this);
			rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
			body.addChild(rotation);
			setRotationAngle(rotation, 1.5708F, 0.0F, 0.0F);
			rotation.setTextureOffset(0, 0).addBox(-7.0F, -10.0F, -10.0F, 12.0F, 20.0F, 12.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(-1.0F, -1.0F, -8.0F);
			body.addChild(head);
			head.setTextureOffset(0, 32).addBox(-4.0F, -5.0F, -13.0F, 8.0F, 10.0F, 13.0F, 0.0F, false);
			ear2 = new ModelRenderer(this);
			ear2.setRotationPoint(4.0F, -3.0F, -1.4F);
			head.addChild(ear2);
			setRotationAngle(ear2, -0.7854F, 0.0F, 0.0F);
			ear2.setTextureOffset(4, 7).addBox(-0.1F, -3.0F, -1.4F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			ear1 = new ModelRenderer(this);
			ear1.setRotationPoint(-4.0F, -3.0F, -1.4F);
			head.addChild(ear1);
			setRotationAngle(ear1, -0.7854F, 0.0F, 0.0F);
			ear1.setTextureOffset(0, 7).addBox(0.1F, -3.0F, -1.4F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			leg1 = new ModelRenderer(this);
			leg1.setRotationPoint(-4.0F, 10.0F, 7.0F);
			body.addChild(leg1);
			leg1.setTextureOffset(16, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(2.0F, 10.0F, 7.0F);
			body.addChild(leg2);
			leg2.setTextureOffset(0, 55).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg3 = new ModelRenderer(this);
			leg3.setRotationPoint(-4.0F, 10.0F, -6.0F);
			body.addChild(leg3);
			leg3.setTextureOffset(48, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg4 = new ModelRenderer(this);
			leg4.setRotationPoint(2.0F, 10.0F, -7.0F);
			body.addChild(leg4);
			leg4.setTextureOffset(36, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			baby_standing = new ModelRenderer(this);
			baby_standing.setRotationPoint(-1.0F, 3.0F, -5.0F);
			standin.addChild(baby_standing);
			body_baby = new ModelRenderer(this);
			body_baby.setRotationPoint(0.0F, -18.0F, 1.0F);
			baby_standing.addChild(body_baby);
			rotation2 = new ModelRenderer(this);
			rotation2.setRotationPoint(0.0F, 0.0F, 0.0F);
			body_baby.addChild(rotation2);
			setRotationAngle(rotation2, 1.5708F, 0.0F, 0.0F);
			rotation2.setTextureOffset(90, 105).addBox(-3.0F, -2.0F, -10.0F, 9.0F, 13.0F, 8.0F, 0.0F, false);
			head_baby = new ModelRenderer(this);
			head_baby.setRotationPoint(1.0F, 5.0F, -2.0F);
			body_baby.addChild(head_baby);
			head_baby.setTextureOffset(84, 84).addBox(-3.0F, -8.0F, -10.0F, 7.0F, 9.0F, 12.0F, 0.0F, false);
			ear_baby = new ModelRenderer(this);
			ear_baby.setRotationPoint(3.9F, -7.0F, 0.6F);
			head_baby.addChild(ear_baby);
			setRotationAngle(ear_baby, -0.7854F, 0.0F, 0.0F);
			ear_baby.setTextureOffset(90, 82).addBox(-0.1F, -2.2929F, -0.6929F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			ear_baby2 = new ModelRenderer(this);
			ear_baby2.setRotationPoint(-3.0F, -7.0F, 0.6F);
			head_baby.addChild(ear_baby2);
			setRotationAngle(ear_baby2, -0.7854F, 0.0F, 0.0F);
			ear_baby2.setTextureOffset(90, 82).addBox(0.1F, -2.2929F, -0.6929F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			leg_baby = new ModelRenderer(this);
			leg_baby.setRotationPoint(-1.0F, 10.0F, 10.0F);
			body_baby.addChild(leg_baby);
			leg_baby.setTextureOffset(86, 77).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby2 = new ModelRenderer(this);
			leg_baby2.setRotationPoint(4.0F, 10.0F, 7.0F);
			body_baby.addChild(leg_baby2);
			leg_baby2.setTextureOffset(86, 77).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby3 = new ModelRenderer(this);
			leg_baby3.setRotationPoint(-1.0F, 10.0F, 0.0F);
			body_baby.addChild(leg_baby3);
			leg_baby3.setTextureOffset(86, 77).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby4 = new ModelRenderer(this);
			leg_baby4.setRotationPoint(4.0F, 10.0F, 0.0F);
			body_baby.addChild(leg_baby4);
			leg_baby4.setTextureOffset(86, 77).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			sit = new ModelRenderer(this);
			sit.setRotationPoint(0.0F, 24.0F, 0.0F);
			adult_sitting = new ModelRenderer(this);
			adult_sitting.setRotationPoint(1.0F, 8.0F, 0.0F);
			sit.addChild(adult_sitting);
			body_sit = new ModelRenderer(this);
			body_sit.setRotationPoint(0.0F, -18.0F, 1.0F);
			adult_sitting.addChild(body_sit);
			rotation_sit = new ModelRenderer(this);
			rotation_sit.setRotationPoint(0.0F, 0.0F, 0.0F);
			body_sit.addChild(rotation_sit);
			setRotationAngle(rotation_sit, 1.5708F, 0.0F, 0.0F);
			rotation_sit.setTextureOffset(0, 73).addBox(-7.0F, -10.0F, -10.0F, 12.0F, 20.0F, 12.0F, 0.0F, false);
			head_sit = new ModelRenderer(this);
			head_sit.setRotationPoint(-1.0F, -1.0F, -8.0F);
			body_sit.addChild(head_sit);
			head_sit.setTextureOffset(0, 105).addBox(-4.0F, -5.0F, -13.0F, 8.0F, 10.0F, 13.0F, 0.0F, false);
			ear_sit = new ModelRenderer(this);
			ear_sit.setRotationPoint(4.0F, -3.0F, -1.4F);
			head_sit.addChild(ear_sit);
			setRotationAngle(ear_sit, -0.7854F, 0.0F, 0.0F);
			ear_sit.setTextureOffset(42, 116).addBox(-0.1F, -3.0F, -1.4F, 0.0F, 2.0F, 2.0F, 0.0F, true);
			ear_sit2 = new ModelRenderer(this);
			ear_sit2.setRotationPoint(-4.0F, -3.0F, -1.4F);
			head_sit.addChild(ear_sit2);
			setRotationAngle(ear_sit2, -0.7854F, 0.0F, 0.0F);
			ear_sit2.setTextureOffset(42, 116).addBox(0.1F, -3.0F, -1.4F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			leg_sit1 = new ModelRenderer(this);
			leg_sit1.setRotationPoint(-5.0F, 8.0F, 9.0F);
			body_sit.addChild(leg_sit1);
			setRotationAngle(leg_sit1, 1.5708F, -0.7854F, 0.0F);
			leg_sit1.setTextureOffset(29, 106).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg_sit2 = new ModelRenderer(this);
			leg_sit2.setRotationPoint(4.0F, 8.0F, 9.0F);
			body_sit.addChild(leg_sit2);
			setRotationAngle(leg_sit2, 1.5708F, 0.7854F, 0.0F);
			leg_sit2.setTextureOffset(29, 106).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg_sit3 = new ModelRenderer(this);
			leg_sit3.setRotationPoint(-5.0F, 7.0F, -9.0F);
			body_sit.addChild(leg_sit3);
			setRotationAngle(leg_sit3, -1.5708F, 0.7854F, 0.0F);
			leg_sit3.setTextureOffset(28, 106).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			leg_sit4 = new ModelRenderer(this);
			leg_sit4.setRotationPoint(3.0F, 7.0F, -8.0F);
			body_sit.addChild(leg_sit4);
			setRotationAngle(leg_sit4, -1.5708F, -0.7854F, 0.0F);
			leg_sit4.setTextureOffset(29, 106).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
			baby_sit = new ModelRenderer(this);
			baby_sit.setRotationPoint(-1.0F, 8.0F, -5.0F);
			sit.addChild(baby_sit);
			body_baby_sit = new ModelRenderer(this);
			body_baby_sit.setRotationPoint(0.0F, -18.0F, 1.0F);
			baby_sit.addChild(body_baby_sit);
			rotation_sit2 = new ModelRenderer(this);
			rotation_sit2.setRotationPoint(0.0F, 0.0F, 0.0F);
			body_baby_sit.addChild(rotation_sit2);
			setRotationAngle(rotation_sit2, 1.5708F, 0.0F, 0.0F);
			rotation_sit2.setTextureOffset(42, 41).addBox(-3.0F, -2.0F, -10.0F, 9.0F, 13.0F, 8.0F, 0.0F, false);
			head_baby_sit = new ModelRenderer(this);
			head_baby_sit.setRotationPoint(1.0F, 5.0F, -2.0F);
			body_baby_sit.addChild(head_baby_sit);
			head_baby_sit.setTextureOffset(36, 20).addBox(-3.0F, -8.0F, -10.0F, 7.0F, 9.0F, 12.0F, 0.0F, false);
			ear_baby_sit = new ModelRenderer(this);
			ear_baby_sit.setRotationPoint(3.9F, -7.0F, 0.6F);
			head_baby_sit.addChild(ear_baby_sit);
			setRotationAngle(ear_baby_sit, -0.7854F, 0.0F, 0.0F);
			ear_baby_sit.setTextureOffset(4, 5).addBox(-0.1F, -2.2929F, -0.6929F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			ear_baby_sit2 = new ModelRenderer(this);
			ear_baby_sit2.setRotationPoint(-3.0F, -7.0F, 0.6F);
			head_baby_sit.addChild(ear_baby_sit2);
			setRotationAngle(ear_baby_sit2, -0.7854F, 0.0F, 0.0F);
			ear_baby_sit2.setTextureOffset(0, 5).addBox(0.1F, -2.2929F, -0.6929F, 0.0F, 2.0F, 2.0F, 0.0F, false);
			leg_baby_sit = new ModelRenderer(this);
			leg_baby_sit.setRotationPoint(-2.0F, 8.0F, 10.0F);
			body_baby_sit.addChild(leg_baby_sit);
			setRotationAngle(leg_baby_sit, 1.5708F, -0.7854F, 0.0F);
			leg_baby_sit.setTextureOffset(52, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby_sit2 = new ModelRenderer(this);
			leg_baby_sit2.setRotationPoint(5.0F, 11.0F, 10.0F);
			body_baby_sit.addChild(leg_baby_sit2);
			setRotationAngle(leg_baby_sit2, 1.5708F, 0.7854F, 0.0F);
			leg_baby_sit2.setTextureOffset(42, 41).addBox(-1.0F, 0.0F, 1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby_sit3 = new ModelRenderer(this);
			leg_baby_sit3.setRotationPoint(-2.0F, 9.0F, -1.0F);
			body_baby_sit.addChild(leg_baby_sit3);
			setRotationAngle(leg_baby_sit3, -1.5708F, 0.7854F, 0.0F);
			leg_baby_sit3.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			leg_baby_sit4 = new ModelRenderer(this);
			leg_baby_sit4.setRotationPoint(5.0F, 9.0F, -1.0F);
			body_baby_sit.addChild(leg_baby_sit4);
			setRotationAngle(leg_baby_sit4, -1.5708F, -0.7854F, 0.0F);
			leg_baby_sit4.setTextureOffset(0, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
				float f = 1;
				if (this.isChild) {
					f = (float) ((double) f * 0.5D);
					}
					 matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
                     matrixStack.scale(f, f, f);
			standin.render(matrixStack, buffer, packedLight, packedOverlay);
			sit.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg_baby.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg_baby3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg_baby4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg_baby2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.head_baby.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head_baby.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.head_baby_sit.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head_baby_sit.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.head_sit.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head_sit.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
