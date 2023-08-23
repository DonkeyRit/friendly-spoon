package com.github.telegrambotstepfather.botinteractions.json;

import com.github.telegrambotstepfather.botinteractions.json.deserializers.ManualJsonDeserializer;
import com.github.telegrambotstepfather.botinteractions.json.deserializers.CookieDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.List;

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
    public T read(TypeReference<T> dataType, Optional<ManualJsonDeserializer<T>> deserializer) {
        try {
            String json = Files.readString(Path.of(filePath));
            if(json.isEmpty())
            {
                return null;
            }

            if(deserializer.isPresent())
            {
                return deserializer.get().deserialize(json);
            }

            return objectMapper.readValue(json, dataType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

