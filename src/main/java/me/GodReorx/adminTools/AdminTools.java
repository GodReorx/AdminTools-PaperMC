package me.GodReorx.adminTools;

import me.GodReorx.adminTools.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("atpunish").setExecutor(new punishmentCommand());
        getCommand("attpalltome").setExecutor(new tpAllToMeCommand(this));
        getCommand("attptome").setExecutor(new tpToMeCommand(this));
        getCommand("attpto").setExecutor(new tpToCommand());
        getCommand("atgamemode").setExecutor(new gameModeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
