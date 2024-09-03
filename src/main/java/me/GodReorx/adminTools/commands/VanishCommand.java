package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class VanishCommand {

    private static Map<Player, String> vanishSwitch = new HashMap<>();

    public static void execute (Player adminPlayer, AdminTools adminTools){
        String newState;
        if(vanishSwitch.containsKey(adminPlayer) && "invisible".equalsIgnoreCase(vanishSwitch.get(adminPlayer))){
            newState = "visible";
        } else {
            newState = "invisible";
        }
        for (Player targetPlayer : Bukkit.getOnlinePlayers()){
            if(newState.equalsIgnoreCase("invisible") && !targetPlayer.isOp()){
                targetPlayer.hidePlayer(adminTools,adminPlayer);
            } else {
                targetPlayer.showPlayer(adminTools,adminPlayer);
            }
        }
        vanishSwitch.put(adminPlayer,newState);
        if (newState.equalsIgnoreCase("invisible")){
            adminPlayer.sendMessage("You are hidden from all players, except administrators.");
        } else {
            adminPlayer.sendMessage("You are no longer hidden from all players.");
        }
    }
}
