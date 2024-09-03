package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TpToMeCommand {

    public static void execute (Player adminPlayer, Player targetPlayer, AdminTools adminTools) {
        targetPlayer.sendMessage("You will be teleported to " + adminPlayer.getName() + " in 5 seconds.");
        new BukkitRunnable() {
            private int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    targetPlayer.sendMessage("Teleporting in " + countdown + " seconds.");
                    countdown--;
                } else {
                    targetPlayer.teleport(adminPlayer);
                    cancel();
                }
            }
        }.runTaskTimer(adminTools, 0, 20);
    }
}
