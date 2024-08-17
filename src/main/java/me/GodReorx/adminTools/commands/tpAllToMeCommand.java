package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class tpAllToMeCommand implements CommandExecutor{

    private AdminTools mainClass;
    private BukkitTask teleportTask;

    public tpAllToMeCommand (AdminTools main){
        this.mainClass = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("No se puede ejecutar desde consola");
            return false;
        }
        Player adminPlayer = (Player) sender;
        if(!adminPlayer.isOp()){
            adminPlayer.sendMessage("No tienes permisos para ejecutar este comando");
            return false;
        }
        if(teleportTask == null){
            adminPlayer.sendMessage("Teleport iniciado");
            startTeleport(adminPlayer);
        } else {
            adminPlayer.sendMessage("Ya ha sido iniciado, espera a que acabe");
        }
        return true;
    }

    private void startTeleport(Player adminPlayer) {
        teleportTask = new BukkitRunnable() {
            private List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            private Random random = new Random();

            @Override
            public void run() {
                if (players.isEmpty()) {
                    cancel();
                    teleportTask = null;
                    adminPlayer.sendMessage("Todos los jugadores teleportados a tu posición.");
                    return;
                }
                int randomIndex = random.nextInt(players.size());
                Player targetPlayer = players.remove(randomIndex);
                if (!targetPlayer.isOp() && targetPlayer.isOnline()) {
                    targetPlayer.teleport(adminPlayer);
                }
            }
        }.runTaskTimer(mainClass, 0, 10);
    }
}
