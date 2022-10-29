package fr.leskhalys.tutoplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private final String PLUGIN_NAME = "TutoPlugin";

    @Override
    public void onEnable() {
        System.out.println("Plugin " + PLUGIN_NAME + " enabled.");

    }

    @Override
    public void onDisable() {
        System.out.println("Plugin " + PLUGIN_NAME + " disabled.");
    }
}
