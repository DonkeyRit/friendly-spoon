package com.github.telegrambotstepfather.ioc;

import com.github.telegrambotstepfather.ioc.Providers.ObjectMapperProvider;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBotFather;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.bot.TelegramBotFatherImpl;
import com.github.telegrambotstepfather.bot.TelegramBotImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;

public class TelegramBotStepFatherModule extends AbstractModule
{
    @Override
    protected void configure() 
    {
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);
        bind(TelegramBot.class).to(TelegramBotImpl.class);
        bind(TelegramBotFather.class).to(TelegramBotFatherImpl.class);
    }
}
