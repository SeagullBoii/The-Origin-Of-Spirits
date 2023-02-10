package net.seagullboi.originofspirits.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.CluffEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class CluffEntityModel <T extends CluffEntity> extends EntityModel<T> {
    private final ModelRenderer cloud;
    private final ModelRenderer mustache;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer mustache2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer tail;
    private final ModelRenderer tail1;
    private final ModelRenderer tail1_5;
    private final ModelRenderer tail2;
    private final ModelRenderer tail2_5;
    private final ModelRenderer tail3;
    private final ModelRenderer tail3_5;
    private final ModelRenderer tail4;
    private final ModelRenderer tail4_5;

    public CluffEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        cloud = new ModelRenderer(this);
        cloud.setRotationPoint(0.0F, 18.5F, 6.9F);
        cloud.setTextureOffset(0, 0).addBox(-8.0F, -5.5F, -15.0F, 16.0F, 11.0F, 16.0F, 0.0F, false);

        mustache = new ModelRenderer(this);
        mustache.setRotationPoint(6.2607F, 0.5F, -14.0F);
        cloud.addChild(mustache);
        setRotationAngle(mustache, 0.0F, 0.0F, 0.4363F);
        mustache.setTextureOffset(0, 42).addBox(-0.0607F, -2.0F, -1.01F, 7.0F, 4.0F, 2.0F, 0.0F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-0.4066F, 10.9431F, 7.0F);
        mustache.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.0F, 0.48F);
        cube_r1.setTextureOffset(34, 45).addBox(-4.8541F, -11.9431F, -7.51F, 7.0F, 4.0F, 1.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-2.7393F, 10.5641F, 7.5F);
        mustache.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -0.48F);
        cube_r2.setTextureOffset(0, 48).addBox(6.4786F, -9.5641F, -8.01F, 7.0F, 4.0F, 1.0F, 0.0F, false);

        mustache2 = new ModelRenderer(this);
        mustache2.setRotationPoint(-6.2607F, 0.5F, -14.0F);
        cloud.addChild(mustache2);
        setRotationAngle(mustache2, 0.0F, 0.0F, -0.4363F);
        mustache2.setTextureOffset(0, 42).addBox(-6.9393F, -2.0F, -1.01F, 7.0F, 4.0F, 2.0F, 0.0F, true);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.4066F, 10.9431F, 7.0F);
        mustache2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, -0.48F);
        cube_r3.setTextureOffset(34, 45).addBox(-2.1459F, -11.9431F, -7.51F, 7.0F, 4.0F, 1.0F, 0.0F, true);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(2.7393F, 10.5641F, 7.5F);
        mustache2.addChild(cube_r4);
        setRotationAngle(cube_r4, 0.0F, 0.0F, 0.48F);
        cube_r4.setTextureOffset(0, 48).addBox(-13.4786F, -9.5641F, -8.01F, 7.0F, 4.0F, 1.0F, 0.0F, true);

        tail = new ModelRenderer(this);
        tail.setRotationPoint(0.0F, 18.0F, 9.0F);
        setRotationAngle(tail, 1.4399F, 0.0F, 0.0F);


        tail1 = new ModelRenderer(this);
        tail1.setRotationPoint(0.0F, -0.9617F, -0.987F);
        tail.addChild(tail1);
        setRotationAngle(tail1, -0.1745F, 0.0F, 0.0F);


        tail1_5 = new ModelRenderer(this);
        tail1_5.setRotationPoint(0.0F, 4.0F, 0.0F);
        tail1.addChild(tail1_5);
        tail1_5.setTextureOffset(0, 27).addBox(-4.0F, -3.5F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

        tail2 = new ModelRenderer(this);
        tail2.setRotationPoint(0.0F, 8.5F, 0.0F);
        tail1.addChild(tail2);
        setRotationAngle(tail2, 0.0873F, 0.0F, 0.0F);


        tail2_5 = new ModelRenderer(this);
        tail2_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        tail2.addChild(tail2_5);
        tail2_5.setTextureOffset(32, 27).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        tail3 = new ModelRenderer(this);
        tail3.setRotationPoint(0.0F, 7.0F, 0.0F);
        tail2.addChild(tail3);
        setRotationAngle(tail3, 0.2182F, 0.0F, 0.0F);


        tail3_5 = new ModelRenderer(this);
        tail3_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        tail3.addChild(tail3_5);
        tail3_5.setTextureOffset(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        tail4 = new ModelRenderer(this);
        tail4.setRotationPoint(0.0F, 5.0F, 0.1F);
        tail3.addChild(tail4);
        setRotationAngle(tail4, 0.5672F, 0.0F, 0.0F);


        tail4_5 = new ModelRenderer(this);
        tail4_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        tail4.addChild(tail4_5);
        tail4_5.setTextureOffset(0, 27).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 3.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        float f = ageInTicks / 10;
        if(entity.getAngerTime() > 0) {
            f = ageInTicks / 5;
        } else {
            f = ageInTicks / 10;
        }

        this.cloud.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.cloud.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount / 4 + cloud.rotateAngleY / 2;
        this.tail1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount / 4 + cloud.rotateAngleY / 2;
        this.tail1_5.rotateAngleY = f + cloud.rotateAngleY/ 4;
        this.tail2_5.rotateAngleY = -f + cloud.rotateAngleY/ 4;
        this.tail3_5.rotateAngleY = f + cloud.rotateAngleY / 6;
        this.tail4_5.rotateAngleY = -f + cloud.rotateAngleY / 6;
        this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 10 + 1.4399F;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        cloud.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
