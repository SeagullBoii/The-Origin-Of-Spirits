package net.seagullboi.originofspirits.client.model;

// Made with Blockbench 4.2.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.CaecanusEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CaecanusEntityModel<T extends CaecanusEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer bodywear;
    private final ModelRenderer arms;
    private final ModelRenderer arms_rotation;
    private final ModelRenderer arms_flipped;
    private final ModelRenderer head;
    private final ModelRenderer headwear;
    private final ModelRenderer headwear2;
    private final ModelRenderer rotation;
    private final ModelRenderer nose;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;

    public CaecanusEntityModel() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 12.0F, 0.0F);
        body.setTextureOffset(0, 45).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);

        bodywear = new ModelRenderer(this);
        bodywear.setRotationPoint(0.0F, -12.0F, 0.0F);
        body.addChild(bodywear);
        bodywear.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.25F, false);

        arms = new ModelRenderer(this);
        arms.setRotationPoint(0.0F, -8.5F, 0.3F);
        body.addChild(arms);


        arms_rotation = new ModelRenderer(this);
        arms_rotation.setRotationPoint(0.0F, -2.0F, 0.05F);
        arms.addChild(arms_rotation);
        setRotationAngle(arms_rotation, -0.7505F, 0.0F, 0.0F);
        arms_rotation.setTextureOffset(58, 34).addBox(-8.0F, 0.0F, -2.05F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms_rotation.setTextureOffset(44, 51).addBox(-4.0F, 4.0F, -2.05F, 8.0F, 4.0F, 4.0F, 0.0F, false);

        arms_flipped = new ModelRenderer(this);
        arms_flipped.setRotationPoint(0.0F, 24.0F, 0.0F);
        arms_rotation.addChild(arms_flipped);
        arms_flipped.setTextureOffset(34, 18).addBox(4.0F, -24.0F, -2.05F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -12.0F, 0.0F);
        body.addChild(head);
        head.setTextureOffset(26, 33).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        headwear = new ModelRenderer(this);
        headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(headwear);
        headwear.setTextureOffset(28, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.5F, false);

        headwear2 = new ModelRenderer(this);
        headwear2.setRotationPoint(0.0F, 0.0F, 0.0F);
        headwear.addChild(headwear2);


        rotation = new ModelRenderer(this);
        rotation.setRotationPoint(0.0F, 0.0F, 0.0F);
        headwear2.addChild(rotation);
        setRotationAngle(rotation, -1.5708F, 0.0F, 0.0F);
        rotation.setTextureOffset(0, 24).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        head.addChild(nose);
        nose.setTextureOffset(22, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        right_leg.setTextureOffset(28, 51).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
        left_leg.setTextureOffset(50, 18).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(CaecanusEntity e, float f, float f1, float f2, float f3, float f4){
        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
        this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
        this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        float f = 1;
        if (this.isChild) {
            f = (float) ((double) f * 0.55D);
        }
        matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
        matrixStack.scale(f, f, f);

        body.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}