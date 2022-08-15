package net.seagullboi.originofspirits.registry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TOOSMaterialColors {

    public static final TOOSMaterialColors[] TOOSMaterialColors = new TOOSMaterialColors[64];
    public static final TOOSMaterialColors SACRED_VEGETATION = new TOOSMaterialColors(0, -9261946);

    public final int colorValue;
    public final int colorIndex;

    private TOOSMaterialColors(int index, int color) {
        if (index >= 0 && index <= 63) {
            this.colorIndex = index;
            this.colorValue = color;
            TOOSMaterialColors[index] = this;
        } else {
            throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getMapColor(int index) {
        int i = 220;
        if (index == 3) {
            i = 135;
        }

        if (index == 2) {
            i = 255;
        }

        if (index == 1) {
            i = 220;
        }

        if (index == 0) {
            i = 180;
        }

        int j = (this.colorValue >> 16 & 255) * i / 255;
        int k = (this.colorValue >> 8 & 255) * i / 255;
        int l = (this.colorValue & 255) * i / 255;
        return -16777216 | l << 16 | k << 8 | j;
    }
}
