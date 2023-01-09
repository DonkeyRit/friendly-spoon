package com.github.donkeyrit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.executor.HttpClientJsonExecutor;
import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;
import com.github.donkeyrit.settings.TelegramBotSettings;

public class App {
  public static void main(String[] args) throws Exception {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    HttpClientExecutor httpClientExecutor = new HttpClientJsonExecutor();
    ObjectMapper jsonObjectMapper = new ObjectMapper();
    TelegramPoolingBot telegramPoolingBot = new TelegramPoolingBot(
        configurationSettings,
        httpClientExecutor,
        jsonObjectMapper);

    TelegramBotSettings settings = telegramPoolingBot.GetMe();
    
  }
}
