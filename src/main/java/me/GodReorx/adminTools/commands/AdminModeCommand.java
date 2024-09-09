package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AdminModeCommand {

    private static Map<Player, String> adminSwitch = new HashMap<>();

    public static void execute (Player adminPlayer, AdminTools adminTools){
        String newState;
        if(adminSwitch.containsKey(adminPlayer) && "activated".equalsIgnoreCase(adminSwitch.get(adminPlayer))){
            newState = "desactivated";
        } else {
            newState = "activated";
        }
        for (Player targetPlayer : Bukkit.getOnlinePlayers()){
            if(newState.equalsIgnoreCase("activated") && !targetPlayer.isOp()){
                targetPlayer.hidePlayer(adminTools,adminPlayer);
            } else {
                targetPlayer.showPlayer(adminTools,adminPlayer);
            }
        }
        if(newState.equalsIgnoreCase("activated")){
            SavePlayerCommand.execute(adminPlayer, adminPlayer, adminTools);
            adminPlayer.getInventory().clear();
            adminPlayer.setGameMode(GameMode.CREATIVE);
            adminPlayer.sendMessage("Admin mode activated!!");
        } else {
            LoadPlayerCommand.execute(adminPlayer, adminPlayer, adminTools);
            adminPlayer.setGameMode(GameMode.SURVIVAL);
            adminPlayer.sendMessage("Admin mode desactivated!!");
        }
        adminSwitch.put(adminPlayer,newState);
    }

}
