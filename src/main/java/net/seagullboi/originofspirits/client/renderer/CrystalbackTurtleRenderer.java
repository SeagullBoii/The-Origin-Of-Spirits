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

import net.seagullboi.originofspirits.entity.CrystalbackTurtleEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class CrystalbackTurtleRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(CrystalbackTurtleEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelcrystalback_tortoise(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/crystalback_tortoise.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelcrystalback_tortoise extends EntityModel<Entity> {
		private final ModelRenderer fr_leg;
		private final ModelRenderer fl_leg;
		private final ModelRenderer br_leg;
		private final ModelRenderer bl_leg;
		private final ModelRenderer head;
		private final ModelRenderer body;
		private final ModelRenderer cube_r1;
		private final ModelRenderer tail;
		private final ModelRenderer cube_r2;

		public Modelcrystalback_tortoise() {
			textureWidth = 128;
			textureHeight = 128;
			fr_leg = new ModelRenderer(this);
			fr_leg.setRotationPoint(6.0F, 16.0F, -9.0F);
			fr_leg.setTextureOffset(53, 0).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);
			fl_leg = new ModelRenderer(this);
			fl_leg.setRotationPoint(-6.0F, 16.0F, -9.0F);
			fl_leg.setTextureOffset(53, 0).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 8.0F, 5.0F, 0.0F, true);
			br_leg = new ModelRenderer(this);
			br_leg.setRotationPoint(-6.0F, 16.0F, 9.0F);
			br_leg.setTextureOffset(53, 0).addBox(-3.0F, 0.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, true);
			bl_leg = new ModelRenderer(this);
			bl_leg.setRotationPoint(6.0F, 16.0F, 9.0F);
			bl_leg.setTextureOffset(53, 0).addBox(-2.0F, 0.0F, -3.0F, 5.0F, 8.0F, 5.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 18.0F, -10.0F);
			head.setTextureOffset(47, 31).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);
			head.setTextureOffset(0, 51).addBox(-4.0F, 1.0F, -7.6F, 8.0F, 2.0F, 8.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 16.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-8.0F, -5.0F, -10.0F, 16.0F, 10.0F, 20.0F, 0.0F, false);
			body.setTextureOffset(0, 31).addBox(-7.0F, 5.0F, -9.0F, 14.0F, 1.0F, 18.0F, 0.0F, false);
			body.setTextureOffset(96, -16).addBox(0.0F, -21.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 8.0F, -4.0F);
			body.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
			cube_r1.setTextureOffset(96, 0).addBox(-4.0F, -29.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 18.0F, 10.0F);
			setRotationAngle(tail, 0.2182F, 0.0F, 0.0F);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 6.0F, -14.0F);
			tail.addChild(cube_r2);
			setRotationAngle(cube_r2, -0.48F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(31, 49).addBox(0.0F, -16.0F, 9.0F, 0.0F, 8.0F, 13.0F, 0.0F, false);
			cube_r2.setTextureOffset(44, 51).addBox(-2.0F, -14.0F, 9.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			fr_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			fl_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			br_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			bl_leg.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			tail.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.bl_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.br_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.fl_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.fr_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}

}
