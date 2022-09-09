package net.seagullboi.originofspirits.client.renderer;

import net.minecraft.util.math.MathHelper;
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

import net.seagullboi.originofspirits.entity.FlyingFishEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class FlyingFishRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(FlyingFishEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelflying_fish(), 0.37f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/flying_fish.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.5
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelflying_fish extends EntityModel<FlyingFishEntity.CustomEntity> {
		private final ModelRenderer body_front;
		private final ModelRenderer head;
		private final ModelRenderer right_stache;
		private final ModelRenderer left_stache;
		private final ModelRenderer body_back;
		private final ModelRenderer fin_back_2;
		private final ModelRenderer tail;
		private final ModelRenderer fin_back_1;
		private final ModelRenderer fin_left;
		private final ModelRenderer wing_right;
		private final ModelRenderer wing_left;
		private final ModelRenderer fin_right;

		public Modelflying_fish() {
			textureWidth = 64;
			textureHeight = 64;
			body_front = new ModelRenderer(this);
			body_front.setRotationPoint(0.0F, 20.0F, -5.0F);
			body_front.setTextureOffset(0, 13).addBox(-2.5F, -2.5F, -4.0F, 5.0F, 5.0F, 8.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, -4.0F);
			body_front.addChild(head);
			head.setTextureOffset(23, 23).addBox(-2.5F, -2.5F, -3.0F, 5.0F, 5.0F, 3.0F, 0.0F, false);
			right_stache = new ModelRenderer(this);
			right_stache.setRotationPoint(-2.5F, 1.5F, -1.0F);
			head.addChild(right_stache);
			setRotationAngle(right_stache, -1.5708F, 0.0F, -0.7854F);
			right_stache.setTextureOffset(0, 13).addBox(-4.0F, -2.0F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, false);
			left_stache = new ModelRenderer(this);
			left_stache.setRotationPoint(2.5F, 1.5F, -1.0F);
			head.addChild(left_stache);
			setRotationAngle(left_stache, -1.5708F, 0.0F, 0.7854F);
			left_stache.setTextureOffset(0, 13).addBox(0.0F, -2.0F, 0.0F, 4.0F, 3.0F, 0.0F, 0.0F, true);
			body_back = new ModelRenderer(this);
			body_back.setRotationPoint(0.0F, 0.0F, 4.0F);
			body_front.addChild(body_back);
			body_back.setTextureOffset(0, 0).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 8.0F, 0.0F, false);
			fin_back_2 = new ModelRenderer(this);
			fin_back_2.setRotationPoint(0.0F, -4.5F, -1.0F);
			body_back.addChild(fin_back_2);
			fin_back_2.setTextureOffset(0, 2).addBox(0.0F, 0.0F, 1.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);
			fin_back_2.setTextureOffset(0, 0).addBox(0.0F, 7.0F, 1.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);
			tail = new ModelRenderer(this);
			tail.setRotationPoint(0.0F, 0.0F, 8.0F);
			body_back.addChild(tail);
			tail.setTextureOffset(0, 17).addBox(0.0F, -3.5F, -3.0F, 0.0F, 7.0F, 9.0F, 0.0F, false);
			fin_back_1 = new ModelRenderer(this);
			fin_back_1.setRotationPoint(0.0F, -4.5F, 1.0F);
			body_front.addChild(fin_back_1);
			fin_back_1.setTextureOffset(26, 1).addBox(0.0F, 0.0F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
			fin_back_1.setTextureOffset(18, 25).addBox(0.0F, 7.0F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
			fin_left = new ModelRenderer(this);
			fin_left.setRotationPoint(2.0F, 1.0F, -3.0F);
			body_front.addChild(fin_left);
			setRotationAngle(fin_left, 0.0F, -0.6109F, 0.0F);
			fin_left.setTextureOffset(30, 31).addBox(-0.5F, -4.5F, 0.0F, 5.0F, 7.0F, 0.0F, 0.0F, true);
			wing_right = new ModelRenderer(this);
			wing_right.setRotationPoint(0.0F, 0.0F, -1.0F);
			body_front.addChild(wing_right);
			setRotationAngle(wing_right, 0.0F, 0.6109F, 0.0F);
			wing_right.setTextureOffset(18, 13).addBox(-11.5F, -3.5F, 0.0F, 10.0F, 7.0F, 0.0F, 0.0F, false);
			wing_left = new ModelRenderer(this);
			wing_left.setRotationPoint(0.0F, 0.0F, -1.0F);
			body_front.addChild(wing_left);
			setRotationAngle(wing_left, 0.0F, -0.6109F, 0.0F);
			wing_left.setTextureOffset(18, 13).addBox(1.5F, -3.5F, 0.0F, 10.0F, 7.0F, 0.0F, 0.0F, true);
			fin_right = new ModelRenderer(this);
			fin_right.setRotationPoint(-2.0F, 1.0F, -3.0F);
			body_front.addChild(fin_right);
			setRotationAngle(fin_right, 0.0F, 0.6109F, 0.0F);
			fin_right.setTextureOffset(30, 31).addBox(-4.5F, -4.5F, 0.0F, 5.0F, 7.0F, 0.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			body_front.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(FlyingFishEntity.CustomEntity e, float f, float f1, float ageInTicks, float f3, float f4) {
			f = 1.0F;
			this.body_front.rotateAngleY = -f * 0.40F * MathHelper.sin(0.6F * ageInTicks);
			this.wing_left.rotateAngleY = -f * 0.5F * MathHelper.sin(0.6F * ageInTicks);
			this.wing_right.rotateAngleY = -f * 0.5F * MathHelper.sin(0.6F * ageInTicks);
			this.body_back.rotateAngleY = -f * 0.40F * MathHelper.sin(0.6F * ageInTicks);
			this.tail.rotateAngleY = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}

}
