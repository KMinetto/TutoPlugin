package fr.leskhalys.tutoplugin;

import fr.leskhalys.tutoplugin.commands.changeteam.ChangeTeamCommand;
import fr.leskhalys.tutoplugin.commands.fly.FlyCommand;
import fr.leskhalys.tutoplugin.commands.gamble.GambleCommand;
import fr.leskhalys.tutoplugin.commands.godboots.GodBootsCommand;
import fr.leskhalys.tutoplugin.commands.heal.HealCommand;
import fr.leskhalys.tutoplugin.commands.hello.GoodByeCommand;
import fr.leskhalys.tutoplugin.commands.hello.HelloCommand;
import fr.leskhalys.tutoplugin.commands.launch.LaunchCommand;
import fr.leskhalys.tutoplugin.commands.startool.StarToolCommand;
import fr.leskhalys.tutoplugin.listeners.gamble.GambleListener;
import fr.leskhalys.tutoplugin.listeners.godboots.GodBootsFallListener;
import fr.leskhalys.tutoplugin.listeners.godboots.GodBootsListeners;
import fr.leskhalys.tutoplugin.listeners.gui.ChangeTeamListener;
import fr.leskhalys.tutoplugin.listeners.join.PlayerJoinListener;
import fr.leskhalys.tutoplugin.listeners.startool.StarToolListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private final String PLUGIN_NAME = "TutoPlugin";
    private final PluginManager PLUGIN_MANAGER = this.getServer().getPluginManager();

    @Override
    public void onEnable() {
        System.out.println("Plugin " + PLUGIN_NAME + " enabled.");

        // Join Event
        PLUGIN_MANAGER.registerEvents(new PlayerJoinListener(), this);
        // God Boots Events
        PLUGIN_MANAGER.registerEvents(new GodBootsListeners(), this);
        PLUGIN_MANAGER.registerEvents(new GodBootsFallListener(), this);
        // Star Tool Events
        PLUGIN_MANAGER.registerEvents(new StarToolListener(), this);
        // GUI
        PLUGIN_MANAGER.registerEvents(new ChangeTeamListener(), this);
        PLUGIN_MANAGER.registerEvents(new GambleListener(), this);

        Objects.requireNonNull(getCommand("hello")).setExecutor(new HelloCommand(this, "hello.use"));
        Objects.requireNonNull(getCommand("goodbye")).setExecutor(new GoodByeCommand(this, "goodbye.use"));
        Objects.requireNonNull(getCommand("launch")).setExecutor(new LaunchCommand(this, ""));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this, "fly.use"));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand(this, "heal.use"));
        Objects.requireNonNull(
            getCommand("godboots")).setExecutor(new GodBootsCommand(this, "godboots.use")
        );
        Objects.requireNonNull(
            getCommand("startool")).setExecutor(new StarToolCommand(this, "startool.use")
        );
        Objects.requireNonNull(getCommand("changeteam")).setExecutor(new ChangeTeamCommand(this, "changeteam.use"));
        Objects.requireNonNull(getCommand("gamble")).setExecutor(new GambleCommand(this, "gamble.use"));
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin " + PLUGIN_NAME + " disabled.");
    }
}
