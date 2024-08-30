package me.GodReorx.adminTools.jsonClass;


import me.GodReorx.adminTools.converters.InventoryConverter;
import me.GodReorx.adminTools.converters.LocationConverter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerCapsule {

    private HashMap<String, Object> playerInfo = new HashMap<>();

    public PlayerCapsule(Player player) {

        String playerLocation = LocationConverter.locationToString(player.getLocation());
        String playerInventory = InventoryConverter.inventoryToString(player.getInventory());

        playerInfo.put("UUID", player.getUniqueId().toString());
        playerInfo.put("Name", player.getName());
        playerInfo.put("Location", playerLocation);
        playerInfo.put("Inventory",playerInventory);
        playerInfo.put("XP", player.getExp());
    }

    public HashMap<String, Object> getPublisher() {
        return playerInfo;
    }

    public void setPublisher(HashMap<String, Object> playerInfo) {
        this.playerInfo = playerInfo;
    }
}
