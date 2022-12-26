package net.seagullboi.originofspirits.item;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.util.ColorUtil;

import java.util.List;

public class SpitePedestalItem extends BlockItem {
    public SpitePedestalItem(Block p_i48527_1_, Properties p_i48527_2_) {
        super(p_i48527_1_, p_i48527_2_);
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new StringTextComponent("Right click the block to enable" + ColorUtil.DARK_RED + " SPITE"));
        if (Screen.hasShiftDown() || Screen.hasControlDown()) {
            list.add(new StringTextComponent(""));
            list.add(new StringTextComponent(ColorUtil.DARK_RED + "Bosses will become more difficult"));
            list.add(new StringTextComponent(ColorUtil.DARK_GREEN + "Bosses will also drop more rewarding Loot"));

        } else {
            list.add(new StringTextComponent(ColorUtil.GRAY + "Press [Shift] or [Ctrl] to view info about" + ColorUtil.DARK_RED + " SPITE"));
        }
    }
}
