package net.seagullboi.originofspirits.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AbyssalFishingConditionCheckCommand {
    public AbyssalFishingConditionCheckCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("condition").then(Commands.literal("check").then(Commands.literal("abyss_awakening").executes((command) -> checkCondition(command.getSource())))));
    }

    private int checkCondition(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        World worldIn = player.world;

        source.sendFeedback(new StringTextComponent("Condition \"Abyssal Awakening\" is set to " + OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing), true);

        return 1;
    }

}
