package com.github.telegrambotstepfather.botinteractions.json;

import com.github.telegrambotstepfather.botinteractions.json.deserializers.ManualJsonDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Optional;

public interface JsonFile<T> {
    void save(T data);
    T read(TypeReference<T> dataType, Optional<ManualJsonDeserializer<T>> deserializer);
}
