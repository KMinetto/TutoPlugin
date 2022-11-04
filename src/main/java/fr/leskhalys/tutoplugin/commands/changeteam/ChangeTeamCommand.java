package fr.leskhalys.tutoplugin.commands.changeteam;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChangeTeamCommand extends GeneralCommand {

    public static Inventory inventory;
    private final String[] arrayName = {
        ChatColor.BLUE + "" + ChatColor.BOLD + "Blue team",
        ChatColor.DARK_RED + "" + ChatColor.BOLD + "Red team",
        ChatColor.GREEN + "" + ChatColor.BOLD + "Green team",
        ChatColor.GRAY + "" + ChatColor.BOLD + "Black team",
        ChatColor.WHITE + "" + ChatColor.BOLD + "White team",
        ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Purple team",
        ChatColor.GOLD + "" + ChatColor.BOLD + "Yellow team",
        " ",
        ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.BOLD + "Close",
    };
    private final Material[] arrayMaterial = {
        Material.BLUE_CONCRETE,
        Material.RED_CONCRETE,
        Material.GREEN_CONCRETE,
        Material.BLACK_CONCRETE,
        Material.WHITE_CONCRETE,
        Material.PURPLE_CONCRETE,
        Material.YELLOW_CONCRETE,
        Material.WHITE_STAINED_GLASS_PANE,
        Material.BARRIER,
    };

    public ChangeTeamCommand(Main main, String permission) {
        super(main, "changeteam", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    commandWithNoArgument(player);
                } else {
                    player.sendMessage(Messages.TOO_FEW_ARGUMENTS.getMessage());
                }
            } else {
                sender.sendMessage(Messages.NOT_A_PLAYER.getMessage());
            }
            return true;
        }
        return false;
    }

    private ItemMeta createItemMeta(ItemStack item, String lore, String name) {
        ItemMeta meta = item.getItemMeta();
        List<String> itemLore = new ArrayList<>();
        itemLore.add(lore);

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(itemLore);
        }

        return meta;
    }

    private void createInventory() {
        ItemStack item;
        int table = 9;
        inventory = Bukkit.createInventory(null, table, ChatColor.DARK_RED + "Select Team");

        for (int i = 0; i < table; i++) {
            item = new ItemStack(arrayMaterial[i]);
            item.setType(arrayMaterial[i]);

            if (i == table - 2) {
                item.setItemMeta(createItemMeta(item, "", arrayName[i]));
                inventory.setItem(i, item);
            } else if (i == table - 1) {
                item.setItemMeta(createItemMeta(item, ChatColor.GRAY + "Click to close !", arrayName[i]));
                inventory.setItem(i, item);
            } else {
                item.setItemMeta(createItemMeta(item, ChatColor.GRAY + "Click to join !", arrayName[i]));
                inventory.setItem(i, item);
            }
        }
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        //Open the GUI
        createInventory();
        player.openInventory(inventory);
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {

    }
}
