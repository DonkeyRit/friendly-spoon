package com.github.donkeyrit.ioc;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.executor.HttpClientTelegramJsonExecutor;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.TelegramBotFatherImpl;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.TelegramBotImpl;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Module
public class TelegramApiModule 
{
    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper() 
    {
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.registerModule(new Jdk8Module());
        return jsonObjectMapper;
    }

    @Provides
    @Singleton
    public TelegramBotConfigurationSettings provideBrand() 
    { 
        return ConfigurationManager.GetTelegramBotConfiguration();
    }

    @Provides
    public QueryBuilder provideQueryBuilder()
    {
        return new TelegramApiQueryBuilder();
    }

    @Provides
    @Singleton
    public HttpClientExecutor<String, JsonNode> provideTelegramJsonHttpClient(ObjectMapper jsonObjectMapper)
    {
         return new HttpClientTelegramJsonExecutor(jsonObjectMapper);
    }

    @Provides
    public TelegramBot provideTelegramBot(
        TelegramBotConfigurationSettings configurationSettings,
        HttpClientExecutor<String, JsonNode> httpClientExecutor,
        ObjectMapper jsonObjectMapper,
        QueryBuilder queryBuilder
        )
    {
        return new TelegramBotImpl(
            configurationSettings,
            httpClientExecutor,
            jsonObjectMapper,
            queryBuilder);
    }

    @Provides
    public TelegramBotFather provideTelegramBotFather(TelegramBot telegramPoolingBot)
    {
        return new TelegramBotFatherImpl(telegramPoolingBot);
    }
}