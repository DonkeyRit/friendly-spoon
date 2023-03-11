package com.github.telegrambotstepfather.configurations;

public enum ConfigurationFile 
{
    TELEGRAM_BOTS_CONFIGURATION_FILE("resources/config.properties");

    public final String relativeFilePath;

    private ConfigurationFile(String relativeFilePath)
    {
        this.relativeFilePath = relativeFilePath;
    }
}
