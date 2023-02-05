package com.github.donkeyrit;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.executor.HttpClientTelegramJsonExecutor;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.models.update.Update;
import com.github.donkeyrit.bot.TelegramBotImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Duration;
import java.util.Optional;

public class App {
  public static void main(String[] args) throws Exception {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    ObjectMapper jsonObjectMapper = new ObjectMapper();
    jsonObjectMapper.registerModule(new Jdk8Module());
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
      Thread.sleep(Duration.ofSeconds(10));
      System.out.println(updates);
    }
  }
}
