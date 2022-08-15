package net.seagullboi.originofspirits.util;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OriginOfSpirits.MOD_ID);

    public static final RegistryObject<SoundEvent> HEXAYOSH_HURT = registerSoundEvent("hexayosh_hurt");
    public static final RegistryObject<SoundEvent> HEXAYOSH_DEATH = registerSoundEvent("hexayosh_death");
    public static final RegistryObject<SoundEvent> HEXAYOSH_AMBIENT = registerSoundEvent("hexayosh_ambient");
    public static final RegistryObject<SoundEvent> HEXAYOSH_CHARGE = registerSoundEvent("hexayosh_charge");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(OriginOfSpirits.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
