package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static me.GodReorx.adminTools.errorControl.CheckSenderArgs.senderArgsCheck;

public class TpToMeCommand implements CommandExecutor {

    private AdminTools mainClass;

    public TpToMeCommand(AdminTools main){
        this.mainClass = main;
    }
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if(senderArgsCheck(sender, args)) {
            Player adminPlayer = (Player) sender;
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            targetPlayer.sendMessage("Vas a ser teletransportado a " + adminPlayer.getName() + " en 5 segundos.");
            new BukkitRunnable() {
                private int countdown = 5;

                @Override
                public void run() {
                    if (countdown > 0) {
                        targetPlayer.sendMessage("Teletransportado en " + countdown + " segundos.");
                        countdown--;
                    } else {
                        targetPlayer.teleport(adminPlayer);
                        cancel();
                    }
                }
            }.runTaskTimer(mainClass, 0, 20);
            return true;
        }
        return false;
    }
}
