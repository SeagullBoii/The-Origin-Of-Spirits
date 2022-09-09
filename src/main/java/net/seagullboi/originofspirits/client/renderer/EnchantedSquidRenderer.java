package net.seagullboi.originofspirits.client.renderer;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import net.seagullboi.originofspirits.entity.EnchantedSquidEntity;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class EnchantedSquidRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(EnchantedSquidEntity.entity, renderManager -> {
				return new MobRenderer(renderManager, new Modelenchanted_squid(), 0.5f) {
					{
						this.addLayer(new GlowingLayer<>(this));
					}

					@Override
					public ResourceLocation getEntityTexture(Entity entity) {
						return new ResourceLocation("originofspirits:textures/enchanted_squid.png");
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
			IVertexBuilder ivertexbuilder = bufferIn
					.getBuffer(RenderType.getEyes(new ResourceLocation("originofspirits:textures/enchanted_squid_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	// Made with Blockbench 3.9.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public static class Modelenchanted_squid extends EntityModel<Entity> {
		private final ModelRenderer body;
		private final ModelRenderer cube_r8;
		private final ModelRenderer cube_r9;
		private final ModelRenderer cube_r10;
		private final ModelRenderer cube_r11;
		private final ModelRenderer cube_r12;
		private final ModelRenderer cube_r13;
		private final ModelRenderer cube_r14;
		private final ModelRenderer tentacle1;
		private final ModelRenderer cube_r1;
		private final ModelRenderer cube_r2;
		private final ModelRenderer tentacle14;
		private final ModelRenderer cube_r3;
		private final ModelRenderer cube_r4;
		private final ModelRenderer cube_r5;
		private final ModelRenderer cube_r6;
		private final ModelRenderer cube_r7;
		private final ModelRenderer tentacle2;
		private final ModelRenderer cube_r15;
		private final ModelRenderer cube_r16;
		private final ModelRenderer tentacle3;
		private final ModelRenderer cube_r17;
		private final ModelRenderer cube_r18;
		private final ModelRenderer tentacle4;
		private final ModelRenderer cube_r19;
		private final ModelRenderer cube_r20;
		private final ModelRenderer tentacle5;
		private final ModelRenderer cube_r21;
		private final ModelRenderer cube_r22;
		private final ModelRenderer tentacle6;
		private final ModelRenderer cube_r23;
		private final ModelRenderer cube_r24;
		private final ModelRenderer tentacle8;
		private final ModelRenderer cube_r25;
		private final ModelRenderer cube_r26;
		private final ModelRenderer tentacle9;
		private final ModelRenderer cube_r32;
		private final ModelRenderer cube_r33;
		private final ModelRenderer tentacle13;
		private final ModelRenderer cube_r27;
		private final ModelRenderer cube_r28;
		private final ModelRenderer cube_r29;
		private final ModelRenderer cube_r30;
		private final ModelRenderer cube_r31;

		public Modelenchanted_squid() {
			textureWidth = 128;
			textureHeight = 128;
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 17.0F, 0.0F);
			body.setTextureOffset(44, 27).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
			body.setTextureOffset(42, 0).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
			cube_r8 = new ModelRenderer(this);
			cube_r8.setRotationPoint(-3.0F, 7.5927F, 2.5183F);
			body.addChild(cube_r8);
			setRotationAngle(cube_r8, -0.3927F, 0.0F, 0.0F);
			cube_r8.setTextureOffset(48, 51).addBox(-2.0F, -56.8F, -20.0F, 10.0F, 8.0F, 10.0F, 0.0F, false);
			cube_r8.setTextureOffset(0, 39).addBox(-3.0F, -48.8F, -21.0F, 12.0F, 15.0F, 12.0F, 0.0F, false);
			cube_r9 = new ModelRenderer(this);
			cube_r9.setRotationPoint(4.9753F, 7.7867F, 2.2619F);
			body.addChild(cube_r9);
			setRotationAngle(cube_r9, -0.2182F, 0.0F, 0.3927F);
			cube_r9.setTextureOffset(0, 66).addBox(-34.0F, -56.5927F, -3.6183F, 11.0F, 27.0F, 2.0F, 0.0F, false);
			cube_r10 = new ModelRenderer(this);
			cube_r10.setRotationPoint(-5.0911F, 7.1288F, 1.2445F);
			body.addChild(cube_r10);
			setRotationAngle(cube_r10, -0.2182F, 0.0F, -0.3927F);
			cube_r10.setTextureOffset(26, 69).addBox(23.0F, -56.5927F, -3.5183F, 11.0F, 27.0F, 2.0F, 0.0F, false);
			cube_r11 = new ModelRenderer(this);
			cube_r11.setRotationPoint(9.3398F, 16.0811F, 29.212F);
			body.addChild(cube_r11);
			setRotationAngle(cube_r11, -0.1759F, -0.0273F, -0.8297F);
			cube_r11.setTextureOffset(52, 69).addBox(43.7F, -55.5927F, -29.5183F, 7.0F, 7.0F, 2.0F, 0.0F, false);
			cube_r12 = new ModelRenderer(this);
			cube_r12.setRotationPoint(-2.0F, 6.8799F, 2.1657F);
			body.addChild(cube_r12);
			setRotationAngle(cube_r12, -0.0873F, 0.0F, 0.0F);
			cube_r12.setTextureOffset(0, 0).addBox(-5.0F, -37.3F, -10.0F, 14.0F, 25.0F, 14.0F, 0.0F, false);
			cube_r13 = new ModelRenderer(this);
			cube_r13.setRotationPoint(0.0F, 7.0F, 0.0F);
			body.addChild(cube_r13);
			setRotationAngle(cube_r13, -0.7854F, 0.0F, 0.0F);
			cube_r13.setTextureOffset(0, 0).addBox(-8.0F, -6.0F, -8.0F, 2.0F, 5.0F, 5.0F, 0.0F, true);
			cube_r13.setTextureOffset(0, 0).addBox(6.0F, -6.0F, -8.0F, 2.0F, 5.0F, 5.0F, 0.0F, false);
			cube_r14 = new ModelRenderer(this);
			cube_r14.setRotationPoint(-3.0F, 7.5927F, 2.5183F);
			body.addChild(cube_r14);
			setRotationAngle(cube_r14, -0.1745F, 0.0F, 0.0F);
			cube_r14.setTextureOffset(56, 12).addBox(-1.0F, -63.4927F, -6.3F, 8.0F, 6.0F, 8.0F, 0.0F, false);
			tentacle1 = new ModelRenderer(this);
			tentacle1.setRotationPoint(4.0F, 7.0F, -4.0F);
			body.addChild(tentacle1);
			setRotationAngle(tentacle1, -0.1309F, 0.0F, 0.0F);
			tentacle1.setTextureOffset(52, 78).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle1.setTextureOffset(78, 85).addBox(-1.5F, 25.9505F, -2.133F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(-5.0F, 37.9505F, -1.133F);
			tentacle1.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
			cube_r1.setTextureOffset(36, 39).addBox(3.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			cube_r2 = new ModelRenderer(this);
			cube_r2.setRotationPoint(-5.0F, 13.0F, 1.0F);
			tentacle1.addChild(cube_r2);
			setRotationAngle(cube_r2, -0.0873F, 0.0F, 0.0F);
			cube_r2.setTextureOffset(70, 69).addBox(3.0F, 0.0F, -2.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle14 = new ModelRenderer(this);
			tentacle14.setRotationPoint(-4.0F, 7.0F, 0.0F);
			body.addChild(tentacle14);
			tentacle14.setTextureOffset(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, false);
			cube_r3 = new ModelRenderer(this);
			cube_r3.setRotationPoint(-7.7217F, 64.5366F, -0.1F);
			tentacle14.addChild(cube_r3);
			setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
			cube_r3.setTextureOffset(0, 39).addBox(-1.0F, -0.1F, -1.9F, 2.0F, 7.0F, 4.0F, 0.0F, false);
			cube_r4 = new ModelRenderer(this);
			cube_r4.setRotationPoint(-5.0338F, 52.6218F, -1.0F);
			tentacle14.addChild(cube_r4);
			setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2182F);
			cube_r4.setTextureOffset(80, 26).addBox(-1.0F, -0.1F, 0.4F, 2.0F, 12.0F, 1.0F, 0.0F, false);
			cube_r5 = new ModelRenderer(this);
			cube_r5.setRotationPoint(-2.4366F, 40.9063F, 0.0F);
			tentacle14.addChild(cube_r5);
			setRotationAngle(cube_r5, 0.0F, 0.0F, 0.2182F);
			cube_r5.setTextureOffset(70, 84).addBox(-1.0F, -0.1F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			cube_r6 = new ModelRenderer(this);
			cube_r6.setRotationPoint(-1.2202F, 26.9467F, 0.0F);
			tentacle14.addChild(cube_r6);
			setRotationAngle(cube_r6, 0.0F, 0.0F, 0.0873F);
			cube_r6.setTextureOffset(62, 78).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
			cube_r7 = new ModelRenderer(this);
			cube_r7.setRotationPoint(0.0F, 13.0F, 0.0F);
			tentacle14.addChild(cube_r7);
			setRotationAngle(cube_r7, 0.0F, 0.0F, 0.0873F);
			cube_r7.setTextureOffset(80, 69).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
			tentacle2 = new ModelRenderer(this);
			tentacle2.setRotationPoint(-4.0F, 7.0F, -4.0F);
			body.addChild(tentacle2);
			setRotationAngle(tentacle2, -0.1309F, 0.0F, 0.0F);
			tentacle2.setTextureOffset(52, 78).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle2.setTextureOffset(78, 85).addBox(-0.5F, 25.9505F, -2.133F, 2.0F, 12.0F, 2.0F, 0.0F, true);
			cube_r15 = new ModelRenderer(this);
			cube_r15.setRotationPoint(5.0F, 37.9505F, -1.133F);
			tentacle2.addChild(cube_r15);
			setRotationAngle(cube_r15, 0.0873F, 0.0F, 0.0F);
			cube_r15.setTextureOffset(36, 39).addBox(-5.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, true);
			cube_r16 = new ModelRenderer(this);
			cube_r16.setRotationPoint(5.0F, 13.0F, 1.0F);
			tentacle2.addChild(cube_r16);
			setRotationAngle(cube_r16, -0.0873F, 0.0F, 0.0F);
			cube_r16.setTextureOffset(70, 69).addBox(-6.0F, 0.0F, -2.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle3 = new ModelRenderer(this);
			tentacle3.setRotationPoint(-4.0F, 7.0F, 4.0F);
			body.addChild(tentacle3);
			setRotationAngle(tentacle3, 0.1309F, 0.0F, 0.0F);
			tentacle3.setTextureOffset(52, 78).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle3.setTextureOffset(78, 85).addBox(-0.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, true);
			cube_r17 = new ModelRenderer(this);
			cube_r17.setRotationPoint(5.0F, 37.9505F, 1.133F);
			tentacle3.addChild(cube_r17);
			setRotationAngle(cube_r17, -0.0873F, 0.0F, 0.0F);
			cube_r17.setTextureOffset(36, 39).addBox(-5.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, true);
			cube_r18 = new ModelRenderer(this);
			cube_r18.setRotationPoint(5.0F, 13.0F, -1.0F);
			tentacle3.addChild(cube_r18);
			setRotationAngle(cube_r18, 0.0873F, 0.0F, 0.0F);
			cube_r18.setTextureOffset(70, 69).addBox(-6.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle4 = new ModelRenderer(this);
			tentacle4.setRotationPoint(4.0F, 7.0F, 4.0F);
			body.addChild(tentacle4);
			setRotationAngle(tentacle4, 0.1309F, 0.0F, 0.0F);
			tentacle4.setTextureOffset(52, 78).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle4.setTextureOffset(78, 85).addBox(-1.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			cube_r19 = new ModelRenderer(this);
			cube_r19.setRotationPoint(-5.0F, 37.9505F, 1.133F);
			tentacle4.addChild(cube_r19);
			setRotationAngle(cube_r19, -0.0873F, 0.0F, 0.0F);
			cube_r19.setTextureOffset(36, 39).addBox(3.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			cube_r20 = new ModelRenderer(this);
			cube_r20.setRotationPoint(-5.0F, 13.0F, -1.0F);
			tentacle4.addChild(cube_r20);
			setRotationAngle(cube_r20, 0.0873F, 0.0F, 0.0F);
			cube_r20.setTextureOffset(70, 69).addBox(3.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle5 = new ModelRenderer(this);
			tentacle5.setRotationPoint(5.0F, 7.0F, 3.0F);
			body.addChild(tentacle5);
			setRotationAngle(tentacle5, 0.0F, 1.5708F, 0.0F);
			tentacle5.setTextureOffset(52, 78).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle5.setTextureOffset(78, 85).addBox(-1.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			cube_r21 = new ModelRenderer(this);
			cube_r21.setRotationPoint(-5.0F, 37.9505F, 1.133F);
			tentacle5.addChild(cube_r21);
			setRotationAngle(cube_r21, -0.0873F, 0.0F, 0.0F);
			cube_r21.setTextureOffset(36, 39).addBox(3.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			cube_r22 = new ModelRenderer(this);
			cube_r22.setRotationPoint(-5.0F, 13.0F, -1.0F);
			tentacle5.addChild(cube_r22);
			setRotationAngle(cube_r22, 0.0873F, 0.0F, 0.0F);
			cube_r22.setTextureOffset(70, 69).addBox(3.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle6 = new ModelRenderer(this);
			tentacle6.setRotationPoint(5.0F, 7.0F, -4.0F);
			body.addChild(tentacle6);
			setRotationAngle(tentacle6, 0.0F, 1.5708F, 0.0F);
			tentacle6.setTextureOffset(52, 78).addBox(-2.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle6.setTextureOffset(78, 85).addBox(-1.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, false);
			cube_r23 = new ModelRenderer(this);
			cube_r23.setRotationPoint(-5.0F, 37.9505F, 1.133F);
			tentacle6.addChild(cube_r23);
			setRotationAngle(cube_r23, -0.0873F, 0.0F, 0.0F);
			cube_r23.setTextureOffset(36, 39).addBox(3.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			cube_r24 = new ModelRenderer(this);
			cube_r24.setRotationPoint(-5.0F, 13.0F, -1.0F);
			tentacle6.addChild(cube_r24);
			setRotationAngle(cube_r24, 0.0873F, 0.0F, 0.0F);
			cube_r24.setTextureOffset(70, 69).addBox(3.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, false);
			tentacle8 = new ModelRenderer(this);
			tentacle8.setRotationPoint(-5.0F, 7.0F, -4.0F);
			body.addChild(tentacle8);
			setRotationAngle(tentacle8, 0.0F, -1.5708F, 0.0F);
			tentacle8.setTextureOffset(52, 78).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle8.setTextureOffset(78, 85).addBox(-0.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, true);
			cube_r25 = new ModelRenderer(this);
			cube_r25.setRotationPoint(5.0F, 37.9505F, 1.133F);
			tentacle8.addChild(cube_r25);
			setRotationAngle(cube_r25, -0.0873F, 0.0F, 0.0F);
			cube_r25.setTextureOffset(36, 39).addBox(-5.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, true);
			cube_r26 = new ModelRenderer(this);
			cube_r26.setRotationPoint(5.0F, 13.0F, -1.0F);
			tentacle8.addChild(cube_r26);
			setRotationAngle(cube_r26, 0.0873F, 0.0F, 0.0F);
			cube_r26.setTextureOffset(70, 69).addBox(-6.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle9 = new ModelRenderer(this);
			tentacle9.setRotationPoint(-5.0F, 7.0F, 3.0F);
			body.addChild(tentacle9);
			setRotationAngle(tentacle9, 0.0F, -1.5708F, 0.0F);
			tentacle9.setTextureOffset(52, 78).addBox(-1.0F, 0.0F, -1.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle9.setTextureOffset(78, 85).addBox(-0.5F, 25.9505F, 0.133F, 2.0F, 12.0F, 2.0F, 0.0F, true);
			cube_r32 = new ModelRenderer(this);
			cube_r32.setRotationPoint(5.0F, 37.9505F, 1.133F);
			tentacle9.addChild(cube_r32);
			setRotationAngle(cube_r32, -0.0873F, 0.0F, 0.0F);
			cube_r32.setTextureOffset(36, 39).addBox(-5.5F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, true);
			cube_r33 = new ModelRenderer(this);
			cube_r33.setRotationPoint(5.0F, 13.0F, -1.0F);
			tentacle9.addChild(cube_r33);
			setRotationAngle(cube_r33, 0.0873F, 0.0F, 0.0F);
			cube_r33.setTextureOffset(70, 69).addBox(-6.0F, 0.0F, 0.0F, 3.0F, 13.0F, 2.0F, 0.0F, true);
			tentacle13 = new ModelRenderer(this);
			tentacle13.setRotationPoint(4.0F, 7.0F, 0.0F);
			body.addChild(tentacle13);
			tentacle13.setTextureOffset(82, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, true);
			cube_r27 = new ModelRenderer(this);
			cube_r27.setRotationPoint(7.7217F, 64.5366F, -0.1F);
			tentacle13.addChild(cube_r27);
			setRotationAngle(cube_r27, 0.0F, 0.0F, -0.4363F);
			cube_r27.setTextureOffset(0, 39).addBox(-1.0F, -0.1F, -1.9F, 2.0F, 7.0F, 4.0F, 0.0F, true);
			cube_r28 = new ModelRenderer(this);
			cube_r28.setRotationPoint(5.0338F, 52.6218F, -1.0F);
			tentacle13.addChild(cube_r28);
			setRotationAngle(cube_r28, 0.0F, 0.0F, -0.2182F);
			cube_r28.setTextureOffset(80, 26).addBox(-1.0F, -0.1F, 0.4F, 2.0F, 12.0F, 1.0F, 0.0F, true);
			cube_r29 = new ModelRenderer(this);
			cube_r29.setRotationPoint(2.4366F, 40.9063F, 0.0F);
			tentacle13.addChild(cube_r29);
			setRotationAngle(cube_r29, 0.0F, 0.0F, -0.2182F);
			cube_r29.setTextureOffset(70, 84).addBox(-1.0F, -0.1F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);
			cube_r30 = new ModelRenderer(this);
			cube_r30.setRotationPoint(1.2202F, 26.9467F, 0.0F);
			tentacle13.addChild(cube_r30);
			setRotationAngle(cube_r30, 0.0F, 0.0F, -0.0873F);
			cube_r30.setTextureOffset(62, 78).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);
			cube_r31 = new ModelRenderer(this);
			cube_r31.setRotationPoint(0.0F, 13.0F, 0.0F);
			tentacle13.addChild(cube_r31);
			setRotationAngle(cube_r31, 0.0F, 0.0F, -0.0873F);
			cube_r31.setTextureOffset(80, 69).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);
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

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {

			this.body.rotateAngleY = f2 / 20.f;
			this.tentacle8.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle9.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle6.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.tentacle4.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.tentacle5.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.tentacle2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.tentacle1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle13.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.tentacle14.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		}
	}

}
