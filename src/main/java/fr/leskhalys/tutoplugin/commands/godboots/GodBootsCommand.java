package fr.leskhalys.tutoplugin.commands.godboots;

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
import java.util.Objects;

public class GodBootsCommand extends GeneralCommand {

    public GodBootsCommand(Main main, String permission) {
        super(main, "godboots", permission);
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

    private List<String> setItemLore() {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Boots made for the Minecraft Gods");

        return lore;
    }

    private void addThingsToItem(ItemMeta meta) {
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
    }

    private ItemStack getGodBoots() {
        ItemStack godBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = godBoots.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Boots of Leaping");
            meta.setLore(setItemLore());
            addThingsToItem(meta);
            godBoots.setItemMeta(meta);

            return godBoots;
        }

        return null;
    }

    private void dropItem(Player player) {
        Location location = player.getLocation();
        World world = player.getWorld();
        world.dropItemNaturally(location, Objects.requireNonNull(getGodBoots()));
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            dropItem(player);
            player.sendMessage(ChatColor.DARK_GREEN + "The Minecraft Legends gave you a gift near you.");
        }
        player.getInventory().addItem(getGodBoots());
        player.sendMessage(ChatColor.DARK_GREEN + "The Minecraft Legends gave you a gift.");
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {

    }
}
