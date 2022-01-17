package de.cooperr.cooperrchallenge;

import de.cooperr.cooperrchallenge.listener.AsyncChatListener;
import de.cooperr.cooperrchallenge.listener.PlayerJoinListener;
import de.cooperr.cooperrchallenge.listener.PlayerQuitListener;
import de.cooperr.cooperrchallenge.util.Config;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
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
    }

    @Override
    public void onDisable() {
    }

    private void commandRegistration() {

    }

    private void listenerRegistration() {
        new AsyncChatListener(plugin);
        new PlayerJoinListener(plugin);
        new PlayerQuitListener(plugin);
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return config;
    }
}
