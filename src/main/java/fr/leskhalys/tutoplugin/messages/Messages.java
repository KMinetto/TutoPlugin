package fr.leskhalys.tutoplugin.messages;

import org.bukkit.ChatColor;

public enum Messages {

    TOO_FEW_ARGUMENTS(ChatColor.RED + "Too few arguments."),

    SEND_HELLO(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Hey Welcome"),
    SEND_GOODBYE(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "See ya"),

    NOT_A_PLAYER(ChatColor.RED + "You must have to be a player to use this command."),
    PLAYER_NOT_OP(ChatColor.RED + "You don't have permission to use this command.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
