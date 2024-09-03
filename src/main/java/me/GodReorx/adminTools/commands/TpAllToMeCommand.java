package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TpAllToMeCommand {

    private static BukkitTask teleportTask;

    public static void execute(Player adminPlayer, AdminTools adminTools){
        if(teleportTask == null){
            adminPlayer.sendMessage("Teleport initiated");
            startTeleport(adminPlayer, adminTools);
        } else {
            adminPlayer.sendMessage("The mass teleportation sequence is underway. Please stand by.");
        }
    }

    private static void startTeleport(Player adminPlayer, AdminTools adminTools) {
        teleportTask = new BukkitRunnable() {
            private List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            private Random random = new Random();

            @Override
            public void run() {
                if (players.isEmpty()) {
                    cancel();
                    teleportTask = null;
                    adminPlayer.sendMessage("Bam! All players are now with you.");
                    return;
                }
                int randomIndex = random.nextInt(players.size());
                Player targetPlayer = players.remove(randomIndex);
                if (!targetPlayer.isOp() && targetPlayer.isOnline()) {
                    targetPlayer.teleport(adminPlayer);
                }
            }
        }.runTaskTimer(adminTools, 0, 10);
    }
}
