package dev.tbm00.spigot.colorizer64;

import org.bukkit.plugin.java.JavaPlugin;

import dev.tbm00.spigot.colorizer64.data.JSONHandler;

public class EntryManager {
    private final JavaPlugin javaPlugin;
    private final JSONHandler db;
    // private unordered_map entries;

    public EntryManager(JavaPlugin javaPlugin, JSONHandler db) {
        this.javaPlugin = javaPlugin;
        this.db = db;
        //this.unordered_map = db.getEntries;
    }

    // returns if the player/color entry for username exists
    public boolean entryExists(String username) {
        return false;
    }

    // returns color from player's entry from map
    // if not found, tries to get it from JSON
    // else returns null
    public String getColor(String username) {
        return null;
    }

    // creates player/color entry in json & map if DNE
    // updates player/color entry in json & map if it does exist
    public void saveEntry(String username, String color) {

    }

    // removes player/color entry from json & map
    public void deleteEntry(String username) {

    }
}