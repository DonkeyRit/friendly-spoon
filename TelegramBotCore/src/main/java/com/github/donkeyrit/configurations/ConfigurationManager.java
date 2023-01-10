package com.github.donkeyrit.configurations;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;

public final class ConfigurationManager 
{
    public static TelegramBotConfigurationSettings GetTelegramBotConfiguration()
    {
        try {
            String configFilePath = ConfigurationFile.TELEGRAM_BOTS_CONFIGURATION_FILE.relativeFilePath;
            Properties prop = GetProperties(configFilePath);
            return new TelegramBotConfigurationSettings(prop.getProperty("BOT_TOKEN"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Properties GetProperties(String configFilePath) throws IOException
    {
        FileInputStream propsInput = new FileInputStream(configFilePath);
        Properties prop = new Properties();
        prop.load(propsInput);
        return prop;   
    }
}
