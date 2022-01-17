package de.cooperr.cooperrchallenge.command;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import de.cooperr.cooperrchallenge.util.MinecraftCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Level;


public class ChallengeCommand extends MinecraftCommand {

    public ChallengeCommand(CooperrChallenge plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length != 0) {
            sendCommandUsage(sender);
            return true;
        }

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("challenges");
        if (section == null) {
            plugin.getLogger().log(Level.SEVERE, "Configuration section \"challenges\" is missing in config!");
            sendErrorMessage(sender);
            return true;
        }

        if (section.getKeys(false).size() == 0) {
            sender.sendMessage(Component.text("There are no available challenges!", NamedTextColor.GOLD));
            return true;
        }

        ComponentBuilder<TextComponent, TextComponent.Builder> challenges = Component.text("Available challenges:", NamedTextColor.GOLD).toBuilder();

        for (String key : section.getKeys(false)) {
            String[] keySplit = key.split("-");

            for (int i = 0; i < keySplit.length; i++) {
                keySplit[i] = keySplit[i].substring(0, 1).toUpperCase() + keySplit[i].substring(1);
            }

            StringBuilder builder = new StringBuilder();
            for (String formattedKeySplit : keySplit) {
                builder.append(formattedKeySplit).append(" ");
            }
            challenges.append(Component.text("\n- " + builder));
        }

        sender.sendMessage(challenges.build());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

    @Override
    public String getCommandName() {
        return "challenge";
    }

    @Override
    public String getCommandUsage() {
        return "/challenge";
    }
}
