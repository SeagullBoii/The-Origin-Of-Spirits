package net.seagullboi.originofspirits.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class FoodItemWithResult extends Item {

    Item result;
    int useDuration;
    boolean drinkable;

    public FoodItemWithResult(Properties properties, Item resultant, int useDuration, boolean liquid) {
        super(properties);
        this.result = resultant;
        this.useDuration = useDuration;
        this.drinkable = liquid;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return this.drinkable ? UseAction.DRINK : UseAction.EAT;
    }

    public int getUseDuration() {
        return useDuration;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
        super.onItemUseFinish(itemstack, world, entity);
        ItemStack resultStack = new ItemStack(result);

        if (itemstack.isEmpty()) {
            return resultStack;
        } else {
            if (entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entity;
                if (!player.isCreative() && !player.inventory.addItemStackToInventory(resultStack)) {
                    player.dropItem(resultStack, false);
                }
            }
            return itemstack;
        }
    }
}
