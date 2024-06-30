package com.momosoftworks.scalingmobs.init;

import com.mojang.brigadier.CommandDispatcher;
import com.momosoftworks.scalingmobs.command.BaseCommand;
import com.momosoftworks.scalingmobs.command.impl.ScalingMobsCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;

public class CommandInit
{
    private static final ArrayList<BaseCommand> commands = new ArrayList();

    public static void registerCommands(final RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        commands.add(new ScalingMobsCommand("scalingmobs", 2, true));

        commands.forEach(command -> {
            if (command.isEnabled() && command.setExecution() != null)
            {
                dispatcher.register(command.getBuilder());
            }
        });
    }
}
