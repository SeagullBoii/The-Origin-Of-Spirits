package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.BoxJellyfishEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class BoxJellyfishRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(BoxJellyfishEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new box_jellyfish(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/box_jellyfish.png");
					}
				};
			});
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			BoxJellyfishEntity.CustomEntity entity = (BoxJellyfishEntity.CustomEntity) entitylivingbaseIn;
			float f = entity.getAnimPos();
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/box_jellyfish.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			matrixStackIn.scale(1 + f, 1 + f, 1 + f);
		}
	}

	public static class box_jellyfish extends EntityModel<BoxJellyfishEntity.CustomEntity> {
		private final ModelRenderer head;
		private final ModelRenderer jellyfish;
		private final ModelRenderer superior_tentacles1;
		private final ModelRenderer superior_tentacles2;
		private final ModelRenderer superior_tentacles3;
		private final ModelRenderer superior_tentacles4;

		public box_jellyfish() {
			textureWidth = 64;
			textureHeight = 64;

			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 20.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(-4.5F, -4.0F, -4.5F, 9.0F, 8.0F, 9.0F, 0.0F, false);

			jellyfish = new ModelRenderer(this);
			jellyfish.setRotationPoint(-0.5F, 20.0F, 0.0F);


			superior_tentacles1 = new ModelRenderer(this);
			superior_tentacles1.setRotationPoint(4.5F, 3.9F, 0.0F);
			jellyfish.addChild(superior_tentacles1);
			superior_tentacles1.setTextureOffset(0, 30).addBox(0.0F, 0.1F, -5.5F, 0.0F, 13.0F, 11.0F, 0.0F, false);

			superior_tentacles2 = new ModelRenderer(this);
			superior_tentacles2.setRotationPoint(-3.5F, 3.9F, 0.0F);
			jellyfish.addChild(superior_tentacles2);
			superior_tentacles2.setTextureOffset(0, 30).addBox(0.0F, 0.1F, -5.5F, 0.0F, 13.0F, 11.0F, 0.0F, true);

			superior_tentacles3 = new ModelRenderer(this);
			superior_tentacles3.setRotationPoint(0.5F, 3.9F, 4.0F);
			jellyfish.addChild(superior_tentacles3);
			setRotationAngle(superior_tentacles3, 0.0F, -1.5708F, 0.0F);
			superior_tentacles3.setTextureOffset(0, 30).addBox(0.0F, 0.1F, -5.5F, 0.0F, 13.0F, 11.0F, 0.0F, false);

			superior_tentacles4 = new ModelRenderer(this);
			superior_tentacles4.setRotationPoint(0.5F, 3.9F, -4.0F);
			jellyfish.addChild(superior_tentacles4);
			setRotationAngle(superior_tentacles4, 0.0F, -1.5708F, 0.0F);
			superior_tentacles4.setTextureOffset(0, 30).addBox(0.0F, 0.1F, -5.5F, 0.0F, 13.0F, 11.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
						   float alpha) {


			head.render(matrixStack, buffer, packedLight, packedOverlay);
			jellyfish.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(BoxJellyfishEntity.CustomEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			float rotation = entityIn.getAnimPos();
			this.head.rotateAngleY = ageInTicks / 40;
			this.jellyfish.rotateAngleY = this.head.rotateAngleY;
			this.superior_tentacles1.rotateAngleZ = rotation / 2;
			this.superior_tentacles2.rotateAngleZ = -rotation / 2;
			this.superior_tentacles3.rotateAngleY = 1.5708F + rotation / 2;
			this.superior_tentacles4.rotateAngleY = 1.5708F - rotation / 2;

		}
	}
}
