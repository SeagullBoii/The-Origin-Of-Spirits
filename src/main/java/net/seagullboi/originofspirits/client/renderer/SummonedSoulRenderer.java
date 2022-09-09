package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.SummonedSoulEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class SummonedSoulRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(SummonedSoulEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelsummoned_soul(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/summoned_soul.png");
					}
				};
			});
		}
	}


	// Made with Blockbench 4.2.2
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelsummoned_soul extends EntityModel<SummonedSoulEntity.CustomEntity> {
		private final ModelRenderer body;
		private final ModelRenderer right_arm;
		private final ModelRenderer left_arm;

		public Modelsummoned_soul() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 14.5F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-5.0F, -9.5F, -5.0F, 10.0F, 19.0F, 10.0F, 0.0F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(4.9F, -1.0F, -2.0F);
			body.addChild(right_arm);
			right_arm.setTextureOffset(0, 29).addBox(0.1F, -1.5F, -6.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(-4.9F, -1.0F, -2.0F);
			body.addChild(left_arm);
			left_arm.setTextureOffset(0, 29).addBox(-3.1F, -1.5F, -6.0F, 3.0F, 3.0F, 8.0F, 0.0F, true);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(SummonedSoulEntity.CustomEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			float rotation = entityIn.getAnimPos();
			this.right_arm.rotateAngleX = 0 + rotation / 2;
			this.left_arm.rotateAngleX = 0 - rotation / 2;
		}
	}

}
