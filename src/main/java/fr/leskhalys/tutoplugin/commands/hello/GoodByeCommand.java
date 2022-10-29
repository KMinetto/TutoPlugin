package fr.leskhalys.tutoplugin.commands.hello;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GoodByeCommand extends GeneralCommand {

    public GoodByeCommand(Main main, String permission) {
        super(main, "goodbye", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                Player player = (Player) sender;
                if (isPlayerOp(player) || hasPermission(player)) {
                    player.sendMessage(Messages.SEND_GOODBYE.getMessage());
                } else {
                    player.sendMessage(Messages.PLAYER_NOT_OP.getMessage());
                }
            } else {
                sender.sendMessage(Messages.NOT_A_PLAYER.getMessage());
            }
            return true;
        }
        return false;
    }
}
