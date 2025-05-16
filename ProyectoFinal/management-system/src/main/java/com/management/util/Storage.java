package com.management.util;

import model.Entity;
import model.Attribute;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private static final String DATA_DIR = "data";
    private static final String ATTRIBUTES_FILE = "data/attributes.json";
    private static final String ENTITIES_FILE = "data/entities.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void initialize() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        
        // Create empty JSON files if they don't exist
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            if (!Files.exists(Paths.get(ATTRIBUTES_FILE))) {
                Files.writeString(Paths.get(ATTRIBUTES_FILE), "[]");
            }
            if (!Files.exists(Paths.get(ENTITIES_FILE))) {
                Files.writeString(Paths.get(ENTITIES_FILE), "[]");
            }
        } catch (IOException e) {
            System.err.println("Error initializing storage: " + e.getMessage());
        }
    }

    public static void saveAttributes(List<Attribute> attributes) {
        try {
            mapper.writeValue(new File(ATTRIBUTES_FILE), attributes);
        } catch (IOException e) {
            System.err.println("Error saving attributes: " + e.getMessage());
        }
    }

    public static List<Attribute> loadAttributes() {
        try {
            CollectionType type = mapper.getTypeFactory()
                .constructCollectionType(List.class, Attribute.class);
            return mapper.readValue(new File(ATTRIBUTES_FILE), type);
        } catch (IOException e) {
            System.err.println("Error loading attributes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveEntities(List<Entity> entities) {
        try {
            mapper.writeValue(new File(ENTITIES_FILE), entities);
        } catch (IOException e) {
            System.err.println("Error saving entities: " + e.getMessage());
        }
    }

    public static List<Entity> loadEntities() {
        try {
            CollectionType type = mapper.getTypeFactory()
                .constructCollectionType(List.class, Entity.class);
            return mapper.readValue(new File(ENTITIES_FILE), type);
        } catch (IOException e) {
            System.err.println("Error loading entities: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
