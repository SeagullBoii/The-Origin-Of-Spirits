package net.seagullboi.originofspirits.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.seagullboi.originofspirits.entity.CursedEyeEntity;

public class CursedEyeModel<T extends CursedEyeEntity> extends EntityModel<T> {
    private final ModelRenderer eye;
    private final ModelRenderer tails;
    private final ModelRenderer tail1_left;
    private final ModelRenderer tail2_left;
    private final ModelRenderer tail3_left;
    private final ModelRenderer tail1_right;
    private final ModelRenderer tail2_right;
    private final ModelRenderer tail3_right;
    private final ModelRenderer tail1_up;
    private final ModelRenderer tail2_up;
    private final ModelRenderer tail3_up;
    private final ModelRenderer tail1_down;
    private final ModelRenderer tail2_down;
    private final ModelRenderer tail3_down;

    public CursedEyeModel() {
        textureWidth = 32;
        textureHeight = 32;

        eye = new ModelRenderer(this);
        eye.setRotationPoint(0.0F, 20.0F, 0.0F);
        eye.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        tails = new ModelRenderer(this);
        tails.setRotationPoint(3.0F, 0.0F, 4.0F);
        eye.addChild(tails);


        tail1_left = new ModelRenderer(this);
        tail1_left.setRotationPoint(-3.0F, 0.0F, 0.0F);
        tails.addChild(tail1_left);
        tail1_left.setTextureOffset(20, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        tail2_left = new ModelRenderer(this);
        tail2_left.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail1_left.addChild(tail2_left);
        tail2_left.setTextureOffset(12, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        tail3_left = new ModelRenderer(this);
        tail3_left.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail2_left.addChild(tail3_left);
        tail3_left.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

        tail1_right = new ModelRenderer(this);
        tail1_right.setRotationPoint(-3.0F, 0.0F, 0.0F);
        tails.addChild(tail1_right);
        tail1_right.setTextureOffset(20, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail2_right = new ModelRenderer(this);
        tail2_right.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail1_right.addChild(tail2_right);
        tail2_right.setTextureOffset(12, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail3_right = new ModelRenderer(this);
        tail3_right.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail2_right.addChild(tail3_right);
        tail3_right.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail1_up = new ModelRenderer(this);
        tail1_up.setRotationPoint(-3.0F, 0.0F, 0.0F);
        tails.addChild(tail1_up);
        tail1_up.setTextureOffset(20, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail2_up = new ModelRenderer(this);
        tail2_up.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail1_up.addChild(tail2_up);
        tail2_up.setTextureOffset(12, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail3_up = new ModelRenderer(this);
        tail3_up.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail2_up.addChild(tail3_up);
        tail3_up.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail1_down = new ModelRenderer(this);
        tail1_down.setRotationPoint(-3.0F, 0.0F, 0.0F);
        tails.addChild(tail1_down);
        tail1_down.setTextureOffset(20, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail2_down = new ModelRenderer(this);
        tail2_down.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail1_down.addChild(tail2_down);
        tail2_down.setTextureOffset(12, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);

        tail3_down = new ModelRenderer(this);
        tail3_down.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail2_down.addChild(tail3_down);
        tail3_down.setTextureOffset(0, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void setRotationAngles(CursedEyeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        float tailRot = (MathHelper.sin(ageInTicks * 0.3F + 2) * 0.05F) * 8;
        
        this.eye.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
        this.eye.rotateAngleX = headPitch / (180F / (float) Math.PI);
        this.tail1_left.rotateAngleY = tailRot;
        this.tail2_left.rotateAngleY = tailRot;
        this.tail3_left.rotateAngleY = -tailRot / 2;
        this.tail1_right.rotateAngleY = -tailRot;
        this.tail2_right.rotateAngleY = -tailRot;
        this.tail3_right.rotateAngleY = tailRot / 2;
        this.tail1_up.rotateAngleX = tailRot;
        this.tail2_up.rotateAngleX = tailRot;
        this.tail3_up.rotateAngleX = -tailRot / 2;
        this.tail1_down.rotateAngleX = -tailRot;
        this.tail2_down.rotateAngleX = -tailRot;
        this.tail3_down.rotateAngleX = tailRot / 2;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        eye.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
