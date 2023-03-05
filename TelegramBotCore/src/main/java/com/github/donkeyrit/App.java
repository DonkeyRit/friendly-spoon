package com.github.donkeyrit;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.executor.HttpClientTelegramJsonExecutor;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;
import com.github.donkeyrit.ioc.DaggerTelegramApiComponent;
import com.github.donkeyrit.ioc.TelegramApiComponent;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.TelegramBotFatherImpl;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.TelegramBotImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.databind.JsonNode;

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

  private static void register()
  {
    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    ObjectMapper jsonObjectMapper = new ObjectMapper();
    jsonObjectMapper.registerModule(new Jdk8Module());
    HttpClientExecutor<String, JsonNode> httpClientExecutor = new HttpClientTelegramJsonExecutor(jsonObjectMapper);
    QueryBuilder queryBuilder = new TelegramApiQueryBuilder();
    TelegramBot telegramPoolingBot = new TelegramBotImpl(
        configurationSettings,
        httpClientExecutor,
        jsonObjectMapper,
        queryBuilder);

    TelegramBotFather botFather = new TelegramBotFatherImpl(telegramPoolingBot);
  }
}
