
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.seagullboi.originofspirits.procedures.EndPearlHeadPickaxeLevelProcedure;
import net.seagullboi.originofspirits.procedures.EndPearlHeadPickaxeItemTickProcedure;
import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsToolsItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.stream.Stream;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.AbstractMap;

@OriginofspiritsModElements.ModElement.Tag
public class EnderPearlHeadPickaxeItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:ender_pearl_head_pickaxe")
	public static final Item block = null;

	public EnderPearlHeadPickaxeItem(OriginofspiritsModElements instance) {
		super(instance, 471);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1651;
			}

			public float getEfficiency() {
				return 8.5f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 3;
			}

			public int getEnchantability() {
				return 12;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.ENDER_PEARL));
			}
		}, 1, -2.4f, new Item.Properties().group(OriginOfSpiritsToolsItemGroup.tab)) {
			@Override
			public boolean onBlockDestroyed(ItemStack itemstack, World world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
				boolean retval = super.onBlockDestroyed(itemstack, world, blockstate, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				EndPearlHeadPickaxeLevelProcedure.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z),
								new AbstractMap.SimpleEntry<>("itemstack", itemstack), new AbstractMap.SimpleEntry<>("entity", entity))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				return retval;
			}

			@Override
			public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
				super.inventoryTick(itemstack, world, entity, slot, selected);
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				if (selected)
					EndPearlHeadPickaxeItemTickProcedure.executeProcedure(
							Stream.of(new AbstractMap.SimpleEntry<>("entity", entity), new AbstractMap.SimpleEntry<>("itemstack", itemstack))
									.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				String progress = String.valueOf((int) ((itemstack).getOrCreateTag().getDouble("progress")));
				String level = String.valueOf((int) ((itemstack).getOrCreateTag().getDouble("level")) + 1);
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A7c[Spite Item]"));
				list.add(new StringTextComponent((("\u00A77") + "Blocks Broken : " + (progress))));
				list.add(new StringTextComponent((("\u00A77") + "Level : " + (level))));
			}
		}.setRegistryName("ender_pearl_head_pickaxe"));
	}
}
