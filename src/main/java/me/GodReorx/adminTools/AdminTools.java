package me.GodReorx.adminTools;

import me.GodReorx.adminTools.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        CommandManager commandManager = new CommandManager(this);

        getCommand("atpunish").setExecutor(commandManager);
        getCommand("attpalltome").setExecutor(commandManager);
        getCommand("attptome").setExecutor(commandManager);
        getCommand("attpto").setExecutor(commandManager);
        getCommand("atgamemode").setExecutor(commandManager);
        getCommand("atinventoryclean").setExecutor(commandManager);
        getCommand("atvanish").setExecutor(commandManager);
        getCommand("atsaveplayer").setExecutor(commandManager);
        getCommand("atloadplayer").setExecutor(commandManager);
        getCommand("atadminmode").setExecutor(commandManager);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
