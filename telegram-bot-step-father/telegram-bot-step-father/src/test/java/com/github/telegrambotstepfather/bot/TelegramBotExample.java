package com.github.telegrambotstepfather.bot;

import com.github.telegrambotstepfather.ioc.Providers.ServiceProvider;
import com.github.telegrambotstepfather.models.response.User;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.google.inject.Injector;

import org.junit.Assert;
import org.junit.Test;


public class TelegramBotExample 
{
    @Test
    public void testLaunch() throws TelegramApiException, JacksonJsonParsingException
    {
        Injector injector = ServiceProvider.buildServiceProvider();
        TelegramBot telegramBot = injector.getInstance(TelegramBot.class);

        User bot = telegramBot.getMe();
        Assert.assertEquals(bot, null);
    }
}
