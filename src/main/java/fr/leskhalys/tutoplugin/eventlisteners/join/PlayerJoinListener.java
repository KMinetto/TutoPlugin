package fr.leskhalys.tutoplugin.eventlisteners.join;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Welcome to this server " +
            ChatColor.GOLD + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.LIGHT_PURPLE + "" +
            ChatColor.BOLD + " !"
        );
    }
}
