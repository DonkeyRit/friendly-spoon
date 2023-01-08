package com.github.donkeyrit;

import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;
import com.github.donkeyrit.settings.TelegramBotSettings;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.query.TelegramApiQueryBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TelegramPoolingBot 
{
    private final TelegramBotConfigurationSettings configurationSettings;
	private final HttpClientExecutor httpClientExecutor;
	private final ObjectMapper jsonObjectMapper;

    public TelegramPoolingBot(
		TelegramBotConfigurationSettings configurationSettings, 
		HttpClientExecutor httpClientExecutor,
		ObjectMapper jsonObjectMapper)
    {
        this.configurationSettings = configurationSettings;
		this.httpClientExecutor = httpClientExecutor;
		this.jsonObjectMapper = jsonObjectMapper;
    }
    
    public TelegramBotSettings GetMe() throws Exception
    {		
		TelegramApiQueryBuilder queryBuilder = new TelegramApiQueryBuilder(
			"https://api.telegram.org/bot%s/getMe", 
			this.configurationSettings.botApiKey());
		
		Object responseJson = httpClientExecutor.Get(queryBuilder.BuildQuery());
		return jsonObjectMapper.readValue(responseJson.toString(), TelegramBotSettings.class);
    }
}
