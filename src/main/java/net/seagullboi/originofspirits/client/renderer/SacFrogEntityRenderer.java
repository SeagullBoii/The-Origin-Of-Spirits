package net.seagullboi.originofspirits.client.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.SacFrogEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.seagullboi.originofspirits.client.model.SacFrogEntityModel;

public class SacFrogEntityRenderer extends MobRenderer<SacFrogEntity, SacFrogEntityModel<SacFrogEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/sack_frog.png");

    protected static final ResourceLocation TEXTURE_EMPTY = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/sac_frog_empty.png");

    public SacFrogEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SacFrogEntityModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(SacFrogEntity entity) {
        if (entity.getSack()) {
            return TEXTURE;
        }
        return TEXTURE_EMPTY;
    }

}