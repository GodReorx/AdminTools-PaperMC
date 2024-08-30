package me.GodReorx.adminTools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.GodReorx.adminTools.errorControl.CheckSenderArgs.senderArgsCheck;

public class PunishmentCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(senderArgsCheck(sender,args)) {
            Player adminPlayer = (Player) sender;
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            Location location = targetPlayer.getLocation();
            Bukkit.getWorld(location.getWorld().getName()).strikeLightningEffect(location);
            targetPlayer.setHealth(0);
            targetPlayer.sendMessage("Castigo divino ejecutado!!");
            adminPlayer.sendMessage("El jugador " + targetPlayer.getName() + " ha sido castigado.");
            return true;
        }
        return false;
    }
}
