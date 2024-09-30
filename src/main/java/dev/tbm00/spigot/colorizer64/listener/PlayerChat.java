package dev.tbm00.spigot.colorizer64.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.tbm00.spigot.colorizer64.EntryManager;

public class PlayerChat implements Listener {
    private final EntryManager entryManager;

    public PlayerChat(EntryManager entryManager) {
        this.entryManager = entryManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {      
        // if player has permission colorizer64.set && entryManager.entryExists("username")==true
            // confirm colorCode is still valid
                // prepend colorCode String to the start of their message

        Player player = event.getPlayer();
        if (player.hasPermission("colorizer64.set") && entryManager.entryExists(player.getName())) {
            String colorCode = entryManager.getColor(player.getName());
            if (colorCode != null) {
                String coloredMessage = ChatColor.translateAlternateColorCodes('&', colorCode) + event.getMessage();
                event.setMessage(coloredMessage);
            }
        }
    }
}