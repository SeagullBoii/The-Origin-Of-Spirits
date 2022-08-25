package net.seagullboi.originofspirits.events;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.seagullboi.originofspirits.OriginOfSpirits;

public class TOOSSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OriginOfSpirits.MOD_ID);

    //Weapons
    public static final RegistryObject<SoundEvent> GUN = registerSoundEvent("gunshot");
    public static final RegistryObject<SoundEvent> SHOTGUN = registerSoundEvent("shotgun_shot");

    //Music
    public static final RegistryObject<SoundEvent> CURSED_ISLANDS_MUSIC = registerSoundEvent("cursed_islands_music");
    public static final RegistryObject<SoundEvent> PINK_DESERT = registerSoundEvent("pink_desert_music");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(OriginOfSpirits.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
