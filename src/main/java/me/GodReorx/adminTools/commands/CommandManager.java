package me.GodReorx.adminTools.commands;

import me.GodReorx.adminTools.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    private final AdminTools adminTools;

    public CommandManager(AdminTools adminTools) {
        this.adminTools = adminTools;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String commandName = command.getName();

        if(!(sender instanceof Player adminPlayer)){
            sender.sendMessage("The command is designed for in-game player use and is not accessible from the console interface.");
            return true;
        }

        if(!adminPlayer.isOp()){
            adminPlayer.sendMessage("Sorry, you can't use that command.");
            return true;
        }

        if (args.length == 0) {
            return noArgsCommand(commandName, adminPlayer);
        } else if (args.length == 1) {
            return oneArgsCommand(commandName,adminPlayer, args);
        } else if (args.length == 2) {
            return twoArgsCommand(commandName, adminPlayer, args);
        } else {
            return false;
        }

    }

    private boolean noArgsCommand(String commandName, Player adminPlayer) {
        return switch (commandName) {
            case "attpalltome" -> {
                TpAllToMeCommand.execute(adminPlayer, adminTools);
                yield true;
            }
            case "atvanish" -> {
                VanishCommand.execute(adminPlayer, adminTools);
                yield true;
            }
            case "atadminmode" -> {
                AdminModeCommand.execute(adminPlayer, adminTools);
                yield true;
            }
            default -> {
                adminPlayer.sendMessage("ERROR: The command you entered is invalid.");
                yield false;
            }
        };
    }

    private boolean oneArgsCommand(String commandName, Player adminPlayer, String[] args){
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null && !commandName.equalsIgnoreCase("atinventoryclean")){
            return false;
        }
        return switch (commandName) {
            case "atpunish" -> {
                PunishmentCommand.execute(adminPlayer, targetPlayer);
                yield true;
            }
            case "attptome" -> {
                TpToMeCommand.execute(adminPlayer, targetPlayer, adminTools);
                yield true;
            }
            case "attpto" -> {
                TpToCommand.execute(adminPlayer, targetPlayer);
                yield true;
            }
            case "atinventoryclean" -> {
                InventCleanCommand.execute(adminPlayer, args, adminTools);
                yield true;
            }
            case "atsaveplayer" -> {
                SavePlayerCommand.execute(adminPlayer, targetPlayer, adminTools);
                yield true;
            }
            case "atloadplayer" -> {
                LoadPlayerCommand.execute(adminPlayer, targetPlayer, adminTools);
                yield true;
            }
            default -> {
                adminPlayer.sendMessage("ERROR: The command you entered is invalid.");
                yield false;
            }
        };
    }

    private boolean twoArgsCommand(String commandName, Player adminPlayer, String[] args){

        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (commandName.equals("atgamemode")) {
            if(args[0].equalsIgnoreCase("all")){
                GameModeCommand.execute(adminPlayer, args[1], adminTools);
                return true;
            }
            if(targetPlayer != null){
                GameModeCommand.execute(adminPlayer, targetPlayer, args[1]);
                return true;
            } else {
                adminPlayer.sendMessage("Can't find that player.");
                return false;
            }
        } else {
            adminPlayer.sendMessage("ERROR: The command you entered is invalid.");
            return false;
        }
    }
}
