package com.github.amazonnotifier;

import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.ioc.TelegramApiBaseModules;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;

import com.google.inject.Injector;
import com.google.inject.Guice;
import java.util.logging.Logger;

public class App 
{

    public static void main(String[] args)
    {
        String url = "https://www.amazon.com/gp/product/B0BBHD5D8Y/ref=ox_sc_saved_title_1?smid=ATVPDKIKX0DER&psc=1";


        
        Injector injector = Guice.createInjector(new TelegramApiBaseModules());
    
        Logger logger = injector.getInstance(Logger.class);
        logger.info("Start application...");
    
        TelegramBotFather botFather = injector.getInstance(TelegramBotFather.class);
        User bot = botFather.init();
        logger.info(() -> "Telegram Bot - " + bot.firstName());
    
        UpdateEventListener eventListener = new UpdateEventListener(logger, injector.getInstance(TelegramBot.class));
        botFather.getUpdatesEventSource().addListener(eventListener);
    }
}
