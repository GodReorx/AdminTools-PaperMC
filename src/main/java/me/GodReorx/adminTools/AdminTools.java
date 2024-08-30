package me.GodReorx.adminTools;

import me.GodReorx.adminTools.commands.*;
import org.bukkit.plugin.java.JavaPlugin;


public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        getCommand("atpunish").setExecutor(new PunishmentCommand());
        getCommand("attpalltome").setExecutor(new TpAllToMeCommand(this));
        getCommand("attptome").setExecutor(new TpToMeCommand(this));
        getCommand("attpto").setExecutor(new TpToCommand());
        getCommand("atgamemode").setExecutor(new GameModeCommand(this));
        getCommand("atinventoryclean").setExecutor(new InventCleanCommand(this));
        getCommand("atvanish").setExecutor(new VanishCommand(this));
        getCommand("atsaveplayer").setExecutor(new SavePlayerCommand(this));
        getCommand("atloadplayer").setExecutor(new LoadPlayerCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
