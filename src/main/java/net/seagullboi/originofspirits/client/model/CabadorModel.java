package net.seagullboi.originofspirits.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.CabadorEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CabadorModel<T extends CabadorEntity> extends EntityModel<T> {
    private final ModelRenderer cabador;
    private final ModelRenderer body;
    private final ModelRenderer saddle;
    private final ModelRenderer tail1;
    private final ModelRenderer tail2;
    private final ModelRenderer front_left_leg;
    private final ModelRenderer front_left_leg_child;
    private final ModelRenderer front_right_leg;
    private final ModelRenderer front_right_leg_child;
    private final ModelRenderer neck;
    private final ModelRenderer head;
    private final ModelRenderer mouth;
    private final ModelRenderer left_ear;
    private final ModelRenderer right_ear;
    private final ModelRenderer mane;

    public CabadorModel() {
        textureWidth = 128;
        textureHeight = 128;

        cabador = new ModelRenderer(this);
        cabador.setRotationPoint(0.0F, 35.0F, -9.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -13.0F, 6.0F);
        cabador.addChild(body);
        body.setTextureOffset(0, 0).addBox(-5.0F, -8.0F, -17.0F, 10.0F, 10.0F, 22.0F, 0.05F, false);

        saddle = new ModelRenderer(this);
        saddle.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(saddle);
        saddle.setTextureOffset(0, 32).addBox(-5.0F, -8.0F, -9.5F, 10.0F, 9.0F, 9.0F, 0.5F, false);

        tail1 = new ModelRenderer(this);
        tail1.setRotationPoint(0.0F, -3.0F, 4.0F);
        body.addChild(tail1);
        tail1.setTextureOffset(38, 32).addBox(-4.0F, -4.01F, 0.0F, 8.0F, 9.0F, 7.0F, 0.05F, false);
        tail1.setTextureOffset(31, 48).addBox(-3.0F, -3.01F, 7.0F, 6.0F, 8.0F, 7.0F, 0.05F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(0.0F, 2.0F, 14.0F);
        tail1.addChild(tail2);
        tail2.setTextureOffset(61, 25).addBox(-2.0F, -3.01F, 0.0F, 4.0F, 6.0F, 7.0F, 0.05F, false);
        tail2.setTextureOffset(0, 38).addBox(0.0F, -4.01F, 0.0F, 0.0F, 8.0F, 12.0F, 0.05F, false);

        front_left_leg = new ModelRenderer(this);
        front_left_leg.setRotationPoint(3.0F, -4.0F, -14.0F);
        body.addChild(front_left_leg);
        setRotationAngle(front_left_leg, 0.1309F, 0.0F, 0.0F);
        front_left_leg.setTextureOffset(0, 58).addBox(-2.0F, -1.0F, -2.9F, 5.0F, 13.0F, 5.0F, 0.0F, false);

        front_left_leg_child = new ModelRenderer(this);
        front_left_leg_child.setRotationPoint(0.0F, 10.4F, 1.0F);
        front_left_leg.addChild(front_left_leg_child);
        setRotationAngle(front_left_leg_child, 1.309F, 0.0F, 0.0F);
        front_left_leg_child.setTextureOffset(36, 63).addBox(-1.5F, -3.3271F, -2.6515F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        front_right_leg = new ModelRenderer(this);
        front_right_leg.setRotationPoint(-3.0F, -4.0F, -14.0F);
        body.addChild(front_right_leg);
        setRotationAngle(front_right_leg, 0.1309F, 0.0F, 0.0F);
        front_right_leg.setTextureOffset(0, 58).addBox(-3.0F, -1.0F, -2.9F, 5.0F, 13.0F, 5.0F, 0.0F, true);

        front_right_leg_child = new ModelRenderer(this);
        front_right_leg_child.setRotationPoint(0.0F, 10.4F, 1.0F);
        front_right_leg.addChild(front_right_leg_child);
        setRotationAngle(front_right_leg_child, 1.309F, 0.0F, 0.0F);
        front_right_leg_child.setTextureOffset(36, 63).addBox(-2.5F, -3.3271F, -2.6515F, 4.0F, 8.0F, 4.0F, 0.0F, true);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, -22.0F, -8.9F);
        cabador.addChild(neck);
        neck.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, -2.1F, 4.0F, 12.0F, 7.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, -1.1F);
        neck.addChild(head);
        head.setTextureOffset(42, 0).addBox(-3.0F, -11.0F, -4.0F, 6.0F, 7.0F, 9.0F, 0.0F, false);

        mouth = new ModelRenderer(this);
        mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(mouth);
        mouth.setTextureOffset(52, 66).addBox(-2.0F, -9.0F, -9.0F, 4.0F, 5.0F, 5.0F, 0.0F, false);

        left_ear = new ModelRenderer(this);
        left_ear.setRotationPoint(-1.5F, -8.5F, 3.49F);
        head.addChild(left_ear);
        setRotationAngle(left_ear, 0.0F, 0.0F, -0.4363F);
        left_ear.setTextureOffset(63, 0).addBox(-1.0F, -5.5F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, false);

        right_ear = new ModelRenderer(this);
        right_ear.setRotationPoint(1.5F, -8.5F, 3.49F);
        head.addChild(right_ear);
        setRotationAngle(right_ear, 0.0F, 0.0F, 0.4363F);
        right_ear.setTextureOffset(21, 55).addBox(-1.0F, -5.5F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, false);

        mane = new ModelRenderer(this);
        mane.setRotationPoint(0.0F, 0.0F, -0.1F);
        neck.addChild(mane);
        mane.setTextureOffset(70, 66).addBox(-1.0F, -11.0F, 4.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.neck.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.neck.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.tail1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount / 4;
        this.tail2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount / 4;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        float f = 1;
        float f2 = 1;
        MatrixStack headScale = new MatrixStack();
        if (this.isChild) {
            f = (float) ((double) f * 0.5D);
        }
        matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
        matrixStack.scale(f, f, f);
        cabador.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
