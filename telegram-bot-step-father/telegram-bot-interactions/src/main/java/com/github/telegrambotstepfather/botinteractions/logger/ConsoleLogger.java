package com.github.telegrambotstepfather.botinteractions.logger;

public class ConsoleLogger implements Logger {

    public void info(String message) {
        System.out.println(message);
    }

    public void error(String message, Exception exception) {
        System.out.println(message);
        System.out.println(exception.getStackTrace());
    }

}
