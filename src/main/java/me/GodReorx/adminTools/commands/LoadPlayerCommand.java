package me.GodReorx.adminTools.commands;

import com.google.gson.Gson;
import me.GodReorx.adminTools.AdminTools;
import me.GodReorx.adminTools.converters.InventoryConverter;
import me.GodReorx.adminTools.converters.LocationConverter;
import me.GodReorx.adminTools.jsonClass.PlayerInfo;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadPlayerCommand {
    public static void execute(Player adminPlauer, Player targetPlayer, AdminTools adminTools){
        if(targetPlayer != null) {
            Gson gson = new Gson();
            Path path = adminTools.getDataPath().resolve(targetPlayer.getUniqueId() + ".json").toAbsolutePath();
            if(Files.exists(path)) {
                try {
                    String jsonFile = Files.readString(path);
                    PlayerInfo playerInfo = gson.fromJson(jsonFile, PlayerInfo.class);
                    targetPlayer.setExp(targetPlayer.getExp());
                    targetPlayer.getInventory().setContents(InventoryConverter.stringToInventory(playerInfo.getInventory()));
                    targetPlayer.teleport(LocationConverter.stringToLocation(playerInfo.getLocation()));
                    Files.delete(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                adminPlauer.sendMessage("ERROR: Player data file not found. Try to save first.");
            }
        } else {
            adminPlauer.sendMessage("Player name not recognized. Please verify.");
        }
    }

}
