package net.seagullboi.originofspirits.entity.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.seagullboi.originofspirits.entity.ClamEmptyEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class ClamEmptyRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(ClamEmptyEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelclam_empty(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/clam.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelclam_empty extends EntityModel<Entity> {
		private final ModelRenderer bb_main;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer cube_r6;
		private final ModelRenderer cube_r7;
		private final ModelRenderer cube_r8;

		public Modelclam_empty() {
			textureWidth = 32;
			textureHeight = 32;
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(10, 7).addBox(-1.0F, -0.9F, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-0.5F, -2.2F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(-0.5F, -2.2F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r1);
			setRotationAngle(cube_r1, -3.1416F, 0.0F, 0.4363F);
			cube_r1.setTextureOffset(18, 14).addBox(-6.9063F, 1.5774F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, true);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r2);
			setRotationAngle(cube_r2, -3.1416F, 0.0F, -0.4363F);
			cube_r2.setTextureOffset(18, 14).addBox(0.9063F, 1.5774F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
			cube_r3.setTextureOffset(18, 3).addBox(-6.9063F, -1.5774F, 3.0F, 6.0F, 2.0F, 1.0F, 0.0F, true);
			cube_r3.setTextureOffset(18, 3).addBox(-6.9063F, -1.5774F, -4.0F, 6.0F, 2.0F, 1.0F, 0.0F, true);
			cube_r3.setTextureOffset(0, 0).addBox(-6.9063F, -0.5774F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, true);
			cube_r3.setTextureOffset(0, 7).addBox(-7.9063F, -1.5774F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, true);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, 3.1416F, 0.0F, 0.4363F);
			cube_r4.setTextureOffset(18, 14).addBox(-6.9063F, 1.5774F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, true);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, -1.5708F, 0.4363F);
			cube_r5.setTextureOffset(0, 17).addBox(-4.0F, -2.5774F, 6.9063F, 8.0F, 1.0F, 1.0F, 0.0F, true);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 0.0F, -0.4363F);
			cube_r6.setTextureOffset(18, 0).addBox(0.9063F, -1.5774F, -4.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 7).addBox(6.9063F, -1.5774F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, false);
			cube_r6.setTextureOffset(18, 3).addBox(0.9063F, -1.5774F, 3.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
			cube_r6.setTextureOffset(0, 0).addBox(0.9063F, -0.5774F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r7);
			setRotationAngle(cube_r7, 3.1416F, 0.0F, -0.4363F);
			cube_r7.setTextureOffset(18, 14).addBox(0.9063F, 1.5774F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r8);
			setRotationAngle(cube_r8, 0.0F, 1.5708F, -0.4363F);
			cube_r8.setTextureOffset(0, 17).addBox(-4.0F, -2.5774F, 6.9063F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

		}
	}

}
