package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModeCommand {

    public static void execute (Player adminPlayer,Player targetPlayer, String gameMode){
        GameMode gameModeOpt = choiceGameMode(gameMode);
        if(gameModeOpt != null) {
            targetPlayer.setGameMode(gameModeOpt);
            adminPlayer.sendMessage("Player " + targetPlayer.getName() + " has switched to " + gameModeOpt + " mode.");
        } else {
            adminPlayer.sendMessage("ERROR: " + gameMode + " doesn't exist.");
        }
    }

    public static void execute (Player adminPlayer, String gameMode, AdminTools adminTools){
        GameMode gameModeOpt = choiceGameMode(gameMode);
        if(gameModeOpt != null) {
            changeGameModeAll(gameModeOpt, adminTools);
            adminPlayer.sendMessage("All players have been switched to " + gameModeOpt + " mode.");
        } else {
            adminPlayer.sendMessage("ERROR: " + gameMode + " doesn't exist.");
        }
    }

    private static GameMode choiceGameMode(String gameModeStr){
        if(gameModeStr.equalsIgnoreCase("creative")){
            return GameMode.CREATIVE;
        } else if (gameModeStr.equalsIgnoreCase("adventure")) {
            return GameMode.ADVENTURE;
        } else if (gameModeStr.equalsIgnoreCase("spectator")){
            return GameMode.SPECTATOR;
        } else if (gameModeStr.equalsIgnoreCase("survival")) {
            return GameMode.SURVIVAL;
        } else {
            return null;
        }
    }

    private static void changeGameModeAll(GameMode gameModeOpt, AdminTools adminTools){
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        new BukkitRunnable(){
            Random random = new Random();
            @Override
            public void run() {
                if(playerList.isEmpty()){
                    cancel();
                    return;
                }
                int playerIndex = random.nextInt(playerList.size());
                Player targetPlayer = playerList.remove(playerIndex);
                if(!targetPlayer.isOp()) {
                    targetPlayer.setGameMode(gameModeOpt);
                }
            }
        }.runTaskTimer(adminTools,0,10);
    }
}
