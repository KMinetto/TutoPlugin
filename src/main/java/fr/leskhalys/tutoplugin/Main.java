package fr.leskhalys.tutoplugin;

import fr.leskhalys.tutoplugin.commands.hello.GoodByeCommand;
import fr.leskhalys.tutoplugin.commands.hello.HelloCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private final String PLUGIN_NAME = "TutoPlugin";

    @Override
    public void onEnable() {
        System.out.println("Plugin " + PLUGIN_NAME + " enabled.");
        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand(this, "hello.use"));
        Objects.requireNonNull(getCommand("goodbye")).setExecutor(new GoodByeCommand(this, "goodbye.use"));
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin " + PLUGIN_NAME + " disabled.");
    }
}
