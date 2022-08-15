
package net.seagullboi.originofspirits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.seagullboi.originofspirits.item.RawMagnetiteItem;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class OriginOfSpiritsMiscItemGroup extends OriginofspiritsModElements.ModElement {
	public OriginOfSpiritsMiscItemGroup(OriginofspiritsModElements instance) {
		super(instance, 258);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taborigin_of_spirits_misc") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(RawMagnetiteItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
