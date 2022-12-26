package net.seagullboi.originofspirits.block.tile_entities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.seagullboi.originofspirits.api.IInventoryHandlingBlockEntity;
import net.seagullboi.originofspirits.block.tile_entities.recipes.PressingMachineRecipe;
import net.seagullboi.originofspirits.registry.ModTileEntities;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

public class PressingMachineTileEntity extends TileEntity implements IInventoryHandlingBlockEntity {

    public final ItemStackHandler itemHandler = createHandler();
    public final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public static ArrayList<Item> multipleResultInputs = new ArrayList<>(Arrays.asList(Blocks.GLOWSTONE.asItem()));
    public static ArrayList<Item> inputs = new ArrayList<>(Arrays.asList(Items.IRON_INGOT, Items.GOLD_INGOT, Items.DIAMOND, Items.SUGAR_CANE, TOOSBlocks.HARD_SUGAR_CANE.get().asItem(), TOOSItems.SMENEREL.get(),
                                           Blocks.GLOWSTONE.asItem()));
    public boolean isPowered = false;
    public int progressTimer = 0;
    public boolean progressIncreasing = true;

    public PressingMachineTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public PressingMachineTileEntity() {
        this(ModTileEntities.PRESSING_MACHINE.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {

        if (side == Direction.DOWN) {
            if (!inputs.contains(itemHandler.getStackInSlot(0).getItem())) {
                if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                    world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
                    return handler.cast();
                }
            }
        } else if (side != Direction.DOWN || side != Direction.UP) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                if (multipleResultInputs.contains(itemHandler.getStackInSlot(0).getItem())) {
                    if (getMultipleResults(16, itemHandler.getStackInSlot(0))) {
                        world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
                        return handler.cast();
                    }
                } else {
                    world.notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
                    return handler.cast();
                }
            }
        }

        return super.getCapability(cap, side);
    }

    public boolean getMultipleResults(int count, ItemStack stack) {
        return multipleResultInputs.contains(stack.getItem()) && stack.getCount() < count;
    }

    public boolean isPowered() {
        return world.isBlockPowered(pos);
    }

    public void craft(BlockPos pos) {
        PressingMachineRecipe.craft(itemHandler, pos, world, this);
    }

    @Override
    public void setHandler(ItemStackHandler handler) {
    }

    @Override
    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }

    // called to generate NBT for a syncing packet when a client loads a chunk that this TE is in
    @Override
    public CompoundNBT getUpdateTag() {
        // we want to tell the client about as much data as it needs to know
        // since it doesn't know any data at this point, we can usually just defer to write() above
        // if you have data that would be written to the disk but the client doesn't ever need to know,
        // you can just sync the need-to-know data instead of calling write()
        // there's an equivalent method for reading the update tag but it just defaults to read() anyway
        return this.write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);

        // the number here is generally ignored for non-vanilla TileEntities, 0 is safest
        return new SUpdateTileEntityPacket(this.getPos(), 0, nbt);
    }

    // this method gets called on the client when it receives the packet that was sent in the previous method
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        this.read(getBlockState(), packet.getNbtCompound());
    }

}
