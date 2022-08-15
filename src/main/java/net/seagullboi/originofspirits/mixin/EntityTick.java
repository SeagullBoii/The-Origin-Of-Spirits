package net.seagullboi.originofspirits.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityTick {
    @Inject(at = @At(value = "HEAD"), method = "tick", remap = false)
    private void tick(CallbackInfo info) {
        Entity entity = (Entity) ((Object) this);
        entity.setGlowing(true);
    }
}
