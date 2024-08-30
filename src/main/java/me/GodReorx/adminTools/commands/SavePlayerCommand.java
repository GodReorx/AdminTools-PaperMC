package me.GodReorx.adminTools.commands;

import com.google.gson.Gson;
import me.GodReorx.adminTools.AdminTools;
import me.GodReorx.adminTools.jsonClass.PlayerCapsule;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SavePlayerCommand implements CommandExecutor {

    private AdminTools mainClass;

    public SavePlayerCommand(AdminTools main){
        this.mainClass = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)){
            sender.sendMessage("Solo se puede ejecutar desde consola o siendo OP");
            return true;
        }
        if(sender instanceof Player adminPlayer && !adminPlayer.isOp()){
            sender.sendMessage("No tienes permisos para ejecutarlo");
            return true;
        }
        if(args.length != 1){
            return false;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null){
            sender.sendMessage("Jugador no encontrado, comprueba el nombre.");
            return false;
        }
        PlayerCapsule playerCapsule = new PlayerCapsule(targetPlayer);
        String playerJson = new Gson().toJson(playerCapsule);
        Path path = mainClass.getDataPath().resolve(targetPlayer.getUniqueId().toString() + ".json").toAbsolutePath();
        try {
            Files.writeString(path,playerJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }return true;
    }
}
