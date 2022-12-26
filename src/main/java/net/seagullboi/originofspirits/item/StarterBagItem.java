package net.seagullboi.originofspirits.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

import java.util.ArrayList;
import java.util.Arrays;

public class StarterBagItem extends Item {

    public StarterBagItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        ItemStack starterBag = new ItemStack(TOOSItems.STARTER_BAG.get());
        ItemStack woodenSword = new ItemStack(Items.WOODEN_SWORD);
        ItemStack bow = new ItemStack(Items.BOW);
        ItemStack woodenStaff = new ItemStack(WoodenStaffItem.block);
        ItemStack arrow = new ItemStack(Items.ARROW);
        ItemStack bread = new ItemStack(Items.BREAD);
        ItemStack spitePedestal = new ItemStack(TOOSBlocks.SPITE_PEDESTAL_ITEM.get());
        ArrayList<ItemStack> items = new ArrayList<>(Arrays.asList(woodenSword, bow, woodenStaff, bread, arrow, spitePedestal));

        arrow.setCount(16);
        bread.setCount(8);

        if (!entity.abilities.isCreativeMode) {
            entity.inventory.func_234564_a_(stack -> starterBag.getItem() == stack.getItem(), 1, entity.container.func_234641_j_());
        }
        for (int i = 0; i < items.size(); i++) {
            entity.addItemStackToInventory(items.get(i));
        }

        return super.onItemRightClick(world, entity, hand);
    }
}
