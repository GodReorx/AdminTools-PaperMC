package me.GodReorx.adminTools.commands;

import com.google.gson.Gson;
import me.GodReorx.adminTools.AdminTools;
import me.GodReorx.adminTools.jsonClass.PlayerInfo;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SavePlayerCommand {

    public static void execute(Player adminPlayer, Player targetPlayer, AdminTools adminTools){
        if(targetPlayer != null) {
            PlayerInfo playerInfo = new PlayerInfo(targetPlayer);
            String playerJson = new Gson().toJson(playerInfo);
            Path path = adminTools.getDataPath().resolve(targetPlayer.getUniqueId() + ".json").toAbsolutePath();
            try {
                Files.writeString(path, playerJson);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            adminPlayer.sendMessage("Player name not recognized. Please verify.");
        }
    }

}
