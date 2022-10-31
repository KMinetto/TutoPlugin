package fr.leskhalys.tutoplugin.commands.launch;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LaunchCommand extends GeneralCommand {

    public LaunchCommand(Main main, String permission) {
        super(main, "launch", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
           if (isPlayer(sender)) {
               Player player = (Player) sender;
               if (args.length == 0) {
                   commandWithNoArgument(player);
               } else if (args.length == 1) {
                   if (isNum(args[0])) {
                       commandWithArguments(args, player);
                   } else {
                       player.sendMessage(ChatColor.RED + "Usage: /launch <number-value>");
                   }
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

    private boolean isNum(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Zooooom!");
        player.setVelocity(
            player.getLocation().getDirection().multiply(2).setY(2)
        );
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {
        player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "Zooooom!");
        player.setVelocity(
            player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(2)
        );
    }
}
