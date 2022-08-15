package net.seagullboi.originofspirits.entity.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.LazoculusEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LazoculusEntityRenderer extends MobRenderer<LazoculusEntity, LazoculusEntityModel<LazoculusEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/lazoculus.png");

    public LazoculusEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LazoculusEntityModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(LazoculusEntity entity) {
        return TEXTURE;
    }
}