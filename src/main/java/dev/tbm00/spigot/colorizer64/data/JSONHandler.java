package dev.tbm00.spigot.colorizer64.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.bukkit.plugin.java.JavaPlugin;

public class JSONHandler {
    private final JavaPlugin javaPlugin;
    private final Object fileLock = new Object();
    private File jsonFile;
    private Gson gson;

    public JSONHandler(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.gson = new Gson();
        initializeDatabase();
    }

    private void initializeDatabase() {
        synchronized (fileLock) {
            jsonFile = new File(javaPlugin.getDataFolder(), "colors.json");
            if (!jsonFile.exists()) {
                try {
                    jsonFile.getParentFile().mkdirs();
                    jsonFile.createNewFile();
                    saveEntries(new HashMap<>());
                } catch (IOException e) {
                    javaPlugin.getLogger().severe("Exception when creating new JSON file!");
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, String> loadEntries() {
        synchronized (fileLock) {
            try (FileReader reader = new FileReader(jsonFile)) {
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                Map<String, String> entries = gson.fromJson(reader, type);
                if (entries == null) {
                    entries = new HashMap<>();
                }
                return entries;
            } catch (IOException e) {
                javaPlugin.getLogger().severe("Exception when loading JSON file!");
                e.printStackTrace();
                return new HashMap<>();
            }
        }
    }
    
    public void saveEntries(Map<String, String> entries) {
        synchronized (fileLock) {
            try (FileWriter writer = new FileWriter(jsonFile)) {
                gson.toJson(entries, writer);
            } catch (IOException e) {
                javaPlugin.getLogger().severe("Exception when saving JSON file!");
                e.printStackTrace();
            }
        }
    }
}

