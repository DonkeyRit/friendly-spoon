package com.github.telegrambotstepfather.botinteractions.persistance;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileCache implements Cache {
    private final String filename;
    private Set<String> cache;

    public FileCache(String filename) {
        this.filename = filename;
        this.cache = loadCacheFromFile();
    }

    public void saveMessage(String message) {
        cache.add(message);
        saveCacheToFile();
    }

    public boolean containsMessage(String message) {
        return cache.contains(message);
    }

    private Set<String> loadCacheFromFile() {
        Set<String> loadedCache = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedCache.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedCache;
    }

    private void saveCacheToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String message : cache) {
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


