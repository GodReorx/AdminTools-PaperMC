package me.GodReorx.adminTools.converters;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class InventoryConverter {

    public static String inventoryToString(ItemStack[] items) {
        try {
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            BukkitObjectOutputStream data = new BukkitObjectOutputStream(str);
            data.writeInt(items.length);
            for (ItemStack item : items) {
                data.writeObject(item);
            }
            data.close();
            return Base64.getEncoder().encodeToString(str.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ItemStack[] stringToInventory(String inventoryData) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(inventoryData));
            BukkitObjectInputStream data = new BukkitObjectInputStream(stream);
            int size = data.readInt();
            ItemStack[] items = new ItemStack[size];
            for (int i = 0; i < size; i++) {
                items[i] = (ItemStack) data.readObject();
            }
            data.close();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
