package net.seagullboi.originofspirits.datagen.client;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.registry.TOOSBlocks;
import net.seagullboi.originofspirits.registry.TOOSItems;

import javax.annotation.Nonnull;

public class TOOSItemModelProvider extends ItemModelProvider {

    public TOOSItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, OriginOfSpirits.MOD_ID, existingFileHelper);
    }

    @Override
    public void registerModels() {
        simpleItem(TOOSItems.IRON_GUN_UPGRADE_TOKEN.get());
        simpleItem(TOOSItems.GOLD_GUN_UPGRADE_TOKEN.get());
        simpleItem(TOOSItems.DIAMOND_GUN_UPGRADE_TOKEN.get());

        simpleItem(TOOSItems.BARLEY_SEEDS.get());

        simpleItem(TOOSItems.SHOTGUN_SHELLS.get());
        simpleItem(TOOSItems.IRON_BULLET.get());

        blockItem(TOOSBlocks.POLISHED_DECEPTONE_SLAB.get().asItem(), TOOSBlocks.POLISHED_DECEPTONE_SLAB.get());
        blockItem(TOOSBlocks.POLISHED_DECEPTONE_STAIRS.get().asItem(), TOOSBlocks.POLISHED_DECEPTONE_STAIRS.get());
        blockItem(TOOSBlocks.POLISHED_DECEPTONE_WALL.get().asItem(), TOOSBlocks.POLISHED_DECEPTONE_WALL.get());
    }

    @Nonnull
    @Override
    public String getName() {
        return "Item Models";
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(OriginOfSpirits.MOD_ID,"items/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(OriginOfSpirits.MOD_ID,"items/" + item.getRegistryName().getPath()));
    }

    protected ItemModelBuilder blockItem(Item item, Block block) {
        return withExistingParent(item.getRegistryName().getPath(), OriginOfSpirits.MOD_ID + ":block/" + block.getRegistryName().getPath());
    }

    protected ItemModelBuilder wallItem(Item item, Block block) {
        return withExistingParent(item.getRegistryName().getPath(), OriginOfSpirits.MOD_ID + ":block/" + block.getRegistryName().getPath());
    }
}
