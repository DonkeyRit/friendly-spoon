package com.github.donkeyrit;

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
  public static void main(String[] args) throws Exception 
  {
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
