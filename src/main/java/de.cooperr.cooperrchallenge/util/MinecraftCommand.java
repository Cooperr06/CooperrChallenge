package de.cooperr.cooperrchallenge.util;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

import java.util.logging.Level;

public abstract class MinecraftCommand implements TabExecutor {

    private final CooperrChallenge plugin;

    public MinecraftCommand(CooperrChallenge plugin) {
        this.plugin = plugin;
        registerCommand();
    }

    public void registerCommand() {
        PluginCommand pluginCommand = plugin.getCommand(getCommandName());
        if (pluginCommand == null) {
            plugin.getLogger().log(Level.SEVERE, "Failed to register command \"" + getCommandName() + "\"");
        } else {
            pluginCommand.setExecutor(this);
        }
    }

    public abstract String getCommandName();
    public abstract String getCommandUsage();

    public void sendCommandUsage(CommandSender sender) {
        sender.sendMessage(Component.text("Usage: " + getCommandUsage(), NamedTextColor.DARK_RED));
    }

    public void sendWrongSenderMessage(CommandSender sender) {
        sender.sendMessage(Component.text("You have to be a player to use this command!", NamedTextColor.DARK_RED));
    }
}
