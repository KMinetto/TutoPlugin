package fr.leskhalys.tutoplugin.listeners.gamble;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static fr.leskhalys.tutoplugin.commands.gamble.GambleCommand.invs;

public class GambleListener implements Listener {

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        if (!invs.contains((event.getInventory()))) return;
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta() == null) return;

        event.setCancelled(true);
    }
}
