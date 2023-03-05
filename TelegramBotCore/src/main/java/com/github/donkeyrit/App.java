package com.github.donkeyrit;

import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.ioc.TelegramApiBaseModules;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;

import com.google.inject.Injector;
import com.google.inject.Guice;

public class App 
{
  public static void main(String[] args) throws Exception 
  {
    Injector injector = Guice.createInjector(new TelegramApiBaseModules());
    TelegramBotFather botFather = injector.getInstance(TelegramBotFather.class);
    User bot = botFather.init();
    System.out.println("Bot - " + bot);

    botFather.registerUpdateEventListener(new UpdateEventListener());
  }
}
