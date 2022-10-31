package fr.leskhalys.tutoplugin.commands.heal;

import fr.leskhalys.tutoplugin.Main;
import fr.leskhalys.tutoplugin.commands.GeneralCommand;
import fr.leskhalys.tutoplugin.messages.Messages;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand extends GeneralCommand {

    public HealCommand(Main main, String permission) {
        super(main, "heal", permission);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isCorrectName(command, this.name)) {
            if (isPlayer(sender)) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    commandWithNoArgument(player);
                } else if (args.length == 1) {
                    commandWithArguments(args, player);
                } else {
                    player.sendMessage(Messages.TOO_FEW_ARGUMENTS.getMessage());
                    player.sendMessage("Usage: /heal");
                }
            } else {
                sender.sendMessage(Messages.NOT_A_PLAYER.getMessage());
            }

            return true;
        }

        return false;
    }

    private TextComponent createClickableMessage() {
        TextComponent message = new TextComponent("Would you like to be healed ?");
        message.setColor(ChatColor.BLUE);
        message.setBold(true);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/heal healme"));
        message.setHoverEvent(new HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            new Text("Click me to be healed"))
        );
        return message;
    }

    @Override
    protected void commandWithNoArgument(Player player) {
        player.spigot().sendMessage(createClickableMessage());
    }

    @Override
    protected void commandWithArguments(String[] args, Player player) {
        if (Objects.equals(args[0], "healme")) {
            player.setHealth(20.0d);
            player.sendMessage(ChatColor.DARK_GREEN + "You've been healed !");
        }
    }
}
