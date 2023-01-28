package com.github.donkeyrit;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.executor.HttpClientTelegramJsonExecutor;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.models.response.Update;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.bot.TelegramBotImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class App {
  public static void main1(String[] args) throws Exception {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    ObjectMapper jsonObjectMapper = new ObjectMapper();
    HttpClientExecutor<String, JsonNode> httpClientExecutor = new HttpClientTelegramJsonExecutor(jsonObjectMapper);
    QueryBuilder queryBuilder = new TelegramApiQueryBuilder();
    TelegramBotImpl telegramPoolingBot = new TelegramBotImpl(
        configurationSettings,
        httpClientExecutor,
        jsonObjectMapper,
        queryBuilder);

    User settings = telegramPoolingBot.getMe();
    System.out.println("Bot - " + settings.toString());

    while(true)
    {
      Update[] updates = telegramPoolingBot.getUpdates(Optional.empty());
      System.out.println(updates);
    }
  }
}
