package fr.leskhalys.tutoplugin.listeners.godboots;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GodBootsListeners implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getBoots() != null) {
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping")) {
                if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                    if (
                        (event.getFrom().getY() < event.getTo().getY()) &&
                        (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
                    ) {
                        player.setVelocity(player.getLocation().getDirection().multiply(1).setY(1));
                    }
                }
            }
        }
    }
}
