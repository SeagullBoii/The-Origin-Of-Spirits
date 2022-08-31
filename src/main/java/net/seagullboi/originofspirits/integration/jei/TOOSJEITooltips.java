package net.seagullboi.originofspirits.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.seagullboi.originofspirits.item.CrystalOfMagicItem;
import net.seagullboi.originofspirits.item.ImmovableShardItem;
import net.seagullboi.originofspirits.item.PuffyCloudItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@mezz.jei.api.JeiPlugin
public class TOOSJEITooltips implements IModPlugin {

        @Override
        public ResourceLocation getPluginUid() {
        return new ResourceLocation("originofspirits", "default");
        }

        @Override
        public void registerRecipes(IRecipeRegistration registry) {
                IIngredientManager ingredientManager = registry.getIngredientManager();
                IIngredientType<ItemStack> itemType = ingredientManager.getIngredientType(ItemStack.class);
                registry.addIngredientInfo(new ItemStack(ImmovableShardItem.block), itemType, "Can be fished in the Abyss.");
                registry.addIngredientInfo(new ItemStack(CrystalOfMagicItem.block), itemType, "Can be fished in the Abyss after defeating the Giant Clam.");
                registry.addIngredientInfo(new ItemStack(PuffyCloudItem.block), itemType, "Dropped by Cluffs, which spawn in The Sky Realm. Allows the player to double jump.");

        }
}
