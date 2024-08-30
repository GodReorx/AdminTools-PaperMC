package me.GodReorx.adminTools.jsonClass;

import me.GodReorx.adminTools.converters.InventoryConverter;
import me.GodReorx.adminTools.converters.LocationConverter;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerInfo {
    private double experiencie;
    private UUID uuid;
    private String inventory;
    private String name;
    private String location;

    public PlayerInfo(Player player) {
        this.experiencie = player.getExp();
        this.uuid = player.getUniqueId();
        this.inventory = InventoryConverter.inventoryToString(player.getInventory().getContents());
        this.name = player.getName();
        this.location = LocationConverter.locationToString(player.getLocation());
    }

    public double getExperiencie() {
        return experiencie;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
