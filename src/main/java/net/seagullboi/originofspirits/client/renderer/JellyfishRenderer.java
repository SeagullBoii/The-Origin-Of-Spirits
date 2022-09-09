package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.JellyfishEntity;
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
public class JellyfishRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(JellyfishEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new jellyfish(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						JellyfishEntity.CustomEntity _jish = (JellyfishEntity.CustomEntity) entity;
						return new ResourceLocation("originofspirits:textures/entity/jellyfish/jellyfish" + String.valueOf(_jish.getColor()) + ".png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class jellyfish extends EntityModel<JellyfishEntity.CustomEntity> {
		private final ModelRenderer body;
		private final ModelRenderer tentacle1;
		private final ModelRenderer tentacle2;
		private final ModelRenderer cube_r1;
		private final ModelRenderer tentacle3;
		private final ModelRenderer tentacle4;

		public jellyfish() {
			textureWidth = 64;
			textureHeight = 64;

			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(13, 32).addBox(-0.5F, -6.0F, -1.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);
			body.setTextureOffset(0, 0).addBox(-4.5F, -6.0F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
			body.setTextureOffset(24, 43).addBox(-1.5F, -6.0F, -0.5F, 2.0F, 5.0F, 2.0F, 0.0F, false);
			body.setTextureOffset(26, 44).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

			tentacle1 = new ModelRenderer(this);
			tentacle1.setRotationPoint(0.0F, 0.0F, -3.5F);
			body.addChild(tentacle1);
			tentacle1.setTextureOffset(0, 19).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 12.0F, 0.0F, 0.0F, false);

			tentacle2 = new ModelRenderer(this);
			tentacle2.setRotationPoint(0.0F, 0.0F, 3.5F);
			body.addChild(tentacle2);


			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(-0.5F, 0.0F, -3.0F);
			tentacle2.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 3.1416F, 0.0F);
			cube_r1.setTextureOffset(0, 19).addBox(-5.0F, 0.0F, -3.0F, 9.0F, 12.0F, 0.0F, 0.0F, false);

			tentacle3 = new ModelRenderer(this);
			tentacle3.setRotationPoint(-3.5F, 0.0F, 0.0F);
			body.addChild(tentacle3);
			setRotationAngle(tentacle3, 0.0F, 1.5708F, 0.0F);
			tentacle3.setTextureOffset(0, 19).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 12.0F, 0.0F, 0.0F, false);

			tentacle4 = new ModelRenderer(this);
			tentacle4.setRotationPoint(3.5F, 0.0F, 0.0F);
			body.addChild(tentacle4);
			setRotationAngle(tentacle4, 0.0F, -1.5708F, 0.0F);
			tentacle4.setTextureOffset(0, 19).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 12.0F, 0.0F, 0.0F, true);
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

		public void setRotationAngles(JellyfishEntity.CustomEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			float rotation = entityIn.getAnimPos();
			this.body.rotateAngleY = ageInTicks / 40;
			this.tentacle1.rotateAngleX = rotation / 2;
			this.tentacle2.rotateAngleX = -rotation / 2;
			this.tentacle3.rotateAngleZ = rotation / 2;
			this.tentacle4.rotateAngleZ = -rotation / 2;
		}
	}
}
