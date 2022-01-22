package de.cooperr.cooperrchallenge.util;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class MinecraftCommand implements TabExecutor {

    public final CooperrChallenge plugin;

    public MinecraftCommand(CooperrChallenge plugin) {
        this.plugin = plugin;

        PluginCommand pluginCommand = plugin.getCommand(getCommandName());
        if (pluginCommand == null) {
            plugin.getLogger().log(Level.SEVERE, "Failed to register command \"" + getCommandName() + "\"");
        } else {
            pluginCommand.setExecutor(this);
        }
    }

    /**
     * Sends the right command usage to a sender
     *
     * @param sender Sender to send to the message
     */
    public void sendCommandUsage(CommandSender sender) {
        sender.sendMessage(Component.text("Usage: " + getCommandUsage(), NamedTextColor.DARK_RED));
    }

    /**
     * Sends a message to a sender if it has to be a player
     *
     * @param sender Sender to send to the message
     */
    protected void sendWrongSenderMessage(CommandSender sender) {
        sender.sendMessage(Component.text("You have to be a player to use this command!", NamedTextColor.DARK_RED));
    }

    /**
     * Sends a message to a sender if something went wrong
     *
     * @param sender Sender to send to the message
     */
    public void sendErrorMessage(String message, CommandSender sender) {
        sender.sendMessage(Component.text(message, NamedTextColor.DARK_RED));
    }

    /**
     * @return Command name of the command
     */
    public abstract String getCommandName();

    /**
     * @return Command usage of the command with "/"
     */
    public abstract String getCommandUsage();
}
