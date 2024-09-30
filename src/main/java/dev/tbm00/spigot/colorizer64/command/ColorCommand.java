package dev.tbm00.spigot.colorizer64.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import dev.tbm00.spigot.colorizer64.EntryManager;

public class ColorCommand implements TabExecutor {
    private final EntryManager entryManager;
    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "Colorizer" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    public ColorCommand(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(prefix + ChatColor.GRAY + "Usage: /chatcolor <colorCode>/unset");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("unset")) {
            Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("Colorizer64"), () -> handleUnsetCommand(sender, args));
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("Colorizer64"), () -> handleSetCommand(sender, args));
        }

        return true;
    }

    private boolean handleUnsetCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("colorizer64.set")) {
            sender.sendMessage(prefix + ChatColor.RED + "No permission!");
            return false;
        }

        if (args.length == 1) {
            // remove color code String with entryManager.deleteEntry
            return true;
        } else {
            sender.sendMessage(prefix + ChatColor.GRAY + "Usage: /chatcolor <colorCode>/unset");
            return false;
        }
    }

    private boolean handleSetCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("colorizer64.set")) {
            sender.sendMessage(prefix + ChatColor.RED + "No permission!");
            return false;
        }

        if (args.length == 1) {
            String colorCode = args[1];
            boolean isValidColor = checkColorCode(colorCode);

            if (isValidColor) {
                // set color code String with entryManager.saveEntry
                return true;
            } else {
                sender.sendMessage(prefix + ChatColor.RED + "Invalid color code!");
                return false;
            }
        } else {
            sender.sendMessage(prefix + ChatColor.GRAY + "Usage: /chatcolor <colorCode>/unset");
            return false;
        }
    }

    boolean checkColorCode(String colorCode) {
        // `if color code is /in (&a, &f) || (&1, &9)`
            //return true
        //else 
            return false;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("colorizer64.set")) {
                String n = "<colorCode>/unset";
                if (n.startsWith(args[0])) {
                    list.add(n);
                }
            }
        }
        return list; 
    }
}