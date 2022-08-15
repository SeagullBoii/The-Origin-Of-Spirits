
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsToolsItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;
import net.seagullboi.originofspirits.registry.TOOSItems;

@OriginofspiritsModElements.ModElement.Tag
public class CursedSteelShovelItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:cursed_steel_shovel")
	public static final Item block = null;

	public CursedSteelShovelItem(OriginofspiritsModElements instance) {
		super(instance, 686);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 1800;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 25;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(TOOSItems.CURSED_STEEL.get()));
			}
		}, 1, -2.4f, new Item.Properties().group(OriginOfSpiritsToolsItemGroup.tab)) {
		}.setRegistryName("cursed_steel_shovel"));
	}
}
