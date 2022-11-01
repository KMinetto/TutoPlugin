package fr.leskhalys.tutoplugin.listeners.godboots;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class GodBootsFallListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (player.getInventory().getBoots() != null) {
                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots of Leaping")) {
                        if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
