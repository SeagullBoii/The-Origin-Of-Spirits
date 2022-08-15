package net.seagullboi.originofspirits.item;

import net.seagullboi.originofspirits.procedures.HexedSpearShootProcedure;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class HexedSpearItem extends SpearItem {

    public HexedSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(itemstack, world, list, flag);
        list.add(new StringTextComponent("\u00A727 \u00A72Ranged \u00A72Damage"));
        list.add(new StringTextComponent("\u00A77Might Inflict \u00A79Cursed"));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
        ItemStack itemstack = retval.getResult();
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();

        HexedSpearShootProcedure.executeProcedure(
                Stream.of(new AbstractMap.SimpleEntry<>("entity", entity), new AbstractMap.SimpleEntry<>("itemstack", itemstack))
                        .collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return retval;
    }

    @Override
    public double getReachDistance() {
        return 6;
    }
}
