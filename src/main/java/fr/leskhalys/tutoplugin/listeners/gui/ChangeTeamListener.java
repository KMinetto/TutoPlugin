package fr.leskhalys.tutoplugin.listeners.gui;

import fr.leskhalys.tutoplugin.commands.changeteam.ChangeTeamCommand;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Objects;

public class ChangeTeamListener implements Listener {

    private final Color[] color = {
        Color.BLUE,
        Color.RED,
        Color.GREEN,
        Color.BLACK,
        Color.WHITE,
        Color.PURPLE,
        Color.YELLOW,
    };

    @EventHandler(priority = EventPriority.NORMAL)
    public void onChangeTeam(InventoryClickEvent event) {
        if (!event.getInventory().equals(ChangeTeamCommand.inventory)) {
            return;
        }
        if (
            event.getCurrentItem() == null ||
            event.getCurrentItem().getItemMeta() == null ||
            event.getCurrentItem().getItemMeta().getDisplayName() == null
        ) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        for (int i = 0; i <= color.length; i++) {
            if (event.getSlot() == i) {
                ItemStack[] armor = Objects.requireNonNull(player.getEquipment()).getArmorContents();
                changeColor(armor, color[i]);
                player.getEquipment().setArmorContents(armor);
                player.sendMessage(ChatColor.GOLD + "You changed your team !");
                player.closeInventory();
            }
        }

        if (event.getSlot() == 8) {
            player.closeInventory();
        }

        return;
    }

    public ItemStack[] changeColor(ItemStack[] array, Color color) {
        for (ItemStack item : array) {
            try {
                if (
                    item.getType() == Material.LEATHER_BOOTS ||
                    item.getType() == Material.LEATHER_LEGGINGS ||
                    item.getType() == Material.LEATHER_CHESTPLATE ||
                    item.getType() == Material.LEATHER_HELMET
                ) {
                    LeatherArmorMeta meta = (LeatherArmorMeta)  item.getItemMeta();
                    meta.setColor(color);
                    item.setItemMeta(meta);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return array;
    }
}
