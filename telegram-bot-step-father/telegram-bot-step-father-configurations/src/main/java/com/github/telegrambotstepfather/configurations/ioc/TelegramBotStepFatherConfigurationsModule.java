package com.github.telegrambotstepfather.configurations.ioc;

import com.github.telegrambotstepfather.configurations.models.TelegramBotConfigurationSettings;
import com.github.telegrambotstepfather.configurations.ConfigurationManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class TelegramBotStepFatherConfigurationsModule extends AbstractModule
{
    @Provides
    public TelegramBotConfigurationSettings provideConfig() 
    { 
        return ConfigurationManager.GetTelegramBotConfiguration();
    }
}
