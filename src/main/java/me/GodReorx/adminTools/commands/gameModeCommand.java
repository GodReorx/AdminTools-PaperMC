package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class gameModeCommand implements CommandExecutor {

    private AdminTools mainClass;

    public gameModeCommand(AdminTools adminTools) {
        this.mainClass = adminTools;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameMode gameModeOpt = choiceGameMode(args[1]);
        if(args.length != 2 || gameModeOpt == null){
            return false;
        }

        if(sender instanceof ConsoleCommandSender){
            checkArgs(sender, args, gameModeOpt);
            return true;
        }

        if(sender instanceof Player adminPlayer){
            if(!adminPlayer.isOp()){
                adminPlayer.sendMessage("No tienes permisos para ejecutar este comando.");
                return true;
            }
            checkArgs(sender, args, gameModeOpt);
            return true;
        }

        return false;
    }


    private GameMode choiceGameMode (String gameModeStr){
        if(gameModeStr.equalsIgnoreCase("creative")){
            return GameMode.CREATIVE;
        } else if (gameModeStr.equalsIgnoreCase("adventure")) {
            return GameMode.ADVENTURE;
        } else if (gameModeStr.equalsIgnoreCase("spectator")){
            return GameMode.SPECTATOR;
        } else if (gameModeStr.equalsIgnoreCase("survival")) {
            return GameMode.SURVIVAL;
        } else {
            return null;
        }
    }

    private void checkArgs (CommandSender sender, String[] args, GameMode gameModeOpt){
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(args[0].equalsIgnoreCase("all")){
            changeGameModeAll(gameModeOpt);
            sender.sendMessage("Todos los jugadores cambiados a " + gameModeOpt);
        }else if (targetPlayer != null){
            targetPlayer.setGameMode(gameModeOpt);
            sender.sendMessage("El jugador " + targetPlayer.getName() + " se ha cambiado a " + gameModeOpt);
        } else {
            sender.sendMessage("No existe ningun jugador con ese nombre.");
        }
    }

    private void changeGameModeAll(GameMode gameModeOpt){
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        new BukkitRunnable(){
            Random random = new Random();
            @Override
            public void run() {
                if(playerList.isEmpty()){
                    cancel();
                    return;
                }
                int playerIndex = random.nextInt(playerList.size());
                Player targetPlayer = playerList.remove(playerIndex);
                if(!targetPlayer.isOp()) {
                    targetPlayer.setGameMode(gameModeOpt);
                }
            }
        }.runTaskTimer(mainClass,0,10);
    }
}
