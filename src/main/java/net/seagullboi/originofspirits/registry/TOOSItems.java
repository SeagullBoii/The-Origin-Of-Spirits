package net.seagullboi.originofspirits.registry;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.block.GlowkelpBlock;
import net.seagullboi.originofspirits.item.*;
import net.seagullboi.originofspirits.itemgroup.*;

public class TOOSItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OriginOfSpirits.MOD_ID);

    //Plants
    public static final RegistryObject<Item> DUCKWEED = ITEMS.register("duckweed", () -> new LilyPadItem(TOOSBlocks.DUCKWEED.get(), new Item.Properties().group(TOOSItemGroup.PLANT_GROUP)));
    public static final RegistryObject<Item> GLOWKELP = ITEMS.register("glowkelp", () -> new BlockItem(TOOSBlocks.GLOWKELP.get(), new Item.Properties().group(TOOSItemGroup.PLANT_GROUP)));

    //Food
    public static final RegistryObject<Item> SWEET_POTATO = ITEMS.register("sweet_potato", () -> new BlockItem(net.seagullboi.originofspirits.registry.TOOSBlocks.SWEET_POTATOES.get(), new Item.Properties().food(new Food.Builder().hunger(3).saturation(0.5f).build()).group(OriginOfSpiritsFoodItemGroup.tab)));

    //Weapons
    public static final RegistryObject<Item> TRIDACNA_SPEAR = ITEMS.register("tridacna_spear", () -> new TridacnaSpearItem(ModItemTier.TRIDACNA,-1,-2.6f, new Item.Properties().maxStackSize(1).rarity(ModRarities.SPITE).group(OriginOfSpiritsCombatItemGroup.tab)));
    public static final RegistryObject<Item> HEXED_SPEAR = ITEMS.register("hexed_spear", () -> new HexedSpearItem(ModItemTier.CURSED_STEEL,-1,-2.6f, new Item.Properties().maxStackSize(1).group(OriginOfSpiritsCombatItemGroup.tab)));
    public static final RegistryObject<Item> EYE_CANNON = ITEMS.register("eye_cannon", () -> new EyeCanonItem(new Item.Properties().maxStackSize(1).maxDamage(1200).group(OriginOfSpiritsCombatItemGroup.tab)));
    public static final RegistryObject<Item> PISTOL = ITEMS.register("pistol", () -> new PistolItem(new Item.Properties().maxStackSize(1).maxDamage(1200).group(OriginOfSpiritsCombatItemGroup.tab)));
    public static final RegistryObject<Item> SHOTGUN = ITEMS.register("shotgun", () -> new ShotgunItem(new Item.Properties().maxStackSize(1).maxDamage(1200).group(OriginOfSpiritsCombatItemGroup.tab)));
    public static final RegistryObject<Item> REDSTONE_HANDGUN = ITEMS.register("redstone_handgun", () -> new RedstoneHandgunItem(new Item.Properties().maxStackSize(1).maxDamage(1200).group(OriginOfSpiritsCombatItemGroup.tab)));


    //Signs
    public static final RegistryObject<Item> SACREDWOOD_SIGN = ITEMS.register("sacredwood_sign",
            () -> new SignItem(new Item.Properties().maxStackSize(16).group(TOOSItemGroup.BLOCK_GROUP),
                    net.seagullboi.originofspirits.registry.TOOSBlocks.SACREDWOOD_SIGN.get(), net.seagullboi.originofspirits.registry.TOOSBlocks.SACREDWOOD_WALL_SIGN.get()));
    public static final RegistryObject<Item> SWIRLWOOD_SIGN = ITEMS.register("swirlwood_sign",
            () -> new SignItem(new Item.Properties().maxStackSize(16).group(TOOSItemGroup.BLOCK_GROUP),
                    net.seagullboi.originofspirits.registry.TOOSBlocks.SWIRLWOOD_SIGN.get(), net.seagullboi.originofspirits.registry.TOOSBlocks.SWIRLWOOD_WALL_SIGN.get()));

    //Boats
    public static final RegistryObject<Item> SACREDWOOD_BOAT = ITEMS.register("sacredwood_boat",
            () -> new SacredwoodBoatItem(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab), "sacredwood"));
    public static final RegistryObject<Item> SWIRLWOOD_BOAT = ITEMS.register("swirlwood_boat",
            () -> new SwirlwoodBoatItem(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab), "swirlwood"));

    //Spawn Eggs
    public static final RegistryObject<ModSpawnEggs> CAECANUS_SPAWN_EGG = ITEMS.register("caecanus_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.CAECANUS, -4810354, -3484507, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));
    public static final RegistryObject<ModSpawnEggs> HOBAYOSH_SPAWN_EGG = ITEMS.register("hobayosh_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.HOBAYOSH, -3030865, -5602177, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));
    public static final RegistryObject<ModSpawnEggs> SAC_FROG_SPAWN_EGG = ITEMS.register("sac_frog_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.SAC_FROG, -7292206, -2116193, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));
    public static final RegistryObject<ModSpawnEggs> LAZOCULUS_SPAWN_EGG = ITEMS.register("lazoculus_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.LAZOCULUS, -4019746, -5977112, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));
    public static final RegistryObject<ModSpawnEggs> CLUFF_SPAWN_EGG = ITEMS.register("cluff_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.CLUFF, -1051919, -4337715, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));
    public static final RegistryObject<ModSpawnEggs> CABADOR_SPAWN_EGG = ITEMS.register("cabador_spawn_egg", () -> new ModSpawnEggs(TOOSEntityTypes.CABADOR, -6432035, -10186568, new Item.Properties().group(OriginOfSpiritsEntitiesItemGroup.tab)));

    //MISC
    public static final RegistryObject<Item> RAW_CURSED_STEEL = ITEMS.register("raw_cursed_steel", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> CURSED_STEEL = ITEMS.register("cursed_steel_ingot", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> IRON_BULLET = ITEMS.register("iron_bullet", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> SHOTGUN_SHELLS = ITEMS.register("shotgun_shells", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));

    public static final RegistryObject<Item> ABYSSAL_ESSENCE = ITEMS.register("abyssal_essence", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> IRON_GUN_UPGRADE_TOKEN = ITEMS.register("iron_gun_upgrade_token", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> GOLD_GUN_UPGRADE_TOKEN = ITEMS.register("gold_gun_upgrade_token", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> DIAMOND_GUN_UPGRADE_TOKEN = ITEMS.register("diamond_gun_upgrade_token", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));
    public static final RegistryObject<Item> CRYSTALIZED_GLASS_SHARD = ITEMS.register("crystalized_glass_shard", () -> new Item(new Item.Properties().group(OriginOfSpiritsMiscItemGroup.tab)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
