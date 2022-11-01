package fr.leskhalys.tutoplugin.listeners.startool;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StarToolListener implements Listener {

    private final List<String> list = new ArrayList<>();

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.TRIDENT)) {
            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                // Right click
                if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                    if (!list.contains(player.getName())) {
                        list.add(player.getName());
                    }
                    return;
                }

                // Left click
                if (event.getAction() == Action.LEFT_CLICK_AIR) {
                    player.launchProjectile(Fireball.class);
                }
            }
            list.remove(player.getName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void starToolLand(ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.TRIDENT) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                if (list.contains(player.getName())) {
                    // Spawn zombies
                    Location location = event.getEntity().getLocation();
                    location.setY(location.getY() + 1);

                    int SPAWN_ZOMBIES = 3;
                    for (int i = 1; i <= SPAWN_ZOMBIES; i++) {
                        Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.DROWNED);
                        location.setX(location.getX() + i);
                    }
                }
            }
        }
    }
}
