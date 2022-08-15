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

import net.seagullboi.originofspirits.entity.CaikleEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class CaikleRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(CaikleEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelcaickle(), 0.5f) {
					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/caickle.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 4.1.1
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelcaickle extends EntityModel<CaikleEntity.CustomEntity> {
		private double biteAnim = 0f;
		private boolean biteAnimation = false;
		private boolean attacked;
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer jaw_top;
		private final ModelRenderer jaw_bottom;
		private final ModelRenderer tail1;
		private final ModelRenderer tail2;
		private final ModelRenderer tail3;
		private final ModelRenderer fr;
		private final ModelRenderer bl;
		private final ModelRenderer fl;
		private final ModelRenderer br;

		public Modelcaickle() {
			textureWidth = 64;
			textureHeight = 64;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 24.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-3.5F, -8.0F, -9.0F, 7.0F, 5.0F, 15.0F, 0.0F, false);
			body.setTextureOffset(0, 5).addBox(0.1F, -19.0F, -9.0F, 0.0F, 11.0F, 15.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -6.0F, -9.0F);
			body.addChild(head);
			setRotationAngle(head, 0.0436F, 0.0F, 0.0F);
			head.setTextureOffset(29, 0).addBox(-3.5F, 1.5F, -9.0F, 7.0F, 1.0F, 9.0F, 0.0F, false);
			head.setTextureOffset(44, 13).addBox(0.1F, -10.0F, -9.0F, 0.0F, 9.0F, 9.0F, 0.0F, false);
			jaw_top = new ModelRenderer(this);
			jaw_top.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.addChild(jaw_top);
			jaw_top.setTextureOffset(21, 22).addBox(-3.5F, -1.5F, -9.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);
			jaw_top.setTextureOffset(35, 11).addBox(-2.5F, -0.5F, -18.0F, 5.0F, 2.0F, 9.0F, 0.0F, false);
			jaw_top.setTextureOffset(0, 0).addBox(-2.5F, -1.5F, -18.0F, 5.0F, 1.0F, 2.0F, 0.0F, false);
			jaw_bottom = new ModelRenderer(this);
			jaw_bottom.setRotationPoint(0.0F, 2.0F, -9.0F);
			head.addChild(jaw_bottom);
			jaw_bottom.setTextureOffset(0, 44).addBox(-2.5F, -0.5F, -9.0F, 5.0F, 1.0F, 9.0F, 0.0F, false);
			jaw_bottom.setTextureOffset(28, 34).addBox(-2.5F, -2.5F, -9.0F, 5.0F, 2.0F, 9.0F, 0.0F, false);
			tail1 = new ModelRenderer(this);
			tail1.setRotationPoint(-0.5F, -5.0F, 6.0F);
			body.addChild(tail1);
			tail1.setTextureOffset(0, 31).addBox(-2.0F, -2.0F, 0.0F, 5.0F, 4.0F, 9.0F, 0.0F, false);
			tail1.setTextureOffset(28, 36).addBox(0.6F, -11.0F, 0.0F, 0.0F, 9.0F, 9.0F, 0.0F, false);
			tail2 = new ModelRenderer(this);
			tail2.setRotationPoint(0.0F, 0.0F, 9.0F);
			tail1.addChild(tail2);
			tail2.setTextureOffset(37, 45).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 3.0F, 9.0F, 0.0F, false);
			tail2.setTextureOffset(0, 46).addBox(0.6F, -8.0F, 0.0F, 0.0F, 7.0F, 8.0F, 0.0F, false);
			tail3 = new ModelRenderer(this);
			tail3.setRotationPoint(0.0F, 1.0F, 8.0F);
			tail2.addChild(tail3);
			tail3.setTextureOffset(47, 31).addBox(0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
			tail3.setTextureOffset(0, 0).addBox(0.6F, -7.0F, 0.0F, 0.0F, 6.0F, 6.0F, 0.0F, false);
			fr = new ModelRenderer(this);
			fr.setRotationPoint(-3.5F, -4.0F, -6.0F);
			body.addChild(fr);
			fr.setTextureOffset(19, 34).addBox(-0.7F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			fr.setTextureOffset(0, 3).addBox(-1.3F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, false);
			bl = new ModelRenderer(this);
			bl.setRotationPoint(3.5F, -4.0F, 5.0F);
			body.addChild(bl);
			bl.setTextureOffset(19, 34).addBox(-1.3F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
			bl.setTextureOffset(0, 3).addBox(-1.7F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, true);
			fl = new ModelRenderer(this);
			fl.setRotationPoint(3.5F, -4.0F, -6.0F);
			body.addChild(fl);
			fl.setTextureOffset(19, 34).addBox(-1.3F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
			fl.setTextureOffset(0, 3).addBox(-1.7F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, true);
			br = new ModelRenderer(this);
			br.setRotationPoint(-2.5F, -4.0F, 5.0F);
			body.addChild(br);
			br.setTextureOffset(19, 34).addBox(-1.3F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
			br.setTextureOffset(0, 3).addBox(-1.7F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, 0.0F, true);
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

		public void setRotationAngles(CaikleEntity.CustomEntity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.br.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.tail1.rotateAngleY = MathHelper.cos(f * 0.1F + (float) Math.PI) * f1;
			this.tail2.rotateAngleY = MathHelper.cos(f * 0.1F + (float) Math.PI) * f1;
			this.tail3.rotateAngleY = MathHelper.cos(f * 0.1F + (float) Math.PI) * f1;
			this.fl.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.bl.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.fr.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			int i = e.getAttackTimer();
			if (i > 0) {
				if (!biteAnimation && attacked) {
					this.biteAnim = this.biteAnim + 0.05;
				}
				attacked = true;
			}
			if (biteAnimation && attacked) {
				this.biteAnim = this.biteAnim - 0.05;
			}
			this.jaw_bottom.rotateAngleX = (float) this.biteAnim;
			if (biteAnim >= 0.5 && !biteAnimation) {
				biteAnimation = true;
			}
			if (biteAnim <= 0 && biteAnimation) {
				biteAnimation = false;
				attacked = false;
			}
		}
	}
}
