package util;

import model.Entity;
import model.Attribute;
import com.google.gson.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private static final String DATA_DIR = "data";
    private static final String ATTRIBUTES_FILE = "data/attributes.json";
    private static final String ENTITIES_FILE = "data/entities.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void initialize() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Error creating data directory: " + e.getMessage());
        }
    }

    public static void saveAttributes(List<Attribute> attributes) {
        try (Writer writer = new FileWriter(ATTRIBUTES_FILE)) {
            gson.toJson(attributes, writer);
        } catch (IOException e) {
            System.err.println("Error saving attributes: " + e.getMessage());
        }
    }

    public static List<Attribute> loadAttributes() {
        try {
            if (Files.exists(Paths.get(ATTRIBUTES_FILE))) {
                String json = Files.readString(Paths.get(ATTRIBUTES_FILE));
                return gson.fromJson(json, new TypeToken<List<Attribute>>(){}.getType());
            }
        } catch (IOException e) {
            System.err.println("Error loading attributes: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void saveEntities(List<Entity> entities) {
        try (Writer writer = new FileWriter(ENTITIES_FILE)) {
            gson.toJson(entities, writer);
        } catch (IOException e) {
            System.err.println("Error saving entities: " + e.getMessage());
        }
    }

    public static List<Entity> loadEntities() {
        try {
            if (Files.exists(Paths.get(ENTITIES_FILE))) {
                String json = Files.readString(Paths.get(ENTITIES_FILE));
                return gson.fromJson(json, new TypeToken<List<Entity>>(){}.getType());
            }
        } catch (IOException e) {
            System.err.println("Error loading entities: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
