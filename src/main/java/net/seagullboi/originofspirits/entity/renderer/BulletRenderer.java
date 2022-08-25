package net.seagullboi.originofspirits.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.BulletEntity;
import net.seagullboi.originofspirits.entity.CursedLaserProjectile;
import net.seagullboi.originofspirits.item.CursedFlamethrowerProjectileItem;

public class BulletRenderer extends EntityRenderer<BulletEntity> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/bullet/bullet_iron.png");
    private static final RenderType RENDER_TYPE = RenderType.getEntityCutoutNoCull(TEXTURE_LOCATION);

    public BulletRenderer(EntityRendererManager p_i46553_1_) {
        super(p_i46553_1_);
    }

    protected int getBlockLight(BulletEntity p_225624_1_, BlockPos p_225624_2_) {
        return 15;
    }

    public void render(BulletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
            IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
            matrixStackIn.push();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
            EntityModel model = new BulletModel();
            model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
            matrixStackIn.pop();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(BulletEntity entity) {
        ResourceLocation TEXTURE_LOC = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/bullet/bullet_" + entity.getMaterial() + ".png");
        return TEXTURE_LOC;
    }

}
