package de.cooperr.cooperrchallenge.listener;

import de.cooperr.cooperrchallenge.CooperrChallenge;
import de.cooperr.cooperrchallenge.util.MinecraftListener;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class AsyncChatListener extends MinecraftListener {

    public AsyncChatListener(CooperrChallenge plugin) {
        super(plugin);
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();

        event.message(player.displayName().style(Style.style(NamedTextColor.BLUE))
                .append(Component.text(" » ", NamedTextColor.GRAY))
                .append(event.originalMessage()
                        .style(Style.style(NamedTextColor.GRAY))));
    }
}
