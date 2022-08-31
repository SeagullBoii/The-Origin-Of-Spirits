package net.seagullboi.originofspirits.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BasicDescriptionBlockItem extends BlockItem {

    String desc;

    public BasicDescriptionBlockItem(String description, Block blockIn, Properties builder) {
        super(blockIn, builder);
        this.desc = description;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent(desc));
    }
}
