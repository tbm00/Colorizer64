package dev.tbm00.spigot.colorizer64.data;

import org.bukkit.plugin.java.JavaPlugin;

public class JSONHandler {
    private JavaPlugin javaPlugin;

    public JSONHandler(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        setupConnectionPool();
        initializeDatabase();
    }

    private void setupConnectionPool() {

    }

    public void closeConnection() {
    }

    private void initializeDatabase() {
    
    }

    //private unordered_map getEntries() {
    //
    //}
}

