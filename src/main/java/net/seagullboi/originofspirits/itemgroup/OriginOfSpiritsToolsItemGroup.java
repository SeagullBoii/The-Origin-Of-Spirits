
package net.seagullboi.originofspirits.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.seagullboi.originofspirits.item.EnderPearlHeadPickaxeItem;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

@OriginofspiritsModElements.ModElement.Tag
public class OriginOfSpiritsToolsItemGroup extends OriginofspiritsModElements.ModElement {
	public OriginOfSpiritsToolsItemGroup(OriginofspiritsModElements instance) {
		super(instance, 255);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("taborigin_of_spirits_tools") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(EnderPearlHeadPickaxeItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}

	public static ItemGroup tab;
}
