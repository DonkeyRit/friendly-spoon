package com.github.telegrambotstepfather.configurations;

import com.github.telegrambotstepfather.configurations.models.TelegramBotConfigurationSettings;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigurationManager 
{
    public static TelegramBotConfigurationSettings getTelegramBotConfiguration()
    {
        try {
            String configFilePath = ConfigurationFile.TELEGRAM_BOTS_CONFIGURATION_FILE.relativeFilePath;
            Properties prop = loadConfig(configFilePath);
            return new TelegramBotConfigurationSettings(prop.getProperty("BOT_TOKEN"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
}
