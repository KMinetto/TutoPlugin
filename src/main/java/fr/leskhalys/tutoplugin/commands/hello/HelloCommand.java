package fr.leskhalys.tutoplugin.commands.hello;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloCommand extends GeneralCommand {

    public HelloCommand(Main main, String permission) {
        super(main, "hello", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                final Player player = (Player) sender;
                if (hasPermission(player) || isPlayerOp(player)) {
                    player.sendMessage(Messages.SEND_HELLO.getMessage());
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
