package com.github.telegrambotstepfather.configurations;

public enum ConfigurationFile 
{
    TELEGRAM_BOTS_CONFIGURATION_FILE("config.properties"),
    DEFAULT_LOGGING_PROPERTIES_FILE("default_logging.properties"),
    CUSTOM_LOGGING_PROPERTIES_FILE("logging.properties");

    public final String relativeFilePath;

    private ConfigurationFile(String relativeFilePath)
    {
        this.relativeFilePath = relativeFilePath;
    }
}
