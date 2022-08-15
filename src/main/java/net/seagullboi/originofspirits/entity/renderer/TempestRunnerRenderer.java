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

import net.seagullboi.originofspirits.entity.TempestRunnerEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class TempestRunnerRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(TempestRunnerEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modeltempest_runner(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/tempest_runner.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modeltempest_runner extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer body;
		private final ModelRenderer left_wing;
		private final ModelRenderer legs;
		private final ModelRenderer right_wing;
		private final ModelRenderer leg1;
		private final ModelRenderer leg2;

		public Modeltempest_runner() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 15.0F, -4.0F);
			head.setTextureOffset(0, 26).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(16, 32).addBox(-2.0F, 0.0F, -1.5F, 4.0F, 3.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(0, 10).addBox(0.0F, -10.0F, 0.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(18, 0).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 16.0F, 0.0F);
			setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(18, 12).addBox(0.0F, 2.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F, false);
			left_wing = new ModelRenderer(this);
			left_wing.setRotationPoint(3.0F, 15.0F, -3.0F);
			setRotationAngle(left_wing, 0.0F, 0.1745F, 0.0F);
			left_wing.setTextureOffset(16, 6).addBox(0.0F, -2.0F, 0.0F, 1.0F, 4.0F, 8.0F, 0.0F, false);
			legs = new ModelRenderer(this);
			legs.setRotationPoint(0.0F, 21.5F, 0.0F);
			legs.setTextureOffset(26, 0).addBox(2.1F, -2.5F, -2.3F, 0.0F, 5.0F, 5.0F, 0.0F, false);
			legs.setTextureOffset(26, 0).addBox(0.1F, -2.5F, -2.3F, 0.0F, 5.0F, 5.0F, 0.0F, false);
			legs.setTextureOffset(26, 0).addBox(-2.1F, -2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, false);
			right_wing = new ModelRenderer(this);
			right_wing.setRotationPoint(-3.0F, 15.0F, -3.0F);
			setRotationAngle(right_wing, 0.0F, -0.1745F, 0.0F);
			right_wing.setTextureOffset(16, 6).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 8.0F, 0.0F, true);
			leg1 = new ModelRenderer(this);
			leg1.setRotationPoint(0.0F, 18.0F, -0.5F);
			leg1.setTextureOffset(32, 0).addBox(1.1F, 0.0F, -0.3F, 3.0F, 6.0F, 2.0F, 0.0F, false);
			leg1.setTextureOffset(32, 0).addBox(-3.9F, 0.0F, -0.3F, 3.0F, 6.0F, 2.0F, 0.0F, false);
			leg2 = new ModelRenderer(this);
			leg2.setRotationPoint(0.0F, 18.0F, -1.1F);
			leg2.setTextureOffset(32, 0).addBox(-1.4F, 0.0F, -1.3F, 3.0F, 6.0F, 2.0F, 0.0F, false);
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
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			left_wing.render(matrixStack, buffer, packedLight, packedOverlay);
			legs.render(matrixStack, buffer, packedLight, packedOverlay);
			right_wing.render(matrixStack, buffer, packedLight, packedOverlay);
			leg1.render(matrixStack, buffer, packedLight, packedOverlay);
			leg2.render(matrixStack, buffer, packedLight, packedOverlay);
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
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.legs.rotateAngleX = f2;
		}
	}
}
