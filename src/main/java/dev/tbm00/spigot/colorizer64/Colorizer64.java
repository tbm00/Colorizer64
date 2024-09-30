package dev.tbm00.spigot.colorizer64;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import dev.tbm00.spigot.colorizer64.command.ColorCommand;
import dev.tbm00.spigot.colorizer64.data.JSONHandler;
import dev.tbm00.spigot.colorizer64.listener.PlayerChat;

public class Colorizer64 extends JavaPlugin {
    private JSONHandler jsonHandler;
    private EntryManager entryManager;

    @Override
    public void onEnable() {
        // Startup Message
        final PluginDescriptionFile pdf = getDescription();
		log(
            ChatColor.DARK_PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-",
            pdf.getName() + " v" + pdf.getVersion() + " created by tbm00",
            ChatColor.DARK_PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
		);

        // Load Config
        saveDefaultConfig();
        boolean enabled = getConfig().getBoolean("enabled", true);
        if (!enabled) {
            getLogger().severe("Disabled in config...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Connect to JSON
        try {
            jsonHandler = new JSONHandler(this);
        } catch (Exception e) {
            getLogger().severe("Failed to connect to JSON. Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        // Connect LogManager
        entryManager = new EntryManager(this, jsonHandler);

        // Register Listener
        getServer().getPluginManager().registerEvents(new PlayerChat(entryManager), this);

        // Register Commands
        getCommand("chatcolor").setExecutor(new ColorCommand(entryManager));
    }

    @Override
    public void onDisable() {
        entryManager.close();
    }

    public JSONHandler getDatabase() {
        return jsonHandler;
    }

    public EntryManager getEntryManager() {
        return entryManager;
    }

    private void log(String... strings) {
		for (String s : strings)
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + s);
	}
}