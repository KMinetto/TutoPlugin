package fr.leskhalys.tutoplugin.commands.gamble;

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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GambleCommand extends GeneralCommand {

    public static List<Inventory> invs = new ArrayList<>();
    private int itemIndex = 0;
    private static final ItemStack[] gambleItems = {
        new ItemStack(Material.COARSE_DIRT, 32),
        new ItemStack(Material.BOOK, 16),
        new ItemStack(Material.IRON_INGOT, 64),
        new ItemStack(Material.NETHER_STAR, 8),
        new ItemStack(Material.DIAMOND, 16),
        new ItemStack(Material.BEEF, 64),
        new ItemStack(Material.QUARTZ_BLOCK, 32),
        new ItemStack(Material.ACACIA_WOOD, 12),
        new ItemStack(Material.ENDER_PEARL, 16),
        new ItemStack(Material.DIAMOND, 32),
    };

    public GambleCommand(Main main, String permission) {
        super(main, "gamble", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                Player player = (Player) sender;
                commandWithNoArgument(player);
            } else {
                sender.sendMessage(Messages.NOT_A_PLAYER.getMessage());
            }

            return true;
        }

        return false;
    }

    private void pay(Player player) {
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        diamond.setAmount(3);

        if (player.getInventory().getItemInMainHand().isSimilar(diamond)) {
            player.getInventory().removeItem(diamond);
            spinGui(player);
        } else {
            player.sendMessage(ChatColor.DARK_RED + "You need three diamonds to gamble !");
            player.sendMessage(
                ChatColor.GOLD + "Make sure you have " +
                ChatColor.AQUA + "3x Diamonds" +
                ChatColor.GOLD + " in your main hand."
            );
        }
    }

    private void customizeInventory(Inventory inv) {
        ItemStack hopper = new ItemStack(Material.HOPPER);
        ItemMeta meta = hopper.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GRAY + "|");
            hopper.setItemMeta(meta);
            inv.setItem(4, hopper);
        }
    }

    private void shuffle(Inventory inv) {
        int gambleItemLength = gambleItems.length;
        int startingIndex = ThreadLocalRandom.current().nextInt(gambleItemLength);

        for (int i = 0; i < startingIndex; i++) {
            for (int j = gambleItemLength - 1; j < 18; j++) {
                inv.setItem(j, gambleItems[(j + itemIndex) % gambleItemLength]);
            }
            itemIndex++;
        }

        customizeInventory(inv);
    }

    private void seconds(Inventory inv, Player player) {
        Random r = new Random();
        double seconds = 7.0d + (12.0d - 7.0d) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if (done) return;
                ticks++;
                delay+= 1 / (20 * seconds);
                if (ticks > delay * 10) {
                    ticks = 0;

                    for (int i = 9; i < 18; i++) {
                        inv.setItem(i, gambleItems[(i + itemIndex) % gambleItems.length]);
                    }
                    itemIndex++;

                    if (delay > .5) {
                        done = true;
                        new BukkitRunnable() {
                            public void run() {
                                ItemStack item = inv.getItem(13);
                                player.getInventory().addItem(item);
                                player.sendMessage(
                                        ChatColor.DARK_GREEN + "You won " +
                                        ChatColor.GOLD + item +
                                        ChatColor.DARK_GREEN + " !"
                                );
                                player.updateInventory();
                                player.closeInventory();
                                cancel();
                            }
                        }.runTaskLater(Main.getPlugin(Main.class), 50);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(this.main, 0, 1);
    }

    private void spinGui(final Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Good Luck !");
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);
        seconds(inv, player);
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        pay(player);
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {

    }
}
