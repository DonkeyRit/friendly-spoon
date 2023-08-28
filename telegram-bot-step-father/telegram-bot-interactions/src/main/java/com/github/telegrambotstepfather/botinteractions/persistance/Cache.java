package com.github.telegrambotstepfather.botinteractions.persistance;

public interface Cache {
    void saveMessage(String message);
    boolean containsMessage(String message);
}
