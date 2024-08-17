package me.GodReorx.adminTools.errorControl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class checkSenderArgs {
    public static boolean senderArgsCheck(CommandSender sender, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("No se puede ejecutar desde consola.");
            return false;
        }
        Player adminPlayer = (Player) sender;
        if(args.length != 1){
            return false;
        }
        if(!adminPlayer.isOp()){
            adminPlayer.sendMessage("No tienes permisos para ejecutar el comando");
            return false;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null){
            adminPlayer.sendMessage("No existe ningun jugador con ese nombre");
            return false;
        }
        return true;
    }
}
