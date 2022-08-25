package net.seagullboi.originofspirits.block.tile_entities;

import net.seagullboi.originofspirits.registry.TOOSItems;
import net.seagullboi.originofspirits.registry.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GunsmithingTableTileEntity extends TileEntity {

    public final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public GunsmithingTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public GunsmithingTableTileEntity() {
        this(ModTileEntities.GUNSMITHING_TABLE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                    case 2:
                        return stack.getItem() == TOOSItems.EYE_CANNON.get() || stack.getItem() == TOOSItems.REDSTONE_HANDGUN.get() || stack.getItem() == TOOSItems.SHOTGUN.get();
                    case 1:
                        return stack.getItem() == TOOSItems.DIAMOND_GUN_UPGRADE_TOKEN.get() || stack.getItem() == TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get() || stack.getItem() == TOOSItems.IRON_GUN_UPGRADE_TOKEN.get();
                    default:
                        return false;
                }
            }
            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void upgradeWeapon () {
        boolean hasEyeCanon = this.itemHandler.getStackInSlot(0).getCount() > 0 && this.itemHandler.getStackInSlot(0).getItem() == TOOSItems.EYE_CANNON.get();
        boolean hasShotgun = this.itemHandler.getStackInSlot(0).getCount() > 0 && this.itemHandler.getStackInSlot(0).getItem() == TOOSItems.SHOTGUN.get();
        boolean hasRedstoneHandgun = this.itemHandler.getStackInSlot(0).getCount() > 0 && this.itemHandler.getStackInSlot(0).getItem() == TOOSItems.REDSTONE_HANDGUN.get();

        boolean hasDiamondToken = this.itemHandler.getStackInSlot(1).getCount() > 0 && this.itemHandler.getStackInSlot(1).getItem() == TOOSItems.DIAMOND_GUN_UPGRADE_TOKEN.get();
        boolean hasGoldToken = this.itemHandler.getStackInSlot(1).getCount() > 0 && this.itemHandler.getStackInSlot(1).getItem() == TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get();
        boolean hasIronToken = this.itemHandler.getStackInSlot(1).getCount() > 0 && this.itemHandler.getStackInSlot(1).getItem() == TOOSItems.IRON_GUN_UPGRADE_TOKEN.get();

        boolean emptyResult = this.itemHandler.getStackInSlot(2).getCount() == 0 ;
        int randomMod = (int) Math.ceil(Math.random() * 2);

        if (hasEyeCanon && hasDiamondToken && emptyResult) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.insertItem(2, new ItemStack(TOOSItems.EYE_CANNON.get()), false);

            this.itemHandler.getStackInSlot(2).getOrCreateTag().putInt("weapon_mod", randomMod);
        }
        if (hasRedstoneHandgun && hasGoldToken && emptyResult) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.insertItem(2, new ItemStack(TOOSItems.REDSTONE_HANDGUN.get()), false);

            this.itemHandler.getStackInSlot(2).getOrCreateTag().putInt("weapon_mod", randomMod);
        }
        if (hasShotgun && hasIronToken && emptyResult) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);
            this.itemHandler.insertItem(2, new ItemStack(TOOSItems.SHOTGUN.get()), false);

            this.itemHandler.getStackInSlot(2).getOrCreateTag().putInt("weapon_mod", 1);
        }
    }

}
