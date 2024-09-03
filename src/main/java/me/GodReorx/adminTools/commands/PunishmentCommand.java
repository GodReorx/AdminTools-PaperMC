package me.GodReorx.adminTools.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PunishmentCommand{

    public static void execute(Player adminPlayer, Player targetPlayer){
        Location location = targetPlayer.getLocation();
        Bukkit.getWorld(location.getWorld().getName()).strikeLightningEffect(location);
        targetPlayer.setHealth(0);
        targetPlayer.sendMessage("God's wrath has been unleashed!");
        adminPlayer.sendMessage("Player " + targetPlayer.getName() + " has been punished.");
    }
}
