package fr.leskhalys.tutoplugin;

import fr.leskhalys.tutoplugin.commands.fly.FlyCommand;
import fr.leskhalys.tutoplugin.commands.godboots.GodBootsCommand;
import fr.leskhalys.tutoplugin.commands.heal.HealCommand;
import fr.leskhalys.tutoplugin.commands.hello.GoodByeCommand;
import fr.leskhalys.tutoplugin.commands.hello.HelloCommand;
import fr.leskhalys.tutoplugin.commands.launch.LaunchCommand;
import fr.leskhalys.tutoplugin.eventlisteners.godboots.GodBootsFallListener;
import fr.leskhalys.tutoplugin.eventlisteners.godboots.GodBootsListeners;
import fr.leskhalys.tutoplugin.eventlisteners.join.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private final String PLUGIN_NAME = "TutoPlugin";
    private final PluginManager PLUGIN_MANAGER = this.getServer().getPluginManager();

    @Override
    public void onEnable() {
        System.out.println("Plugin " + PLUGIN_NAME + " enabled.");

        PLUGIN_MANAGER.registerEvents(new PlayerJoinListener(), this);
        PLUGIN_MANAGER.registerEvents(new GodBootsListeners(), this);
        PLUGIN_MANAGER.registerEvents(new GodBootsFallListener(), this);

        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand(this, "hello.use"));
        Objects.requireNonNull(getCommand("goodbye")).setExecutor(new GoodByeCommand(this, "goodbye.use"));
        Objects.requireNonNull(getCommand("launch")).setExecutor(new LaunchCommand(this, ""));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this, "fly.use"));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand(this, "heal.use"));
        Objects.requireNonNull(
            getCommand("godboots")).setExecutor(new GodBootsCommand(this, "godboots.use")
        );
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin " + PLUGIN_NAME + " disabled.");
    }
}
