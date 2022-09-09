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

import net.seagullboi.originofspirits.entity.GeneralEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class GeneralRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(GeneralEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelgeneral(), 0.5f) {

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/general.png");
					}
				};
			});
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelgeneral extends EntityModel<Entity> {
		private final ModelRenderer general;
		private final ModelRenderer body;
		private final ModelRenderer head;
		private final ModelRenderer nose;
		private final ModelRenderer hat;
		private final ModelRenderer hat_handle;
		private final ModelRenderer hat_handle2;
		private final ModelRenderer right_arm;
		private final ModelRenderer heartstealer;
		private final ModelRenderer left_arm;
		private final ModelRenderer medal;
		private final ModelRenderer left_leg;
		private final ModelRenderer right_leg;

		public Modelgeneral() {
			textureWidth = 128;
			textureHeight = 128;
			general = new ModelRenderer(this);
			general.setRotationPoint(0.0F, 24.0F, 0.0F);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, -12.0F, 0.0F);
			general.addChild(body);
			body.setTextureOffset(22, 34).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
			body.setTextureOffset(0, 16).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, -12.0F, 0.0F);
			body.addChild(head);
			head.setTextureOffset(28, 16).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
			nose = new ModelRenderer(this);
			nose.setRotationPoint(0.0F, -2.0F, 0.0F);
			head.addChild(nose);
			nose.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
			hat = new ModelRenderer(this);
			hat.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.addChild(hat);
			hat.setTextureOffset(0, 0).addBox(-5.0F, -12.0F, -4.5F, 10.0F, 7.0F, 9.0F, 0.0F, false);
			hat.setTextureOffset(43, 0).addBox(-6.0F, -5.3F, -5.5F, 12.0F, 0.0F, 11.0F, 0.0F, false);
			hat_handle = new ModelRenderer(this);
			hat_handle.setRotationPoint(5.0F, -5.0F, 0.0F);
			hat.addChild(hat_handle);
			setRotationAngle(hat_handle, 0.0F, 0.0F, -0.3927F);
			hat_handle.setTextureOffset(0, 31).addBox(0.0F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, false);
			hat_handle.setTextureOffset(0, 31).addBox(-0.1F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, false);
			hat_handle.setTextureOffset(0, 31).addBox(-0.2F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, false);
			hat_handle2 = new ModelRenderer(this);
			hat_handle2.setRotationPoint(-5.0F, -5.0F, 0.0F);
			hat.addChild(hat_handle2);
			setRotationAngle(hat_handle2, 0.0F, 0.0F, 0.3927F);
			hat_handle2.setTextureOffset(0, 31).addBox(0.0F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, true);
			hat_handle2.setTextureOffset(0, 31).addBox(0.1F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, true);
			hat_handle2.setTextureOffset(0, 31).addBox(0.2F, 0.0F, -4.5F, 0.0F, 7.0F, 9.0F, 0.0F, true);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-4.0F, -11.0F, 0.0F);
			body.addChild(right_arm);
			setRotationAngle(right_arm, 0.0F, 0.0F, 0.1309F);
			right_arm.setTextureOffset(14, 52).addBox(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			right_arm.setTextureOffset(66, 0).addBox(-3.5F, -1.9F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, true);
			heartstealer = new ModelRenderer(this);
			heartstealer.setRotationPoint(-2.0F, 11.0F, 3.5F);
			right_arm.addChild(heartstealer);
			setRotationAngle(heartstealer, 1.5708F, -1.0036F, -1.5708F);
			heartstealer.setTextureOffset(118, 0).addBox(-4.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-5.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-9.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-10.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-10.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-11.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-12.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-12.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-13.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-14.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-15.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-16.0F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-17.0F, -15.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-18.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-19.0F, -17.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-20.0F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-21.0F, -19.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-19.0F, -21.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-18.0F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-17.0F, -19.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-16.0F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-15.0F, -17.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-14.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-13.0F, -15.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-12.0F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-11.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-10.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-9.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-8.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-7.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-6.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-5.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-6.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-7.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-7.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-9.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-9.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-8.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-10.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-9.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-10.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-11.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-11.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-12.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-13.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-14.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-15.0F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-16.0F, -15.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-17.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-18.0F, -17.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-19.0F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-20.0F, -19.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-21.0F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-8.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-9.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-10.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-9.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-8.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 8).addBox(-8.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 8).addBox(-10.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-11.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-12.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 4).addBox(-13.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-14.0F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-15.0F, -15.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-16.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-15.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-14.0F, -15.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-12.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-11.0F, -12.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-13.0F, -14.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-16.0F, -17.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-17.0F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-17.0F, -17.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-18.0F, -18.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-19.0F, -19.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-20.0F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-20.0F, -21.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-19.0F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 10).addBox(-21.0F, -21.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 12).addBox(-18.0F, -19.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 8).addBox(-9.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-22.0F, -20.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-22.0F, -21.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-22.0F, -22.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-21.0F, -22.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 4).addBox(-20.0F, -22.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-6.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-7.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-7.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-8.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-9.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-9.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-8.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-8.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 4).addBox(-7.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 4).addBox(-7.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 4).addBox(-6.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-6.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-3.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-3.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-4.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-5.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-10.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-9.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-8.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-8.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-7.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-6.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-5.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-4.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-3.0F, -10.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-2.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-2.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-3.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-4.0F, -7.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-5.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 0).addBox(-3.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-2.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 6).addBox(-5.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-4.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 8).addBox(-3.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-2.0F, -3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 6).addBox(-1.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(0.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 2).addBox(-1.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(122, 0).addBox(-2.0F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-3.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			heartstealer.setTextureOffset(118, 2).addBox(-4.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(4.0F, -11.0F, 0.0F);
			body.addChild(left_arm);
			setRotationAngle(left_arm, 0.0F, 0.0F, -0.1745F);
			left_arm.setTextureOffset(30, 52).addBox(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			left_arm.setTextureOffset(66, 0).addBox(0.5F, -1.9F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			medal = new ModelRenderer(this);
			medal.setRotationPoint(-3.6F, 0.0F, 0.1F);
			body.addChild(medal);
			setRotationAngle(medal, -0.1309F, 0.0F, 0.0F);
			medal.setTextureOffset(78, 0).addBox(5.0F, -11.0F, -5.0F, 3.0F, 6.0F, 0.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(2.0F, -12.0F, 0.0F);
			general.addChild(left_leg);
			setRotationAngle(left_leg, 0.0F, 0.0F, -0.0436F);
			left_leg.setTextureOffset(46, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(-2.0F, -12.0F, 0.0F);
			general.addChild(right_leg);
			setRotationAngle(right_leg, 0.0F, 0.0F, 0.0436F);
			right_leg.setTextureOffset(38, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			general.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}

}
