package de.cooperr.cooperrchallenge;

import de.cooperr.cooperrchallenge.listener.AsyncChatListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CooperrChallenge extends JavaPlugin {

    private CooperrChallenge plugin;

    @Override
    public void onLoad() {
        plugin = this;
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
    }
}