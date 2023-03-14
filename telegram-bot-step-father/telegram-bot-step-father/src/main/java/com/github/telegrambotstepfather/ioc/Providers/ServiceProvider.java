package com.github.telegrambotstepfather.ioc.Providers;

import com.github.telegrambotstepfather.configurations.ioc.TelegramBotStepFatherConfigurationsModule;
import com.github.telegrambotstepfather.ioc.TelegramBotStepFatherModule;
import com.github.telegrambotstepfather.http.ioc.TelegramApiHttpModule;

import com.google.inject.Injector;
import com.google.inject.Guice;

public class ServiceProvider 
{
    public static Injector buildServiceProvider()
    {
        com.google.inject.Module[] modules = new com.google.inject.Module[]
        {
            new TelegramApiHttpModule(),
            new TelegramBotStepFatherConfigurationsModule(),
            new TelegramBotStepFatherModule()
        };
        return Guice.createInjector(modules);
    }
}
