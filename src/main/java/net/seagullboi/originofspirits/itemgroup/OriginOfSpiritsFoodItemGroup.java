
package net.seagullboi.originofspirits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.seagullboi.originofspirits.item.BlueBerriesItem;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class OriginOfSpiritsFoodItemGroup extends OriginofspiritsModElements.ModElement {
	public OriginOfSpiritsFoodItemGroup(OriginofspiritsModElements instance) {
		super(instance, 257);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taborigin_of_spirits_food") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BlueBerriesItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
