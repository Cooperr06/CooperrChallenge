package de.cooperr.cooperrchallenge.listener;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import de.cooperr.cooperrchallenge.util.MinecraftListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends MinecraftListener {

    public PlayerJoinListener(CooperrChallenge plugin) {
        super(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(player.displayName()
                .append(Component.text(" joined the server!"))
                .style(Style.style(NamedTextColor.GREEN)));
    }
}
