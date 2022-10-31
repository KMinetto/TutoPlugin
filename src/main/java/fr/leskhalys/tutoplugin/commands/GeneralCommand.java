package fr.leskhalys.tutoplugin.commands;

import fr.leskhalys.tutoplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class GeneralCommand implements CommandExecutor {
    protected final Main main;
    protected String name;
    protected final String permission;

    public GeneralCommand(Main main, String name, String permission) {
        this.main = main;
        this.name = name;
        this.permission = permission;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    protected boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    protected boolean isPlayerOp(Player player) {
        return player.isOp();
    }

    protected boolean hasPermission(Player player) {
        return player.hasPermission(this.permission);
    }

    protected boolean isCorrectName(Command command, String name) {
        return command.getName().equalsIgnoreCase(name);
    }

    protected abstract void commandWithNoArgument(Player player);
    protected abstract void commandWithArguments(String[] args, Player player);
}
