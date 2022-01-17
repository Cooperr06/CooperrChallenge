package de.cooperr.cooperrchallenge;

import de.cooperr.cooperrchallenge.command.ChallengeCommand;
import de.cooperr.cooperrchallenge.listener.AsyncChatListener;
import de.cooperr.cooperrchallenge.listener.PlayerJoinListener;
import de.cooperr.cooperrchallenge.listener.PlayerQuitListener;
import de.cooperr.cooperrchallenge.util.Config;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Getter
public final class CooperrChallenge extends JavaPlugin {

    private CooperrChallenge plugin;
    private Config config;

    @Override
    public void onLoad() {
        plugin = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        commandRegistration();
        listenerRegistration();
        permissionRegistration();
    }

    @Override
    public void onDisable() {
    }

    private void commandRegistration() {
        new ChallengeCommand(plugin);
    }

    private void listenerRegistration() {
        new AsyncChatListener(plugin);
        new PlayerJoinListener(plugin);
        new PlayerQuitListener(plugin);
    }

    private void permissionRegistration() {
        getServer().getPluginManager().addPermission(new Permission("cooperrchallenge.command.challenge"));

        getServer().getPluginManager().getPermissions().forEach(permission -> {
            if (permission.getName().startsWith("cooperrchallenge.command.") && !permission.getName().contains("*")) {
                permission.addParent("cooperrchallenge.command.*", true);
            } else if (!permission.getName().equals("cooperrchallenge.*") && permission.getName().contains("*")) {
                permission.addParent("cooperrchallenge.*", true);
            }
        });
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return config;
    }
}
