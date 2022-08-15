package net.seagullboi.originofspirits.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.HobayoshEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class HobayoshModel <T extends HobayoshEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer horn_left;
    private final ModelRenderer horn_left2;
    private final ModelRenderer horn_left3;
    private final ModelRenderer horn_right;
    private final ModelRenderer horn_right2;
    private final ModelRenderer horn_right3;
    private final ModelRenderer body;
    private final ModelRenderer tail;
    private final ModelRenderer back_left_leg;
    private final ModelRenderer back_right_leg;
    private final ModelRenderer front_left_leg;
    private final ModelRenderer front_right_leg;

    public HobayoshModel() {
        textureWidth = 128;
        textureHeight = 128;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 4.0F, -8.0F);
        head.setTextureOffset(0, 30).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(36, 0).addBox(-4.0F, -1.0F, -9.0F, 8.0F, 5.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(48, 8).addBox(-5.0F, 2.0F, -9.01F, 10.0F, 7.0F, 0.0F, 0.0F, false);

        horn_left = new ModelRenderer(this);
        horn_left.setRotationPoint(-4.0F, -3.0F, -3.5F);
        head.addChild(horn_left);
        setRotationAngle(horn_left, 0.2618F, 0.0F, 0.0F);
        horn_left.setTextureOffset(0, 6).addBox(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        horn_left2 = new ModelRenderer(this);
        horn_left2.setRotationPoint(-4.0F, -3.0F, -3.5F);
        head.addChild(horn_left2);
        setRotationAngle(horn_left2, 1.1781F, 0.0F, 0.0F);
        horn_left2.setTextureOffset(0, 6).addBox(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        horn_left3 = new ModelRenderer(this);
        horn_left3.setRotationPoint(-4.0F, -3.0F, -3.5F);
        head.addChild(horn_left3);
        setRotationAngle(horn_left3, 2.4435F, 0.0F, 0.0F);
        horn_left3.setTextureOffset(0, 6).addBox(-1.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        horn_right = new ModelRenderer(this);
        horn_right.setRotationPoint(4.0F, -3.0F, -3.5F);
        head.addChild(horn_right);
        setRotationAngle(horn_right, 0.2618F, 0.0F, 0.0F);
        horn_right.setTextureOffset(4, 6).addBox(0.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        horn_right2 = new ModelRenderer(this);
        horn_right2.setRotationPoint(4.0F, -3.0F, -3.5F);
        head.addChild(horn_right2);
        setRotationAngle(horn_right2, 1.1781F, 0.0F, 0.0F);
        horn_right2.setTextureOffset(4, 6).addBox(0.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        horn_right3 = new ModelRenderer(this);
        horn_right3.setRotationPoint(4.0F, -3.0F, -3.5F);
        head.addChild(horn_right3);
        setRotationAngle(horn_right3, 2.4435F, 0.0F, 0.0F);
        horn_right3.setTextureOffset(4, 6).addBox(0.0F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 5.0F, 2.0F);
        setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, -9.0F, 12.0F, 18.0F, 12.0F, 0.0F, false);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 8.0F, -2.0F);
        body.addChild(tail);
        setRotationAngle(tail, -0.829F, 0.0F, 0.0F);
        tail.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        back_left_leg = new ModelRenderer(this);
        back_left_leg.setRotationPoint(-4.0F, 12.0F, 7.0F);
        back_left_leg.setTextureOffset(44, 42).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        back_right_leg = new ModelRenderer(this);
        back_right_leg.setRotationPoint(4.0F, 12.0F, 7.0F);
        back_right_leg.setTextureOffset(32, 30).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        front_left_leg = new ModelRenderer(this);
        front_left_leg.setRotationPoint(-4.0F, 14.0F, -5.0F);
        front_left_leg.setTextureOffset(16, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);

        front_right_leg = new ModelRenderer(this);
        front_right_leg.setRotationPoint(4.0F, 14.0F, -5.0F);
        front_right_leg.setTextureOffset(0, 46).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T e, float f, float f1, float f2, float f3, float f4) {
        this.front_right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
        this.back_right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
        this.front_left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
        this.back_left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
        this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        float f = 1;
        float f2 = 1;
        MatrixStack headScale = new MatrixStack();
        if (this.isChild) {
            f = (float) ((double) f * 0.7D);
            f2 = (float) ((double) f2 * 0.9D);
        }
        matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
        matrixStack.scale(f, f, f);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        back_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        back_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        front_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        front_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;

    }
}