package net.seagullboi.originofspirits.util;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public final class TOOSMaterials {
    public static final Material CURSED_GORE = (new Material.Builder(MaterialColor.PURPLE)).build();

    private final MaterialColor color;
    private final PushReaction pushReaction;
    private final boolean blocksMovement;
    private final boolean flammable;
    private final boolean isLiquid;
    private final boolean isOpaque;
    private final boolean replaceable;
    private final boolean isSolid;

    public TOOSMaterials(MaterialColor color, boolean isLiquid, boolean isSolid, boolean blocksMovement, boolean isOpaque, boolean flammable, boolean replaceable, PushReaction pushReaction) {
        this.color = color;
        this.isLiquid = isLiquid;
        this.isSolid = isSolid;
        this.blocksMovement = blocksMovement;
        this.isOpaque = isOpaque;
        this.flammable = flammable;
        this.replaceable = replaceable;
        this.pushReaction = pushReaction;
    }

    public boolean isLiquid() {
        return this.isLiquid;
    }
    public boolean isSolid() {
        return this.isSolid;
    }
    public boolean blocksMovement() {
        return this.blocksMovement;
    }
    public boolean isFlammable() {
        return this.flammable;
    }
    public boolean isReplaceable() {
        return this.replaceable;
    }
    public boolean isOpaque() {
        return this.isOpaque;
    }
    public PushReaction getPushReaction() {
        return this.pushReaction;
    }
}
