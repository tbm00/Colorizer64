package dev.tbm00.spigot.colorizer64;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import dev.tbm00.spigot.colorizer64.data.JSONHandler;

public class EntryManager {
    private final JavaPlugin javaPlugin;
    private final JSONHandler db;
    private Map<String, String> entries;

    public EntryManager(JavaPlugin javaPlugin, JSONHandler db) {
        this.javaPlugin = javaPlugin;
        this.db = db;
        this.entries = new ConcurrentHashMap<>(db.loadEntries());
    }

    // returns if the player/color entry for username exists
    public boolean entryExists(String username) {
        return entries.containsKey(username);
    }

    // returns color from player's entry from map
    // if not found, tries to get it from JSON
    // else returns null
    public String getColor(String username) {
        return entries.get(username);
    }

    // creates player/color entry in json & map if DNE
    // updates player/color entry in json & map if it does exist
    public void saveEntry(String username, String color) {
        entries.put(username, color);
        saveEntriesAsync();
    }

    // removes player/color entry from json & map
    public void deleteEntry(String username) {
        entries.remove(username);
        saveEntriesAsync();
    }

    private void saveEntriesAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> db.saveEntries(entries));
    }

    // on plugin disable
    public void close() {
        db.saveEntries(entries);
    }
}