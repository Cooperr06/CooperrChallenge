package de.cooperr.cooperrchallenge.util;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;

import java.time.Duration;
import java.util.logging.Level;

public class Timer {

    private final CooperrChallenge plugin;

    @Getter
    private boolean running = false;
    @Getter
    private int rawTime = 0;
    private int taskId;

    public Timer(CooperrChallenge plugin) {
        this.plugin = plugin;

        if (plugin.getConfig().getConfigurationSection("timer") == null) {
            plugin.getConfig().set("timer.time", 0);

            plugin.getLogger().log(Level.INFO, "Default values set for Timer!");
        }
    }

    /**
     * Starts the timer if it is running
     */
    public void start() {

        if (running) {
            return;
        }

        running = true;
        rawTime = plugin.getConfig().getInt("timer.time");

        plugin.getServer().broadcast(Component.text("Timer started!", NamedTextColor.GOLD, TextDecoration.BOLD));

        plugin.getServer().showTitle(Title.title(Component.text("Timer", NamedTextColor.GOLD, TextDecoration.BOLD),
                Component.text("started", NamedTextColor.GREEN, TextDecoration.BOLD), Title.Times.of(
                        Duration.ofMillis(500),
                        Duration.ofMillis(1000),
                        Duration.ofMillis(500))));

        plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendActionBar(Component.text(
                formatTime(), NamedTextColor.GREEN, TextDecoration.BOLD)));

        taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (!running) {
                return;
            }

            rawTime++;
            plugin.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendActionBar(Component.text(
                    formatTime(), NamedTextColor.GREEN, TextDecoration.BOLD)));
        }, 20, 20);
    }

    /**
     * Stops the timer if it is running and sets the current time or resets it
     *
     * @param reset Whether to reset the time
     */
    public void stop(boolean reset) {

        if (!running) {
            return;
        }

        plugin.getServer().getScheduler().cancelTask(taskId);
        running = false;

        plugin.getServer().broadcast(Component.text("Timer stopped! Your time is " + formatTime() + "!",
                NamedTextColor.GOLD, TextDecoration.BOLD));

        plugin.getServer().showTitle(Title.title(Component.text("Timer", NamedTextColor.GOLD, TextDecoration.BOLD),
                Component.text("stopped", NamedTextColor.RED, TextDecoration.BOLD), Title.Times.of(
                        Duration.ofMillis(500),
                        Duration.ofMillis(1000),
                        Duration.ofMillis(500))));

        if (reset) {
            rawTime = 0;
            plugin.getConfig().set("timer.time", 0);
        } else {
            plugin.getConfig().set("timer.time", rawTime);
        }
    }

    /**
     * Formats the raw time in this pattern: 14d 3h 5m 54s or 4h 6m or 3d 21h
     *
     * @return Formatted time
     */
    private String formatTime() {
        int seconds = rawTime;
        int minutes = 0;
        int hours = 0;
        int days = 0;

        while (seconds >= 60) {
            minutes++;
            seconds -= 60;
        }
        while (minutes >= 60) {
            hours++;
            minutes -= 60;
        }
        while (hours >= 24) {
            days++;
            hours -= 24;
        }

        return (days == 0 ? "" : days + "d") + " " +
                (hours == 0 && minutes != 0 ? "" : hours + "h") + " " +
                (minutes == 0 && seconds != 0 ? "" : minutes + "m") + " " +
                seconds + "s";
    }
}
