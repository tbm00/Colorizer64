package dev.tbm00.spigot.colorizer64.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import dev.tbm00.spigot.colorizer64.EntryManager;

public class PlayerChat implements Listener {
    private JavaPlugin javaPlugin;
    private final EntryManager entryManager;
    private final boolean enabled;

    public PlayerChat(JavaPlugin javaPlugin, EntryManager entryManager) {
        this.javaPlugin = javaPlugin;
        this.entryManager = entryManager;
        this.enabled = javaPlugin.getConfig().getBoolean("enabled", true);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // check if enabled
        if (!enabled) return;
        
        // if ( (player has permission colorizer64.set) && (entryManager.entryExists("username")==true) )
            // if chatmessage.startsWith(/) return
            // else
                // append color String to the start of their message
                // return

        return;
    }
}