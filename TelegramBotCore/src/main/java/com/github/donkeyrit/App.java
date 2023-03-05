package com.github.donkeyrit;

import com.github.donkeyrit.ioc.DaggerTelegramApiComponent;
import com.github.donkeyrit.ioc.TelegramApiComponent;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;

public class App 
{
  public static void main(String[] args) throws Exception 
  {
    TelegramApiComponent component = DaggerTelegramApiComponent.create();
    TelegramBotFather botFather = component.buildTelegramBotFather();
    User bot = botFather.init();
    System.out.println("Bot - " + bot);

    botFather.registerUpdateEventListener(new UpdateEventListener());
  }
}
