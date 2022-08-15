package net.seagullboi.originofspirits.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.LazoculusEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LazoculusEntityModel <T extends LazoculusEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer robe;
    private final ModelRenderer tails;
    private final ModelRenderer tail1;
    private final ModelRenderer tail2;
    private final ModelRenderer tail3;
    private final ModelRenderer tail4;
    private final ModelRenderer tail5;
    private final ModelRenderer tail6;
    private final ModelRenderer tail4_5;
    private final ModelRenderer head_rot;
    private final ModelRenderer head;
    private final ModelRenderer eye;

    public LazoculusEntityModel() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 24).addBox(-5.7F, -26.0F, -5.7F, 11.0F, 3.0F, 11.0F, 0.0F, false);
        body.setTextureOffset(36, 0).addBox(-4.7F, -29.0F, -5.5F, 2.0F, 3.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(36, 0).addBox(2.3F, -29.0F, -5.5F, 2.0F, 3.0F, 10.0F, 0.0F, true);

        robe = new ModelRenderer(this);
        robe.setRotationPoint(0.0F, -24.0F, 0.0F);
        body.addChild(robe);
        robe.setTextureOffset(0, 0).addBox(-4.7F, -1.0F, -4.7F, 9.0F, 15.0F, 9.0F, 0.0F, false);
        robe.setTextureOffset(41, 0).addBox(-4.7F, 7.0F, -4.7F, 9.0F, 0.0F, 9.0F, 0.0F, false);

        tails = new ModelRenderer(this);
        tails.setRotationPoint(-0.075F, 9.5F, -0.175F);
        robe.addChild(tails);


        tail1 = new ModelRenderer(this);
        tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tails.addChild(tail1);
        tail1.setTextureOffset(27, 0).addBox(0.075F, -1.9F, -4.025F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        tail1.setTextureOffset(25, 42).addBox(0.175F, -1.6F, 0.075F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        tail1.setTextureOffset(0, 38).addBox(-4.125F, -1.9F, 0.075F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        tail1.setTextureOffset(16, 38).addBox(-3.125F, -1.6F, -3.125F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(0.075F, 3.9F, 0.075F);
        tails.addChild(tail2);
        tail2.setTextureOffset(13, 44).addBox(-3.1F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
        tail2.setTextureOffset(34, 45).addBox(0.1F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        tail3 = new ModelRenderer(this);
        tail3.setRotationPoint(0.075F, 7.2F, 0.175F);
        tails.addChild(tail3);
        tail3.setTextureOffset(0, 46).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        tail4 = new ModelRenderer(this);
        tail4.setRotationPoint(0.075F, 9.9F, 1.175F);
        tails.addChild(tail4);
        setRotationAngle(tail4, 0.3927F, 0.0F, 0.0F);


        tail5 = new ModelRenderer(this);
        tail5.setRotationPoint(0.0F, 2.3F, 0.0F);
        tail4.addChild(tail5);
        setRotationAngle(tail5, 0.5236F, 0.0F, 0.0F);
        tail5.setTextureOffset(0, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        tail6 = new ModelRenderer(this);
        tail6.setRotationPoint(0.0F, -0.5F, 0.0F);
        tail5.addChild(tail6);
        setRotationAngle(tail6, 0.4363F, 0.0F, 0.0F);
        tail6.setTextureOffset(6, 24).addBox(-0.5F, 1.8F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        tail4_5 = new ModelRenderer(this);
        tail4_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        tail4.addChild(tail4_5);
        tail4_5.setTextureOffset(0, 4).addBox(0.1F, -1.0F, 0.1F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        tail4_5.setTextureOffset(0, 0).addBox(-2.1F, -1.0F, -2.1F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        head_rot = new ModelRenderer(this);
        head_rot.setRotationPoint(0.0F, -29.0F, 0.0F);
        body.addChild(head_rot);


        head = new ModelRenderer(this);
        head.setRotationPoint(-0.2F, 1.0F, -0.2F);
        head_rot.addChild(head);
        setRotationAngle(head, 0.0F, 0.0F, 0.7854F);
        head.setTextureOffset(33, 16).addBox(-4.8F, -5.0F, -4.3F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        eye = new ModelRenderer(this);
        eye.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(eye);
        setRotationAngle(eye, 0.0F, 0.0F, -0.7854F);
        eye.setTextureOffset(0, 28).addBox(-0.9F, -3.0F, -4.7F, 2.0F, 3.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float f, float f1, float f2, float f3, float f4) {
        float rotation = f2 / 15f;
        this.tail1.rotateAngleY = rotation;
        this.tail2.rotateAngleY = -rotation;
        this.tail3.rotateAngleY = rotation;
        this.tail4.rotateAngleY = -rotation;
        this.tail4_5.rotateAngleY = rotation;
        this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
        this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
