package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class VanishCommand implements CommandExecutor {

    private final AdminTools mainClass;

    private Map<Player, String> vanishSwitch = new HashMap<>();

    public VanishCommand(AdminTools adminTools){
        this.mainClass = adminTools;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String newState;
        if (!(sender instanceof Player)){
            sender.sendMessage("No se puede usar desde la consola");
            return true;
        }
        Player adminPlayer = (Player) sender;
        if (!adminPlayer.isOp()){
            sender.sendMessage("No tienes permisos para ejecutar este comando");
            return true;
        }
        if(vanishSwitch.containsKey(adminPlayer) && "invisible".equalsIgnoreCase(vanishSwitch.get(adminPlayer))){
            newState = "visible";
        } else {
            newState = "invisible";
        }
        for (Player targetPlayer : Bukkit.getOnlinePlayers()){
            if(newState.equalsIgnoreCase("invisible") && !targetPlayer.isOp()){
                targetPlayer.hidePlayer(mainClass,adminPlayer);
            } else {
                targetPlayer.showPlayer(mainClass,adminPlayer);
            }
        }
        vanishSwitch.put(adminPlayer,newState);
        if (newState.equalsIgnoreCase("invisible")){
            adminPlayer.sendMessage("Eres invisible para todos los jugadores, excepto admins.");
        } else {
            adminPlayer.sendMessage("Eres visible para todos los jugadores de nuevo.");
        }
        return true;
    }
}
