package net.seagullboi.originofspirits.entity.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CabadorEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CabadorRenderer extends MobRenderer<CabadorEntity, CabadorModel<CabadorEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/cabador.png");
    protected static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/cabador_saddle.png");

    public CabadorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CabadorModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(CabadorEntity entity) {
        return entity.isSaddled() ? SADDLE_TEXTURE : TEXTURE;
    }
}
