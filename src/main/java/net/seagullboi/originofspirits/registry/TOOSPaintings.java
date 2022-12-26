package net.seagullboi.originofspirits.registry;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSPaintings {
    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<PaintingType> DOOMSDAY = PAINTING_TYPES.register("doomsday_painting", () -> new PaintingType(32, 32));

}
