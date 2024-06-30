package com.momosoftworks.scalingmobs.command.impl;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.momosoftworks.scalingmobs.events.MonsterEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import com.momosoftworks.scalingmobs.command.BaseCommand;
import com.momosoftworks.scalingmobs.config.ScalingMobsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.text.DecimalFormat;

public class ScalingMobsCommand extends BaseCommand
{
    public ScalingMobsCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution()
    {
        return builder
                .then(Commands.literal("health")
                        .then(Commands.literal("rate")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setHealthRate(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getHealthRate(source.getSource()))
                                )
                        )
                        .then(Commands.literal("base")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setHealthBase(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getHealthBase(source.getSource()))
                                )
                        )
                        .then(Commands.literal("max")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setHealthMax(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getHealthMax(source.getSource()))
                                )
                        )
                )
                .then(Commands.literal("damage")
                        .then(Commands.literal("rate")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setDamageRate(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDamageRate(source.getSource()))
                                )
                        )
                        .then(Commands.literal("base")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setDamageBase(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDamageBase(source.getSource()))
                                )
                        )
                        .then(Commands.literal("max")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE))
                                        .executes(source -> setDamageMax(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDamageMax(source.getSource()))
                                )
                        )
                )
                .then(Commands.literal("piercing")
                        .then(Commands.literal("rate")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setPierceRate(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getPierceRate(source.getSource()))
                                )
                        )
                        .then(Commands.literal("base")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setPierceBase(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getPierceBase(source.getSource()))
                                )
                        )
                        .then(Commands.literal("max")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setMaxPierce(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getPierceMax(source.getSource()))
                                )
                        )
                )
                .then(Commands.literal("drops")
                        .then(Commands.literal("rate")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setDropRate(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDropRate(source.getSource()))
                                )
                        )
                        .then(Commands.literal("base")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setDropBase(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDropBase(source.getSource()))
                                )
                        )
                        .then(Commands.literal("max")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0, Double.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                        .executes(source -> setMaxDrop(source.getSource(), DoubleArgumentType.getDouble(source, "amount")))
                                )
                                .then(Commands.literal("get")
                                        .executes(source -> getDropMax(source.getSource()))
                                )
                        )
                )
                .then(Commands.literal("mode")
                        .then(Commands.literal("exponential").requires(source -> source.hasPermission(2))
                                .executes(source -> setExponential(source.getSource(), true))
                        )
                        .then(Commands.literal("linear").requires(source -> source.hasPermission(2))
                                .executes(source -> setExponential(source.getSource(), false))
                        )
                        .then(Commands.literal("get")
                                .executes(source -> getExponential(source.getSource()))
                        )
                )
                .then(Commands.literal("burnDay")
                        .then(Commands.argument("amount", IntegerArgumentType.integer(0, Integer.MAX_VALUE)).requires(source -> source.hasPermission(2))
                                .executes(source -> setBurnDay(source.getSource(), IntegerArgumentType.getInteger(source, "amount")))
                        )
                        .then(Commands.literal("get")
                                .executes(source -> getBurnDay(source.getSource()))
                        )
                )
                .then(Commands.literal("get")
                        .executes(source -> getAll(source.getSource()))
                );
    }


    /**
     * Set Commands
     */
    static int setHealthRate(CommandSourceStack source, double rate) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobHealthRate(rate);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the rate of mob health scaling to " +
                white + "+" + ScalingMobsConfig.getInstance().getMobHealthRate() * 100 + "%" + yellow + " per day"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the rate of mob health scaling to " + gray + ScalingMobsConfig.getInstance().getMobHealthRate() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setHealthBase(CommandSourceStack source, double base) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobHealthBase(base);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the base health of all mobs to " +
                white + ScalingMobsConfig.getInstance().getMobHealthBase() * 100 + "%" + yellow + " of their original values"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the base health of all mobs to " + gray + ScalingMobsConfig.getInstance().getMobHealthBase() * 100 + "%" + dgray + " of their original values"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setHealthMax(CommandSourceStack source, double max) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobHealthMax(max);
        ScalingMobsConfig.getInstance().save();

        source.getPlayer().displayClientMessage(Component.literal(
                ChatFormatting.YELLOW + "Set the maximum health of all mobs to " +
                ChatFormatting.WHITE + ScalingMobsConfig.getInstance().getMobHealthMax() * 100 + "%"), false);

        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        ChatFormatting.GRAY + source.getPlayer().getName().getString() + ": " +
                        ChatFormatting.DARK_GRAY + "Set the maximum health of all mobs to " +
                        ChatFormatting.GRAY + ScalingMobsConfig.getInstance().getMobHealthMax() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setDamageRate(CommandSourceStack source, double rate) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDamageRate(rate);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the rate of mob damage scaling to " +
                white + "+" + ScalingMobsConfig.getInstance().getMobDamageRate() * 100 + "%" + yellow + " per day"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the rate of mob damage scaling to " + gray + ScalingMobsConfig.getInstance().getMobDamageRate() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setDamageBase(CommandSourceStack source, double base) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDamageBase(base);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the base damage of all mobs to " +
                white + ScalingMobsConfig.getInstance().getMobDamageBase() * 100 + "%" + yellow + " of their original values"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the base damage of all mobs to " + gray + ScalingMobsConfig.getInstance().getMobDamageBase() * 100 + "%" + dgray + " of their original values"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setDamageMax(CommandSourceStack source, double max) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDamageMax(max);

        source.getPlayer().displayClientMessage(Component.literal(
                ChatFormatting.YELLOW + "Set the maximum damage of all mobs to " +
                ChatFormatting.WHITE + ScalingMobsConfig.getInstance().getMobDamageMax() * 100 + "%"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        ChatFormatting.GRAY + source.getPlayer().getName().getString() + ": " +
                        ChatFormatting.DARK_GRAY + "Set the maximum damage of all mobs to " +
                        ChatFormatting.WHITE + ScalingMobsConfig.getInstance().getMobDamageMax() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setPierceRate(CommandSourceStack source, double rate) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setPiercingRate(rate);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the rate of mob piercing damage increase to " +
                white + "+" + ScalingMobsConfig.getInstance().getPiercingRate() * 100 + "%" + yellow + " per day"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the rate of mob piercing damage increase to " + gray + ScalingMobsConfig.getInstance().getPiercingRate() * 100 + "%" + dgray + " per day"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setPierceBase(CommandSourceStack source, double base) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setPiercingBase(base);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the base amount of mob piercing damage to " +
                white + ScalingMobsConfig.getInstance().getPiercingBase() * 100 + "%"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the base amount of mob piercing damage to " + gray + ScalingMobsConfig.getInstance().getPiercingBase() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setMaxPierce(CommandSourceStack source, double max) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMaxPiercing(max);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the maximum amount of mob piercing damage to " +
                white + ScalingMobsConfig.getInstance().getMaxPiercing() * 100 + "%"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the maximum amount of mob piercing damage to " + gray + ScalingMobsConfig.getInstance().getMaxPiercing() * 100 + "%"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setDropRate(CommandSourceStack source, double rate) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDropsRate(rate);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the rate of mob drop increase to " +
                white + "+" + ScalingMobsConfig.getInstance().getMobDropsRate() * 100 + "%" + yellow + " per day"), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the rate of mob drop increase to " + gray + ScalingMobsConfig.getInstance().getMobDropsRate() * 100 + "%" + dgray + " per day"), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setDropBase(CommandSourceStack source, double base) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDropsBase(base);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the base amount of mob drops to " +
                        white + ScalingMobsConfig.getInstance().getMobDropsBase()), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                                "Set the base amount of mob drops to " + gray + ScalingMobsConfig.getInstance().getMobDropsBase()), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setMaxDrop(CommandSourceStack source, double max) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobDropsMax(max);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set the maximum amount of mob drops to " +
                white + ScalingMobsConfig.getInstance().getMobDropsMax()), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the maximum amount of mob drops to " + gray + ScalingMobsConfig.getInstance().getMobDropsMax()), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setExponential(CommandSourceStack source, boolean exponential) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setExponentialStats(exponential);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Set mob damage scaling mode to " +
                white + (ScalingMobsConfig.getInstance().areStatsExponential() ? "exponential" : "linear")), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set mob damage scaling mode to " + gray + (ScalingMobsConfig.getInstance().areStatsExponential() ? "exponential" : "linear")), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    static int setBurnDay(CommandSourceStack source, int day) throws CommandSyntaxException
    {
        ScalingMobsConfig.getInstance().setMobsStopBurningDay(day);

        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();
        String dgray = ChatFormatting.DARK_GRAY.toString();
        String gray = ChatFormatting.GRAY.toString();

        // print changes to sender
        source.getPlayer().displayClientMessage(Component.literal(
                yellow + "Mobs will now stop burning after day " +
                white + ScalingMobsConfig.getInstance().getMobsStopBurningDay()), false);

        // print to all players
        for (Player player : source.getPlayer().level().players())
        {
            if (player != source.getPlayer())
            {
                player.displayClientMessage(Component.literal(
                        gray + source.getPlayer().getName().getString() + ": " + dgray +
                        "Set the day of the month to start burning mobs for " + gray + ScalingMobsConfig.getInstance().getMobsStopBurningDay()), false);
            }
        }

        return Command.SINGLE_SUCCESS;
    }

    
    /**
     * Get Commands
     */
    static int getHealthRate(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The rate of mob health scaling is currently " +
                white + "+" + ScalingMobsConfig.getInstance().getMobHealthRate() * 100 + "%" + yellow + " per day"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getHealthBase(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The base health for all mobs is currently " +
                white + ScalingMobsConfig.getInstance().getMobHealthBase() * 100 + "%" + yellow + " of their original values"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getHealthMax(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The maximum health for all mobs is currently " +
                white + ScalingMobsConfig.getInstance().getMobHealthMax() * 100 + "%" + yellow + " of their original values"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDamageRate(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The rate of mob damage scaling is currently " +
                white + "+" + ScalingMobsConfig.getInstance().getMobDamageRate() * 100 + "%" + yellow + " per day"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDamageBase(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The base damage for all mobs is currently " +
                white + ScalingMobsConfig.getInstance().getMobDamageBase() * 100 + "%" + yellow + " of their original values"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDamageMax(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The maximum damage for all mobs is currently " +
                white + ScalingMobsConfig.getInstance().getMobDamageMax() * 100 + "%" + yellow + " of their original values"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getPierceRate(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The rate of mob piercing damage increase is currently " +
                white + "+" + ScalingMobsConfig.getInstance().getPiercingRate() * 100 + "%" + yellow + " per day"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getPierceBase(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The base amount of mob piercing damage is currently " +
                white + ScalingMobsConfig.getInstance().getPiercingBase() * 100 + "%"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getPierceMax(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The maximum amount of mob piercing damage is currently " +
                white + ScalingMobsConfig.getInstance().getMaxPiercing() * 100 + "%"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDropRate(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The rate of mob drop scaling is currently " +
                white + "+" + ScalingMobsConfig.getInstance().getMobDropsRate() * 100 + "%" + yellow + " per day"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDropBase(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The base amount of mob drop scaling is currently " +
                white + ScalingMobsConfig.getInstance().getMobDropsBase() * 100 + "%"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getDropMax(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The maximum amount of mob drop scaling is currently " +
                white + ScalingMobsConfig.getInstance().getMobDropsMax() * 100 + "%"), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getExponential(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "The mob damage scaling mode is currently " +
                white + (ScalingMobsConfig.getInstance().areStatsExponential() ? "exponential" : "linear")), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getBurnDay(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        source.getPlayer().displayClientMessage(Component.literal(yellow + "Mobs will currently start burning on day " +
                white + ScalingMobsConfig.getInstance().getMobsStopBurningDay()), false);

        return Command.SINGLE_SUCCESS;
    }

    static int getAll(CommandSourceStack source) throws CommandSyntaxException
    {
        String yellow = ChatFormatting.YELLOW.toString();
        String white = ChatFormatting.WHITE.toString();

        ScalingMobsConfig cfg = ScalingMobsConfig.getInstance();
        int currentDay = (int) (source.getPlayer().level().getDayTime() / 24000L);
        boolean exp = cfg.areStatsExponential();

        DecimalFormat df = new DecimalFormat("#.##");

        source.getPlayer().displayClientMessage(Component.literal(yellow + "Current damage scaling: " +
                white + df.format(MonsterEvents.getMultipliedStat(100, cfg.getMobDamageBase(), cfg.getMobDamageRate(), cfg.getMobDamageMax(), currentDay, exp)) + "%"), false);

        source.getPlayer().displayClientMessage(Component.literal(yellow + "Current health scaling: " +
                white + df.format(MonsterEvents.getMultipliedStat(100, cfg.getMobHealthBase(), cfg.getMobHealthRate(), cfg.getMobHealthMax(), currentDay, exp)) + "%"), false);

        source.getPlayer().displayClientMessage(Component.literal(yellow + "Current piercing scaling: " +
                white + df.format(MonsterEvents.getMultipliedStat(100, cfg.getPiercingBase(), cfg.getPiercingRate(), cfg.getMaxPiercing(), currentDay, exp)) + "%"), false);

        source.getPlayer().displayClientMessage(Component.literal(yellow + "Current mob drop scaling: " +
                white + df.format(MonsterEvents.getMultipliedStat(100, cfg.getMobDropsBase(), cfg.getMobDropsRate(), cfg.getMobDropsMax(), currentDay, exp)) + "%"), false);

        return Command.SINGLE_SUCCESS;
    }
}
