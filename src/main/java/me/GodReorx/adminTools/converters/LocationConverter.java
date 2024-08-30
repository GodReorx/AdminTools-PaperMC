package me.GodReorx.adminTools.converters;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationConverter {
    public static String locationToString(Location playerLocation){
        return playerLocation.getWorld().getName() + "," + String.valueOf(playerLocation.getX()) + "," + String.valueOf(playerLocation.getY()) + ","+ String.valueOf(playerLocation.getZ());
    }

    public static Location stringToLocation(String playerLocation){

        String[] values = playerLocation.split(",");
        World world = Bukkit.getWorld(values[0]);
        Double x = Double.parseDouble(values[1]);
        Double y = Double.parseDouble(values[2]);
        Double z = Double.parseDouble(values[3]);

        return new Location(world,x,y,z);
    }
}
