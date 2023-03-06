package com.github.donkeyrit.ioc;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.http.executor.HttpClientTelegramJsonExecutor;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.configurations.ConfigurationManager;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.ioc.Providers.ObjectMapperProvider;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.TelegramBotFatherImpl;
import com.github.donkeyrit.bot.TelegramBotImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

public class TelegramApiBaseModules extends AbstractModule
{
    @Provides
    public TelegramBotConfigurationSettings provideConfig() 
    { 
        return ConfigurationManager.GetTelegramBotConfiguration();
    }

    @Override
    protected void configure() 
    {
        bind(createHttpClientExecutor()).to(HttpClientTelegramJsonExecutor.class);
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);
        bind(QueryBuilder.class).to(TelegramApiQueryBuilder.class);
        bind(TelegramBot.class).to(TelegramBotImpl.class);
        bind(TelegramBotFather.class).to(TelegramBotFatherImpl.class);
    }

    private TypeLiteral<HttpClientExecutor<String, JsonNode>> createHttpClientExecutor()
    {
        return new TypeLiteral<HttpClientExecutor<String, JsonNode>>() {};
    }
}
