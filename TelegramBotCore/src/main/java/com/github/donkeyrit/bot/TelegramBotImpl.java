package com.github.donkeyrit.bot;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.response.Update;
import com.github.donkeyrit.models.response.User;

import java.util.Optional;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TelegramBotImpl implements TelegramBot
{
    private final TelegramBotConfigurationSettings configurationSettings;
	private final HttpClientExecutor httpClientExecutor;
	private final ObjectMapper jsonObjectMapper;

    public TelegramBotImpl(
		TelegramBotConfigurationSettings configurationSettings, 
		HttpClientExecutor httpClientExecutor,
		ObjectMapper jsonObjectMapper)
    {
        this.configurationSettings = configurationSettings;
		this.httpClientExecutor = httpClientExecutor;
		this.jsonObjectMapper = jsonObjectMapper;
    }
    
    public User getMe() throws Exception
    {
		String urlStr = String.format("https://api.telegram.org/bot%s/getMe", this.configurationSettings.botApiKey());
		String responseJson = httpClientExecutor.Get(new URI(urlStr));
		return jsonObjectMapper.readValue(responseJson, User.class);
    }

	public Update getUpdates(GetUpdatesRequest request) throws Exception
	{
		String urlStr = String.format("https://api.telegram.org/bot%s/getUpdates", this.configurationSettings.botApiKey());
		String responseJson = httpClientExecutor.Post(new URI(urlStr), "{}");
		return jsonObjectMapper.readValue(responseJson, Update.class);
	}

	public Update getUpdates() throws Exception
	{
		String urlStr = String.format("https://api.telegram.org/bot%s/getUpdates", this.configurationSettings.botApiKey());
		String responseJson = httpClientExecutor.Post(new URI(urlStr), "{}");
		return jsonObjectMapper.readValue(responseJson, Update.class);
	}
}
