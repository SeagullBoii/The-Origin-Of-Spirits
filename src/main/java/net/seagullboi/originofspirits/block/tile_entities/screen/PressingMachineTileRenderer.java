package net.seagullboi.originofspirits.block.tile_entities.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;
import net.seagullboi.originofspirits.block.tile_entities.PressingMachineTileEntity;

public class PressingMachineTileRenderer extends TileEntityRenderer<PressingMachineTileEntity> {

    public PressingMachineTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(PressingMachineTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if (tileEntityIn.itemHandler != null) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            IBakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(tileEntityIn.itemHandler.getStackInSlot(0), null, null);
            matrixStackIn.push();
            matrixStackIn.translate(0.5D, 0.58D, 0.30D);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90));
            matrixStackIn.scale(1.25f, 1.25f, 1.25f);
            Minecraft.getInstance().getItemRenderer().renderItem(tileEntityIn.itemHandler.getStackInSlot(0), ItemCameraTransforms.TransformType.GROUND, true, matrixStackIn, bufferIn, 200, combinedOverlayIn, model);
            matrixStackIn.pop();
        }
    }

}
