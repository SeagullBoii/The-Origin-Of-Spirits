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

import net.seagullboi.originofspirits.entity.MeteoriteGolemEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class MeteoriteGolemRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(MeteoriteGolemEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelmeteorite_golem(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/meteorite_golem.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelmeteorite_golem extends EntityModel<MeteoriteGolemEntity.CustomEntity> {
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer right_arm;
		private final ModelRenderer right_leg;
		private final ModelRenderer left_arm;
		private final ModelRenderer left_leg;

		public Modelmeteorite_golem() {
			textureWidth = 128;
			textureHeight = 128;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 17.0F, 5.0F);
			setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-8.0F, -19.1323F, -4.0926F, 16.0F, 19.0F, 10.0F, 0.0F, false);
			body.setTextureOffset(30, 29).addBox(1.0F, -18.1323F, 5.9074F, 6.0F, 6.0F, 1.0F, 0.0F, false);
			body.setTextureOffset(30, 29).addBox(-7.0F, -18.1323F, 5.9074F, 6.0F, 6.0F, 1.0F, 0.0F, false);
			body.setTextureOffset(30, 29).addBox(-7.0F, -10.1323F, 5.9074F, 6.0F, 6.0F, 1.0F, 0.0F, false);
			body.setTextureOffset(30, 29).addBox(1.0F, -10.1323F, 5.9074F, 6.0F, 6.0F, 1.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.setTextureOffset(52, 0).addBox(-5.0F, -10.0F, -10.0F, 10.0F, 13.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(0, 29).addBox(-5.0F, -9.0F, -9.0F, 10.0F, 12.0F, 10.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(6.0F, 1.0F, -2.0F);
			right_arm.setTextureOffset(40, 29).addBox(0.0F, -2.0F, -4.0F, 7.0F, 22.0F, 7.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(4.4F, 15.0F, 5.0F);
			right_leg.setTextureOffset(0, 51).addBox(-3.4F, -2.0F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(-6.0F, 1.0F, -2.0F);
			left_arm.setTextureOffset(40, 29).addBox(-7.0F, -2.0F, -4.0F, 7.0F, 22.0F, 7.0F, 0.0F, true);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(-4.4F, 15.0F, 5.0F);
			left_leg.setTextureOffset(0, 51).addBox(-2.6F, -2.0F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
			left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(MeteoriteGolemEntity.CustomEntity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			int i = e.getAttackTimer();
			if (i > 0) {
				this.right_arm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) i - 1, 10.0F);
				this.left_arm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float) i - 1, 10.0F);
			}
		}
	}
}
