package net.seagullboi.originofspirits.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.seagullboi.originofspirits.entity.SacFrogEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class SacFrogEntityModel <T extends SacFrogEntity> extends EntityModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer right_leg_back;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer left_leg_back;
    private float jumpRotation;

    public SacFrogEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(0, 13).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(1, 1).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 2.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(0, 27).addBox(4.1F, -7.9F, -4.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(0, 27).addBox(-5.1F, -7.9F, -4.0F, 1.0F, 3.0F, 3.0F, 0.0F, true);
        body.setTextureOffset(27, 0).addBox(1.0F, -10.0F, -5.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        body.setTextureOffset(27, 0).addBox(-4.0F, -10.0F, -5.0F, 3.0F, 2.0F, 3.0F, 0.0F, true);

        right_leg_back = new ModelRenderer(this);
        right_leg_back.setRotationPoint(4.0F, 21.0F, 3.75F);
        right_leg_back.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -0.75F, 2.0F, 4.0F, 3.0F, 0.0F, false);
        right_leg_back.setTextureOffset(22, 13).addBox(-2.0F, 3.0F, -2.75F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(4.0F, 20.0F, -3.0F);
        right_leg.setTextureOffset(0, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        right_leg.setTextureOffset(22, 17).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(-4.0F, 20.0F, -3.0F);
        left_leg.setTextureOffset(0, 13).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        left_leg.setTextureOffset(22, 17).addBox(-2.0F, 4.0F, -3.0F, 4.0F, 0.0F, 4.0F, 0.0F, true);

        left_leg_back = new ModelRenderer(this);
        left_leg_back.setRotationPoint(-4.0F, 20.0F, 3.75F);
        left_leg_back.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -0.75F, 2.0F, 4.0F, 3.0F, 0.0F, true);
        left_leg_back.setTextureOffset(22, 13).addBox(-2.0F, 4.0F, -2.75F, 4.0F, 0.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float)entityIn.ticksExisted;
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float)Math.PI);
        this.left_leg_back.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
        this.right_leg_back.rotateAngleX = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
        this.left_leg.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
        this.right_leg.rotateAngleX = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float)Math.PI);
    }


    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        float f = 1;
        float f2 = 1;
        MatrixStack headScale = new MatrixStack();
        if (this.isChild) {
            f = (float) ((double) f * 0.7D);
            f2 = (float) ((double) f2 * 0.9D);
        }
        matrixStack.translate(0f, 1.5f - f * 1.5f, 0f);
        matrixStack.scale(f, f, f);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        right_leg_back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        left_leg_back.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;

    }
}