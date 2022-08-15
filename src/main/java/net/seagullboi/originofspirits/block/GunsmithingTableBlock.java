package net.seagullboi.originofspirits.block;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.vector.Vector3d;
import net.seagullboi.originofspirits.block.tile_entities.GunsmithingTableTileEntity;
import net.seagullboi.originofspirits.block.tile_entities.container.GunsmithingTableContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GunsmithingTableBlock extends Block {

    public GunsmithingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult pHit) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
            //pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);

            return ActionResultType.CONSUME;
        }

    }

    @Override
    public void tick (BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if(tileEntity instanceof GunsmithingTableTileEntity) {
            ((GunsmithingTableTileEntity) tileEntity).upgradeWeapon();
        }

        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);

    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.originofspirits.gunsmithing_table");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new GunsmithingTableContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new GunsmithingTableTileEntity();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.isIn(newState.getBlock())) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
                this.dropItems(worldIn, state, pos, tileEntity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }


    public void dropItems(World worldIn, BlockState state, BlockPos pos, TileEntity tileEntity) {
        double randPos = 0.5 + Math.random() / 2;

        if (tileEntity instanceof GunsmithingTableTileEntity) {
            GunsmithingTableTileEntity table = (GunsmithingTableTileEntity) tileEntity;

            if (worldIn instanceof World && !worldIn.isRemote()) {

                for (int count = 0; count < table.itemHandler.getStackInSlot(0).getCount(); count++) {
                    ItemEntity slotOne = new ItemEntity(worldIn, pos.getX() + randPos, pos.getY() + randPos, pos.getZ() + randPos, new ItemStack(table.itemHandler.getStackInSlot(0).getItem()));
                    slotOne.setPickupDelay(10);
                    worldIn.addEntity(slotOne);
                }
                for (int count = 0; count < table.itemHandler.getStackInSlot(1).getCount(); count++) {
                    ItemEntity slotTwo = new ItemEntity(worldIn, pos.getX() + randPos, pos.getY() + randPos, pos.getZ() + randPos, new ItemStack(table.itemHandler.getStackInSlot(1).getItem()));
                    slotTwo.setPickupDelay(10);
                    worldIn.addEntity(slotTwo);
                }

                for (int count = 0; count < table.itemHandler.getStackInSlot(2).getCount(); count++) {
                    ItemEntity slotThree = new ItemEntity(worldIn, pos.getX() + randPos, pos.getY() + randPos, pos.getZ() + randPos, new ItemStack(table.itemHandler.getStackInSlot(2).getItem()));
                    slotThree.setPickupDelay(10);
                    worldIn.addEntity(slotThree);
                }
            }
        }
    }
}
