package com.github.telegrambotstepfather.botinteractions.json.deserializers;

public interface ManualJsonDeserializer<T> {
    T deserialize(String json);
}
