package com.github.telegrambotstepfather.configurations;

import com.github.telegrambotstepfather.configurations.models.TelegramBotConfigurationSettings;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

public final class ConfigurationManager 
{
    public static TelegramBotConfigurationSettings getTelegramBotConfiguration() 
    {
        try 
        {
            String configFilePath = ConfigurationFile.TELEGRAM_BOTS_CONFIGURATION_FILE.relativeFilePath;
            Properties prop = loadConfig(configFilePath);
            return new TelegramBotConfigurationSettings(prop.getProperty("BOT_TOKEN"));
        } 
        catch (FileNotFoundException e) 
        {
            //TODO: use
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return null;
    }

    public static Optional<InputStream> getLoggingProperties() 
    {
        List<String> paths = Arrays.asList(
            ConfigurationFile.CUSTOM_LOGGING_PROPERTIES_FILE.relativeFilePath,
            ConfigurationFile.DEFAULT_LOGGING_PROPERTIES_FILE.relativeFilePath);

        return paths.stream()
                .map(path -> tryLoadConfig(path))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    public static Properties loadConfig(String configFilePath) throws IOException 
    {
        Properties props = new Properties();
        InputStream inputStream = ConfigurationManager.class
                .getClassLoader()
                .getResourceAsStream(configFilePath);
        props.load(inputStream);

        return props;
    }

    public static Optional<InputStream> tryLoadConfig(String configFilePath)
    {
        return Optional.ofNullable(ConfigurationManager.class.getClassLoader().getResourceAsStream(configFilePath));
    }
}
