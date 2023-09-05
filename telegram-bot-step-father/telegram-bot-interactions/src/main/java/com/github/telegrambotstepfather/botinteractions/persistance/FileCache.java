package com.github.telegrambotstepfather.botinteractions.persistance;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FileCache implements Cache {

    private final Path filePath;
    private Set<String> cache;

    public FileCache(String filename) {
        this.filePath = Paths.get(filename);
        this.cache = new HashSet<>();
    }

    public String saveMessage(String message, String hash) {

        cache.add(hash);
        saveCacheToFile(message, hash);

        return hash;
    }

    public Optional<String> containsMessage(String message) {
        String hash = computeHash(message);

        if(cache.contains(hash)){
            return Optional.empty();
        }

        return Optional.of(hash);
    }

    //#region Compute Hash

    private String computeHash(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(message.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    //#endregion

    public void loadCacheFromFile() {
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            cache.clear();
            for (String line : lines) {
                String[] parts = line.split("=>", 2);
                if (parts.length == 2) {
                    cache.add(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCacheToFile(String message, String hash) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(this.filePath, StandardOpenOption.APPEND);
            writer.write(hash + "=>" + message);
            writer.newLine();  // Move to the next line for future writes
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}