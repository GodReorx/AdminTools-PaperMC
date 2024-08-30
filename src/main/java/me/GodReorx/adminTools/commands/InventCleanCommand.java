package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventCleanCommand implements CommandExecutor {

    private AdminTools mainClass;

    public InventCleanCommand(AdminTools adminTools) {
        this.mainClass = adminTools;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if((sender instanceof Player) || (sender instanceof ConsoleCommandSender) || args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if (args[0].equalsIgnoreCase("all")) {
                cleanAllInvent();
                sender.sendMessage("Todos los inventarios vaciados.");
                return true;
            } else if (targetPlayer != null) {
                targetPlayer.getInventory().clear();
                sender.sendMessage("Inventario del jugador " + targetPlayer.getName() + " ha sido vaciado.");
                return true;
            }
        }
        return false;
    }

    private void cleanAllInvent() {
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
        }.runTaskTimer(mainClass, 0, 10);
    }
}
