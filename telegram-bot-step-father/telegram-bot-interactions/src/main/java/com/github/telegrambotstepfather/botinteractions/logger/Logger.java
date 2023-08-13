package com.github.telegrambotstepfather.botinteractions.logger;

public interface Logger {
    void info(String message);
    void error(String message, Exception exception);
}
