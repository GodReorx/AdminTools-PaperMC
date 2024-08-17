package me.GodReorx.adminTools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.GodReorx.adminTools.errorControl.checkSenderArgs.senderArgsCheck;

public class tpToCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(senderArgsCheck(sender,args)) {
            Player adminPlayer = (Player) sender;
            Player targetPlayer = Bukkit.getPlayer(args[0]);
            adminPlayer.teleport(targetPlayer);
            adminPlayer.sendMessage("Teleportado a " + targetPlayer.getName());
            return true;
        }
    return false;
    }
}
