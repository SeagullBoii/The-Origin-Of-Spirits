package net.seagullboi.originofspirits.registry;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.block.tile_entities.GunsmithingTableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

	public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
			DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, OriginOfSpirits.MOD_ID);
	//Signs
	public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITIES =
			TILE_ENTITIES.register("sign", () -> TileEntityType.Builder.create(ModSignTileEntity::new,
					net.seagullboi.originofspirits.registry.TOOSBlocks.SACREDWOOD_SIGN.get(),
					net.seagullboi.originofspirits.registry.TOOSBlocks.SACREDWOOD_WALL_SIGN.get(),
					net.seagullboi.originofspirits.registry.TOOSBlocks.SWIRLWOOD_SIGN.get(),
					net.seagullboi.originofspirits.registry.TOOSBlocks.SWIRLWOOD_WALL_SIGN.get()
			).build(null));

	//Crafting
	public static final RegistryObject<TileEntityType<GunsmithingTableTileEntity>> GUNSMITHING_TABLE = TILE_ENTITIES.register("gunsmithing_table_tile",
			() -> TileEntityType.Builder.create(GunsmithingTableTileEntity::new, net.seagullboi.originofspirits.registry.TOOSBlocks.GUNSMITHING_TABLE_BLOCK.get()).build(null ));

	public static void register(IEventBus eventBus) {
		TILE_ENTITIES.register(eventBus);
	}


}
