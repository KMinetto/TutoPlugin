package fr.leskhalys.tutoplugin.commands.fly;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends GeneralCommand {

    public FlyCommand(Main main, String permission) {
        super(main, "fly", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                Player player = (Player) sender;
                if (isPlayerOp(player) || hasPermission(player)) {
                    if (args.length == 0) {
                        commandWithNoArgument(player);
                    } else {
                        player.sendMessage(Messages.TOO_FEW_ARGUMENTS.getMessage());
                    }
                } else {
                    player.sendMessage(Messages.PLAYER_NOT_OP.getMessage());
                }
            } else {
                sender.sendMessage(Messages.NOT_A_PLAYER.getMessage());
            }
        }
        return false;
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.DARK_GREEN + "Fly mod is now " + ChatColor.GOLD + "active.");
        } else {
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.DARK_GREEN + "Fly mod is now " + ChatColor.GOLD + "inactive.");
        }
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {

    }
}
