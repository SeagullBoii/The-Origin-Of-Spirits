package net.seagullboi.originofspirits.registry;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.seagullboi.originofspirits.item.TridacnaShardItem;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {

    CURSED_STEEL(4, 1800, 10, 8, 25,
            () -> Ingredient.fromItems(TOOSItems.CURSED_STEEL.get())),
    TRIDACNA(3, 1000, 4,7,10,
            () -> Ingredient.fromItems(TridacnaShardItem.block)),
    SAC(4, 1800, 10,5,25, null),
    CURSED_GORE(4, 1800, 10,8,25, () -> Ingredient.fromItems(TOOSBlocks.CURSED_GORE.get()));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    ModItemTier(int harvestLevel, int maxUses, float efficiency,
                float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.getValue();
    }
}