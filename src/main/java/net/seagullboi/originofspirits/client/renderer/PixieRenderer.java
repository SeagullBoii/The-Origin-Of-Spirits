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

import net.seagullboi.originofspirits.entity.PixieEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class PixieRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(PixieEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelpixie(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						PixieEntity.CustomEntity pix = (PixieEntity.CustomEntity) entity;
						return new ResourceLocation("originofspirits:textures/pixie" + String.valueOf(pix.getVariant()) + ".png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelpixie extends EntityModel<PixieEntity.CustomEntity> {
		private final ModelRenderer right_wing;
		private final ModelRenderer cube_r1;
		private final ModelRenderer left_wing;
		private final ModelRenderer cube_r2;
		private final ModelRenderer body;

		public Modelpixie() {
			textureWidth = 32;
			textureHeight = 32;
			right_wing = new ModelRenderer(this);
			right_wing.setRotationPoint(2.4F, 20.0F, 2.0F);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(1.6F, 3.0F, -7.0F);
			right_wing.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 0.4363F, 0.0F);
			cube_r1.setTextureOffset(12, 10).addBox(-4.4F, -8.0F, 6.0F, 0.0F, 8.0F, 6.0F, 0.0F, false);
			left_wing = new ModelRenderer(this);
			left_wing.setRotationPoint(-2.4F, 20.0F, 2.0F);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(-1.6F, 3.0F, -7.0F);
			left_wing.addChild(cube_r2);
			setRotationAngle(cube_r2, 0.0F, -0.4363F, 0.0F);
			cube_r2.setTextureOffset(0, 10).addBox(4.4F, -8.0F, 6.0F, 0.0F, 8.0F, 6.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 19.0F, -1.0F);
			body.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			right_wing.render(matrixStack, buffer, packedLight, packedOverlay);
			left_wing.render(matrixStack, buffer, packedLight, packedOverlay);
			body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(PixieEntity.CustomEntity e, float f, float f1, float f2, float f3, float f4) {
			int i = e.getAnimRotation();
			this.right_wing.rotateAngleY = -1.0F + 0.75F * MathHelper.func_233021_e_((float) i - 0.5f, 5.0F);
			this.left_wing.rotateAngleY = -this.right_wing.rotateAngleY;
		}
	}
}
