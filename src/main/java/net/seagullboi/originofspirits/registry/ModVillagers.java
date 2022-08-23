package net.seagullboi.originofspirits.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, OriginOfSpirits.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<PointOfInterestType> GUNSMITH_POI = POINT_OF_INTEREST_TYPES.register("gunsmith",
            () -> new PointOfInterestType("gunsmith", PointOfInterestType.getAllStates(net.seagullboi.originofspirits.registry.TOOSBlocks.GUNSMITHING_TABLE_BLOCK.get()), 1, 1));
    public static final RegistryObject<VillagerProfession> GUNSMITH = VILLAGER_PROFESSIONS.register("gunsmith",
            () -> new VillagerProfession("gunsmith", GUNSMITH_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_WEAPONSMITH));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "registerBlockStates", PointOfInterestType.class).invoke(null, GUNSMITH_POI.get());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POINT_OF_INTEREST_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
