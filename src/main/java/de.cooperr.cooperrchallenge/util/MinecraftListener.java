package de.cooperr.cooperrchallenge.util;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import org.bukkit.event.Listener;

public abstract class MinecraftListener implements Listener {

    private final CooperrChallenge plugin;

    public MinecraftListener(CooperrChallenge plugin) {
        this.plugin = plugin;
        registerListener();
    }

    public void registerListener() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
