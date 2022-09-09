package net.seagullboi.originofspirits.client.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.SacFrogEggsEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SacFrogEggsModel <T extends SacFrogEggsEntity > extends EntityModel<T> {
    private final ModelRenderer egg1;
    private final ModelRenderer egg2;
    private final ModelRenderer egg3;
    private final ModelRenderer egg4;
    private final ModelRenderer egg5;

    public SacFrogEggsModel() {
        textureWidth = 32;
        textureHeight = 32;

        egg1 = new ModelRenderer(this);
        egg1.setRotationPoint(4.0F, 24.0F, -4.0F);
        egg1.setTextureOffset(1, 3).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        egg2 = new ModelRenderer(this);
        egg2.setRotationPoint(0.5F, 24.0F, -0.5F);
        egg2.setTextureOffset(0, 9).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        egg3 = new ModelRenderer(this);
        egg3.setRotationPoint(4.5F, 24.0F, 4.5F);
        egg3.setTextureOffset(0, 9).addBox(-1.5F, -5.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

        egg4 = new ModelRenderer(this);
        egg4.setRotationPoint(0.0F, 24.0F, 0.0F);
        egg4.setTextureOffset(1, 3).addBox(-3.0F, -4.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        egg5 = new ModelRenderer(this);
        egg5.setRotationPoint(-5.0F, 24.0F, -2.0F);
        egg5.setTextureOffset(1, 3).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        egg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        egg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        egg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        egg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        egg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(SacFrogEggsEntity eggs, ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
        egg1.rotateAngleX = eggs.getXRotation();
        egg2.rotateAngleX = -eggs.getXRotation();
        egg2.rotateAngleZ = -eggs.getXRotation() / 2;
        egg3.rotateAngleZ = eggs.getXRotation();
        egg4.rotateAngleZ = eggs.getXRotation();
        egg5.rotateAngleX = eggs.getXRotation();
    }
}