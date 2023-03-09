package com.github.telegrambotstepfather.http.ioc;

import com.github.telegrambotstepfather.http.executor.HttpClientTelegramJsonExecutor;
import com.github.telegrambotstepfather.http.executor.HttpClientExecutor;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class TelegramApiHttpModule extends AbstractModule
{
    @Override
    protected void configure() 
    {
        bind(createHttpClientExecutor()).to(HttpClientTelegramJsonExecutor.class);
    }

    private TypeLiteral<HttpClientExecutor<String, JsonNode>> createHttpClientExecutor()
    {
        return new TypeLiteral<HttpClientExecutor<String, JsonNode>>() {};
    }
}
