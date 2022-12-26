package net.seagullboi.originofspirits.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.client.model.CursedEyeModel;
import net.seagullboi.originofspirits.entity.CursedEyeEntity;

public class CursedEyeRenderer extends MobRenderer<CursedEyeEntity, CursedEyeModel<CursedEyeEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/cursed_eye.png");

    public CursedEyeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CursedEyeModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(CursedEyeEntity entity) {
        return TEXTURE;
    }
}
