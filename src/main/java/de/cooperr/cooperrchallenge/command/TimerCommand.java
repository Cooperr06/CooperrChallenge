package de.cooperr.cooperrchallenge.command;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import de.cooperr.cooperrchallenge.util.MinecraftCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimerCommand extends MinecraftCommand {

    public TimerCommand(CooperrChallenge plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 1) {
            sendCommandUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "pause" -> {
                if (!plugin.getTimer().isRunning()) {
                    sendErrorMessage("The timer is not running!", sender);
                    return true;
                }

                plugin.getTimer().stop(false);
                return true;
            }
            case "start" -> {
                if (plugin.getTimer().isRunning()) {
                    sendErrorMessage("The timer is already running!", sender);
                    return true;
                }

                plugin.getTimer().start();
                return true;
            }
            case "stop" -> {
                if (!plugin.getTimer().isRunning()) {
                    sendErrorMessage("The timer is not running!", sender);
                    return true;
                }

                plugin.getTimer().stop(true);
                return true;
            }
            default -> {
                sendCommandUsage(sender);
                return true;
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> tabCompletion = new ArrayList<>();

        if (args.length == 0) {
            tabCompletion.addAll(Arrays.asList("pause", "start", "stop"));
            return tabCompletion;
        } else if (args.length == 1) {

            if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")) {
                return null;
            }

            tabCompletion.addAll(Arrays.asList("pause", "start", "stop"));
            tabCompletion.removeIf(s -> !s.startsWith(args[0]));

            return tabCompletion;
        }
        return null;
    }

    @Override
    public String getCommandName() {
        return "timer";
    }

    @Override
    public String getCommandUsage() {
        return "/timer <action>";
    }
}
