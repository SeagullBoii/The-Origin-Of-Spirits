package net.seagullboi.originofspirits.client.renderer;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.entity.CluffEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class CluffEntityRenderer extends MobRenderer<CluffEntity, CluffEntityModel<CluffEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/cluff/cluff.png");
    protected static final ResourceLocation ATTACK_TEXTURE = new ResourceLocation(OriginOfSpirits.MOD_ID, "textures/entity/cluff/cluff_shoot.png");

    public CluffEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CluffEntityModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(CluffEntity entity) {
        return entity.getAttackTarget() != null ? ATTACK_TEXTURE : TEXTURE;

    }
}