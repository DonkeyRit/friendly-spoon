package com.github.telegrambotstepfather.botinteractions.json;

import com.fasterxml.jackson.core.type.TypeReference;

public interface JsonFile<T> {
    void save(T data);
    T read(TypeReference<T> dataType);
}
