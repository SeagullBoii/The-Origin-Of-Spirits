package net.seagullboi.originofspirits.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TOOSItemGroup {

    public static final ItemGroup BLOCK_GROUP = new ItemGroup("taborigin_of_spirits_blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(net.seagullboi.originofspirits.registry.TOOSBlocks.BLESSED_STONE_BRICKS.get().asItem());
        }
        @Override
        public boolean hasScrollbar() {
            return true;
        }
    };

    public static final ItemGroup PLANT_GROUP = new ItemGroup("taborigin_of_spirits_plants") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(TOOSBlocks.ALCYONEUM_POLYPS.get());
        }
        @Override
        public boolean hasScrollbar() {
            return true;
        }
    };

}
