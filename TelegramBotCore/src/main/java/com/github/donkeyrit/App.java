package com.github.donkeyrit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.executor.HttpClientJsonExecutor;
import com.github.donkeyrit.models.response.User;

public class App {
  public static void main(String[] args) throws Exception {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    ObjectMapper jsonObjectMapper = new ObjectMapper();
    HttpClientExecutor httpClientExecutor = new HttpClientJsonExecutor(jsonObjectMapper);
    TelegramPoolingBot telegramPoolingBot = new TelegramPoolingBot(
        configurationSettings,
        httpClientExecutor,
        jsonObjectMapper);

    User settings = telegramPoolingBot.GetMe();
    telegramPoolingBot.GetUpdates();
  }
}
