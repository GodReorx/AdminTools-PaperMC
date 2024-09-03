package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventCleanCommand {
    public static void execute (Player adminPlayer, String[] args, AdminTools adminTools){
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (args[0].equalsIgnoreCase("all")) {
            cleanAllInvent(adminTools);
            adminPlayer.sendMessage("All inventories have been cleared.");
        } else if (targetPlayer != null) {
            targetPlayer.getInventory().clear();
            adminPlayer.sendMessage("All items have been removed from " + targetPlayer.getName() + "'s inventory.");
        } else {
            adminPlayer.sendMessage("Player name not recognized. Please verify.");
        }
    }

    private static void cleanAllInvent(AdminTools adminTools) {
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        new BukkitRunnable() {
            Random random = new Random();

            @Override
            public void run() {
                if (playerList.isEmpty()) {
                    cancel();
                    return;
                }
                int playerIndex = random.nextInt(playerList.size());
                Player targetPlayer = playerList.remove(playerIndex);
                if (!targetPlayer.isOp()) {
                    targetPlayer.getInventory().clear();
                }
            }
        }.runTaskTimer(adminTools, 0, 10);
    }
}
