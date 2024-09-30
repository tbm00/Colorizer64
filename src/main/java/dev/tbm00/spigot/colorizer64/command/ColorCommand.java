package dev.tbm00.spigot.colorizer64.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import dev.tbm00.spigot.colorizer64.EntryManager;

public class ColorCommand implements TabExecutor {
    private final EntryManager entryManager;
    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "Colorizer" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    public ColorCommand(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + ChatColor.RED + "This command can only be used by players.");
            return false;
        } Player player = (Player) sender;
        
        if (args.length == 0) {
            sender.sendMessage(prefix + ChatColor.GRAY + "Usage: /chatcolor <colorCode>/unset");
            return false;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("unset")) {
            handleUnsetCommand(player);
        } else {
            handleSetCommand(player, args);
        }

        return true;
    }

    // remove color code String from player
    private boolean handleUnsetCommand(Player player) {
        entryManager.deleteEntry(player.getName());
        player.sendMessage(prefix + "Your chat color has been removed!");
        return true;
    }

    // set color code String for player
    private boolean handleSetCommand(Player player, String[] args) {
        if (!player.hasPermission("colorizer64.set")) {
            player.sendMessage(prefix + ChatColor.RED + "No permission!");
            return false;
        }

        if (args.length == 1) {
            String colorCode = args[0];
            boolean isValidColor = checkColorCode(colorCode);

            if (isValidColor) {
                // set color code String
                entryManager.saveEntry(player.getName(), colorCode);
                player.sendMessage(prefix + "Your chat color has been set to " +
                        ChatColor.translateAlternateColorCodes('&', colorCode) + colorCode + ChatColor.GRAY + "!");
                return true;
            } else {
                player.sendMessage(prefix + ChatColor.RED + "Invalid color code!");
                return false;
            }
        } else {
            player.sendMessage(prefix + ChatColor.GRAY + "Usage: /chatcolor <colorCode>/unset");
            return false;
        }
    }

    boolean checkColorCode(String colorCode) {
        Set<String> allowedColors = new HashSet<>(Arrays.asList(
                "&1", "&2", "&3", "&4", "&5", "&6", "&7",
                "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f" 
            ));
        return allowedColors.contains(colorCode.toLowerCase());
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && sender.hasPermission("colorizer64.set")) {
            List<String> suggestions = new ArrayList<>(Arrays.asList(
                    "unset", "&1", "&2", "&3", "&4", "&5", "&6",
                    "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f"
                ));
            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], suggestions, completions);
            Collections.sort(completions);
            return completions;
        }
        return Collections.emptyList();
    }
}