package fr.leskhalys.tutoplugin.commands.startool;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StarToolCommand extends GeneralCommand {

    public StarToolCommand(Main main, String permission) {
        super(main, "startool", permission);
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

    private void setItemFlags(ItemMeta meta) {
        meta.addEnchant(Enchantment.LOYALTY, 3, true);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    }

    private ItemMeta setItemLore(ItemStack starTool) {
        List<String> lore = new ArrayList<>();
        ItemMeta meta = starTool.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Ancient Trident");
            lore.add("");
            lore.add(ChatColor.GRAY + "(Left Click) " + ChatColor.GREEN + "Shoot explosives");
            lore.add(ChatColor.GRAY + "(Right Click) " + ChatColor.GREEN + "Spawns minions");
            setItemFlags(meta);
            meta.setLore(lore);
        }

        return meta;
    }

    private void dropItemToWorld(Player player, ItemStack starTool) {
        Location location = player.getLocation();
        World world = player.getWorld();
        world.dropItemNaturally(location, starTool);
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        ItemStack starTool = new ItemStack(Material.TRIDENT);
        starTool.setItemMeta(setItemLore(starTool));

        if (player.getInventory().firstEmpty() == -1) {
            dropItemToWorld(player, starTool);
            player.sendMessage(ChatColor.DARK_GREEN + "An Item was dropped near to you");
        }
        player.getInventory().addItem(starTool);
        player.sendMessage(ChatColor.DARK_GREEN + "An item was dropped to you");
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {

    }
}
