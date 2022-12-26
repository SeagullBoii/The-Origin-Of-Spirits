package net.seagullboi.originofspirits.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.seagullboi.originofspirits.OriginOfSpirits;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionCheckCommand;
import net.seagullboi.originofspirits.commands.AbyssalFishingConditionToggleCommand;
import net.seagullboi.originofspirits.registry.TOOSItems;
import net.seagullboi.originofspirits.util.GlobalVarUtil;

@Mod.EventBusSubscriber(modid = OriginOfSpirits.MOD_ID)
public class TOOSEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new AbyssalFishingConditionToggleCommand(event.getDispatcher());
        new AbyssalFishingConditionCheckCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ItemStack bag = new ItemStack(TOOSItems.STARTER_BAG.get());
        PlayerEntity player = event.getPlayer();

        if (!GlobalVarUtil.gotBag(player)) {
            player.addItemStackToInventory(bag);
            GlobalVarUtil.setGotBag(player, true);
        }
    }



}
