package com.github.telegrambotstepfather.botinteractions.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleJsonFile<T> implements JsonFile<T> {

    private final ObjectMapper objectMapper;
    private final String filePath;

    public SimpleJsonFile(String filePath) {
        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
    }

    @Override
    public void save(T data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            Files.writeString(Path.of(filePath), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T read(TypeReference<T> dataType) {
        try {
            String json = Files.readString(Path.of(filePath));
            if(json.isEmpty())
            {
                return null;
            }
            return objectMapper.readValue(json, dataType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

