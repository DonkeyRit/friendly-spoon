package com.github.donkeyrit;

import com.github.donkeyrit.TelegramPoolingBot;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;

public class App 
{
  public static void main(String[] args) throws Exception 
  {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    TelegramPoolingBot telegramPoolingBot = new TelegramPoolingBot(configurationSettings);
    telegramPoolingBot.GetMe();
  }
}
