package net.seagullboi.originofspirits.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.seagullboi.originofspirits.OriginofspiritsModVariables;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AbyssalFishingConditionToggleCommand {
    public AbyssalFishingConditionToggleCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("condition").then(Commands.literal("toggle").requires(s -> s.hasPermissionLevel(2)).then(Commands.literal("abyss_awakening").executes((command) -> toggleCondition(command.getSource())))));
    }

    private int toggleCondition(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        World worldIn = player.world;

        OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing = !OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing;
        OriginofspiritsModVariables.WorldVariables.get(worldIn).syncData(worldIn);

        source.sendFeedback(new StringTextComponent("Condition \"Abyssal Awakening\" has been set to " + OriginofspiritsModVariables.WorldVariables.get(worldIn).AbyssalFishing), true);

        return 1;
    }

}
