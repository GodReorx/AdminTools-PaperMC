package me.GodReorx.adminTools.commands;

import org.bukkit.entity.Player;

public class TpToCommand {

    public static void execute (Player adminPlayer, Player targetPlayer){
        adminPlayer.teleport(targetPlayer);
        adminPlayer.sendMessage("Teleported to " + targetPlayer.getName());
    }
}
