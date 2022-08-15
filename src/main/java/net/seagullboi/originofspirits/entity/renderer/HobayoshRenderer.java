package net.seagullboi.originofspirits.entity.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.HobayoshEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class HobayoshRenderer extends MobRenderer<HobayoshEntity, HobayoshModel<HobayoshEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/hobayosh.png");

    public HobayoshRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HobayoshModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(HobayoshEntity entity) {
        return TEXTURE;
    }
}