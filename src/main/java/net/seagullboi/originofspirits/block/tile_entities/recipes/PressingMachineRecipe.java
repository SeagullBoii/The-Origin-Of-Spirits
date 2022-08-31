package net.seagullboi.originofspirits.block.tile_entities.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.seagullboi.originofspirits.block.tile_entities.PressingMachineTileEntity;
import net.seagullboi.originofspirits.registry.TOOSItems;

public class PressingMachineRecipe {

    public static void craftTokens(ItemStackHandler itemHandler, BlockPos pos, World worldIn, PressingMachineTileEntity tileEntity) {
        boolean hasIronIngot = itemHandler.getStackInSlot(0).getItem() == Items.IRON_INGOT;
        boolean hasGoldIngot = itemHandler.getStackInSlot(0).getItem() == Items.GOLD_INGOT;
        boolean hasDiamond = itemHandler.getStackInSlot(0).getItem() == Items.DIAMOND;

        if(hasIronIngot) {
            int count = itemHandler.getStackInSlot(0).getCount();
            itemHandler.getStackInSlot(0).shrink(count);
            itemHandler.insertItem(0, new ItemStack(TOOSItems.IRON_GUN_UPGRADE_TOKEN.get()), false);
            itemHandler.getStackInSlot(0).setCount(count);
        } else if (hasGoldIngot) {
            int count = itemHandler.getStackInSlot(0).getCount();
            itemHandler.getStackInSlot(0).shrink(count);
            itemHandler.insertItem(0, new ItemStack(TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get()), false);
            itemHandler.getStackInSlot(0).setCount(count);
        } else if (hasDiamond) {
            int count = itemHandler.getStackInSlot(0).getCount();
            itemHandler.getStackInSlot(0).shrink(count);
            itemHandler.insertItem(0, new ItemStack(TOOSItems.DIAMOND_GUN_UPGRADE_TOKEN.get()), false);
            itemHandler.getStackInSlot(0).setCount(count);
        }

        worldIn.notifyBlockUpdate(pos, tileEntity.getBlockState(), tileEntity.getBlockState(), 3);
    }
}
