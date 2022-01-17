package de.cooperr.cooperrchallenge.listener;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import de.cooperr.cooperrchallenge.util.MinecraftListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends MinecraftListener {

    public PlayerQuitListener(CooperrChallenge plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.quitMessage(player.displayName()
                .append(Component.text(" left the server!"))
                .style(Style.style(NamedTextColor.RED)));
    }
}
