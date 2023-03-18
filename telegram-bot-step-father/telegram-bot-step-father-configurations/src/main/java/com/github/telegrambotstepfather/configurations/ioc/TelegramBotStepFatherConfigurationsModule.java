package com.github.telegrambotstepfather.configurations.ioc;

import com.github.telegrambotstepfather.configurations.models.TelegramBotConfigurationSettings;
import com.github.telegrambotstepfather.configurations.ConfigurationManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;

import java.util.logging.LogManager;
import java.util.Optional;
import java.io.IOException;
import java.io.InputStream;

public class TelegramBotStepFatherConfigurationsModule extends AbstractModule 
{
    @Provides
    public TelegramBotConfigurationSettings provideConfig() 
    {
        return ConfigurationManager.getTelegramBotConfiguration();
    }

    @Override
    protected void configure() 
    {
        Optional<InputStream> configurationFileStream = ConfigurationManager.getLoggingProperties();
        if (configurationFileStream.isPresent()) 
        {
            try 
            {
                LogManager.getLogManager().readConfiguration(configurationFileStream.get());
            } 
            catch (SecurityException | IOException e) 
            {
                throw new ProvisionException("Couldn't initialize logger with default settings.", e);
            }
        }
    }
}
