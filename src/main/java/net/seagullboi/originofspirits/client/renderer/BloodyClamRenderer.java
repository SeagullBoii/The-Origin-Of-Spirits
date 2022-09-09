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

import net.seagullboi.originofspirits.entity.BloodyClamEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class BloodyClamRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(BloodyClamEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelbloody_clam(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/bloody_clam.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelbloody_clam extends EntityModel<Entity> {
		private final ModelRenderer bb_main;
		private final ModelRenderer bb_main_r1;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer cube_r6;
		private final ModelRenderer cube_r7;
		private final ModelRenderer cube_r8;
		private final ModelRenderer mouth1;
		private final ModelRenderer cube_r9_r1;
		private final ModelRenderer cube_r7_r1;
		private final ModelRenderer cube_r6_r1;
		private final ModelRenderer mouth2;
		private final ModelRenderer cube_r3_r1;
		private final ModelRenderer cube_r6_r2;

		public Modelbloody_clam() {
			textureWidth = 32;
			textureHeight = 32;
			bb_main = new ModelRenderer(this);
			bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
			setRotationAngle(bb_main, 1.5708F, -0.5672F, -1.5708F);
			bb_main.setTextureOffset(10, 7).addBox(0.6119F, 1.6302F, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(1.1119F, 0.3302F, -4.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 0).addBox(1.1119F, 0.3302F, 3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
			bb_main_r1 = new ModelRenderer(this);
			bb_main_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(bb_main_r1);
			setRotationAngle(bb_main_r1, 0.0F, 0.0F, -0.5236F);
			bb_main_r1.setTextureOffset(10, 7).addBox(0.9119F, 2.6302F, -3.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r1);
			setRotationAngle(cube_r1, -3.1416F, 0.0F, 0.4363F);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r2);
			setRotationAngle(cube_r2, -3.1416F, 0.0F, -0.4363F);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r4);
			setRotationAngle(cube_r4, 3.1416F, 0.0F, 0.4363F);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, -1.5708F, 0.4363F);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 0.0F, -0.4363F);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r7);
			setRotationAngle(cube_r7, 3.1416F, 0.0F, -0.4363F);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(0.0F, 0.0F, 0.0F);
			bb_main.addChild(cube_r8);
			setRotationAngle(cube_r8, 0.0F, 1.5708F, -0.4363F);
			mouth1 = new ModelRenderer(this);
			mouth1.setRotationPoint(0.0F, 22.0F, 2.0F);
			setRotationAngle(mouth1, 1.0472F, 0.0F, 0.0F);
			cube_r9_r1 = new ModelRenderer(this);
			cube_r9_r1.setRotationPoint(0.0F, 2.0F, 1.0F);
			mouth1.addChild(cube_r9_r1);
			setRotationAngle(cube_r9_r1, 1.4399F, 0.0F, 0.0F);
			cube_r9_r1.setTextureOffset(0, 22).addBox(-4.0F, -3.5774F, 7.3063F, 8.0F, 1.0F, 0.0F, 0.0F, false);
			cube_r9_r1.setTextureOffset(0, 17).addBox(-4.0F, -2.5774F, 6.9063F, 8.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7_r1 = new ModelRenderer(this);
			cube_r7_r1.setRotationPoint(0.0F, 2.0F, 1.0F);
			mouth1.addChild(cube_r7_r1);
			setRotationAngle(cube_r7_r1, -1.5708F, 0.1309F, 1.5708F);
			cube_r7_r1.setTextureOffset(18, 14).addBox(-7.0937F, 1.5774F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r7_r1.setTextureOffset(18, 14).addBox(-7.0937F, 1.5774F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
			cube_r6_r1 = new ModelRenderer(this);
			cube_r6_r1.setRotationPoint(0.0F, 2.0F, 1.0F);
			mouth1.addChild(cube_r6_r1);
			setRotationAngle(cube_r6_r1, 1.5708F, -0.1309F, -1.5708F);
			cube_r6_r1.setTextureOffset(0, 0).addBox(0.9063F, -0.5774F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
			cube_r6_r1.setTextureOffset(18, 3).addBox(0.9063F, -1.5774F, 3.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
			cube_r6_r1.setTextureOffset(0, 7).addBox(6.9063F, -1.5774F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, false);
			cube_r6_r1.setTextureOffset(18, 0).addBox(0.9063F, -1.5774F, -4.0F, 6.0F, 2.0F, 1.0F, 0.0F, false);
			mouth2 = new ModelRenderer(this);
			mouth2.setRotationPoint(0.0F, 24.0F, 2.0F);
			cube_r3_r1 = new ModelRenderer(this);
			cube_r3_r1.setRotationPoint(0.0F, 0.0F, 1.0F);
			mouth2.addChild(cube_r3_r1);
			setRotationAngle(cube_r3_r1, 0.0F, -1.5708F, 0.0F);
			cube_r3_r1.setTextureOffset(0, 0).addBox(-6.9063F, -0.5774F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, true);
			cube_r3_r1.setTextureOffset(18, 3).addBox(-6.9063F, -1.5774F, -4.0F, 6.0F, 2.0F, 1.0F, 0.0F, true);
			cube_r3_r1.setTextureOffset(18, 14).addBox(-7.9063F, -2.5226F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, true);
			cube_r3_r1.setTextureOffset(18, 14).addBox(-7.9063F, -2.5226F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, true);
			cube_r3_r1.setTextureOffset(0, 7).addBox(-7.9063F, -1.5774F, -4.0F, 1.0F, 2.0F, 8.0F, 0.0F, true);
			cube_r3_r1.setTextureOffset(18, 3).addBox(-6.9063F, -1.5774F, 3.0F, 6.0F, 2.0F, 1.0F, 0.0F, true);
			cube_r6_r2 = new ModelRenderer(this);
			cube_r6_r2.setRotationPoint(0.0F, 0.0F, 1.0F);
			mouth2.addChild(cube_r6_r2);
			setRotationAngle(cube_r6_r2, -3.1416F, 0.0F, 3.1416F);
			cube_r6_r2.setTextureOffset(0, 22).addBox(-4.0F, -3.5774F, 7.5063F, 8.0F, 1.0F, 0.0F, 0.0F, true);
			cube_r6_r2.setTextureOffset(0, 17).addBox(-4.0F, -2.5774F, 6.9063F, 8.0F, 1.0F, 1.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
			mouth1.render(matrixStack, buffer, packedLight, packedOverlay);
			mouth2.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.mouth2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.mouth1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		}
	}

}
