
package net.seagullboi.originofspirits.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;

import net.seagullboi.originofspirits.procedures.SwirlinScytheRightClickProcedure;
import net.seagullboi.originofspirits.itemgroup.OriginOfSpiritsCombatItemGroup;
import net.seagullboi.originofspirits.OriginofspiritsModElements;

import java.util.stream.Stream;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.AbstractMap;

@OriginofspiritsModElements.ModElement.Tag
public class SwirlinScytheItem extends OriginofspiritsModElements.ModElement {
	@ObjectHolder("originofspirits:swirlin_scythe")
	public static final Item block = null;

	public SwirlinScytheItem(OriginofspiritsModElements instance) {
		super(instance, 864);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1500;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 4;
			}

			public int getEnchantability() {
				return 30;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SwirlinItem.block), new ItemStack(JuicySwirlinItem.block),
						new ItemStack(SwirlinShellItem.block), new ItemStack(ProditoriumIngotItem.block));
			}
		}, 3, -2.4f, new Item.Properties().group(OriginOfSpiritsCombatItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				String souls = String.valueOf((int) ((itemstack).getOrCreateTag().getDouble("souls")));
				list.add(new StringTextComponent("\u00A77" + "Stores \u00A73Souls \u00A77in Order to Summon Spirits"));
				list.add(new StringTextComponent("\u00A737 Summon Damage"));
                list.add(new StringTextComponent("\u00A7b[4 Mana Cost]"));
                list.add(new StringTextComponent("\u00A73" + souls + " Souls"));
			}

			@Override
			public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
				ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
				ItemStack itemstack = ar.getResult();
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();

				SwirlinScytheRightClickProcedure.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
								new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity),
								new AbstractMap.SimpleEntry<>("itemstack", itemstack))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
				return ar;
			}

		}.setRegistryName("swirlin_scythe"));
	}
}
