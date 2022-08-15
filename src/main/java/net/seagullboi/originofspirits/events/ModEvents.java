package net.seagullboi.originofspirits.events;

import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionCheckCommand;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionToggleCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AbyssalFishingConditionToggleCommand(event.getDispatcher());
        new AbyssalFishingConditionCheckCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
