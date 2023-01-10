package com.github.donkeyrit;

import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;
import com.github.donkeyrit.settings.TelegramBotSettings;
import com.github.donkeyrit.http.executor.HttpClientExecutor;

import java.net.URI;

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
		String urlStr = String.format("https://api.telegram.org/bot%s/getMe", this.configurationSettings.botApiKey());
		String responseJson = httpClientExecutor.Get(new URI(urlStr));
		return jsonObjectMapper.readValue(responseJson, TelegramBotSettings.class);
    }

	public void GetUpdates() throws Exception
	{
		String urlStr = String.format("https://api.telegram.org/bot%s/getUpdates", this.configurationSettings.botApiKey());
		String responseJson = httpClientExecutor.Post(new URI(urlStr), "{}");
	}
}
