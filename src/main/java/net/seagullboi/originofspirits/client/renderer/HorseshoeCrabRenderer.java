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

import net.seagullboi.originofspirits.entity.HorseshoeCrabEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class HorseshoeCrabRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(HorseshoeCrabEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelhorseshoe_crab(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/horseshoe_crab_4.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelhorseshoe_crab extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer tail;

		public Modelhorseshoe_crab() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -6.0F, 7.0F, 3.0F, 7.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(1.0F, 0.0F, 0.0F);
			body.addChild(cube_r1);
			setRotationAngle(cube_r1, -0.2618F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(22, 11).addBox(-3.0F, -2.2F, 0.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(-1.2858F, -1.1F, 0.2025F);
			body.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, -0.3054F, 0.0F);
			cube_r2.setTextureOffset(0, 10).addBox(-3.5F, -0.9F, -5.4F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(1.1465F, -1.1F, 0.2472F);
			body.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 0.3054F, 0.0F);
			cube_r3.setTextureOffset(0, 10).addBox(2.6F, -0.9F, -5.1F, 2.0F, 2.0F, 7.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(-1.0F, -1.0F, 0.0F);
			body.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.0F, 0.0F, -0.3927F);
			cube_r4.setTextureOffset(11, 14).addBox(3.0F, 0.1F, -6.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(1.0F, -1.0F, 0.0F);
			body.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, 0.0F, 0.3927F);
			cube_r5.setTextureOffset(18, 17).addBox(-2.0F, -0.4F, -6.0F, 0.0F, 3.0F, 7.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 24.0F, 0.0F);
			tail.setTextureOffset(21, 3).addBox(0.0F, -1.0F, 1.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
			tail.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.tail.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}

}
