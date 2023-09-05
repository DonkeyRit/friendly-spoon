package com.github.telegrambotstepfather.botinteractions.persistance;

import java.util.Optional;

public interface Cache {
    String saveMessage(String message, String hash);
    Optional<String> containsMessage(String message);
}
