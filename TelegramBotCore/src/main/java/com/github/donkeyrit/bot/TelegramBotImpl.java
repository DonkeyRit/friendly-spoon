package com.github.donkeyrit.bot;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.response.Update;
import com.github.donkeyrit.models.response.User;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TelegramBotImpl implements TelegramBot
{
	private final static String BASE_URL = "https://api.telegram.org"; 


    private final TelegramBotConfigurationSettings configurationSettings;
	private final HttpClientExecutor httpClientExecutor;
	private final ObjectMapper jsonObjectMapper;
	private final QueryBuilder queryBuilder;

    public TelegramBotImpl(
		TelegramBotConfigurationSettings configurationSettings, 
		HttpClientExecutor httpClientExecutor,
		ObjectMapper jsonObjectMapper,
		QueryBuilder queryBuilder)
    {
        this.configurationSettings = configurationSettings;
		this.httpClientExecutor = httpClientExecutor;
		this.jsonObjectMapper = jsonObjectMapper;
		this.queryBuilder = queryBuilder
			.setBaseUrl(BASE_URL)
			.setApiKey(this.configurationSettings.botApiKey());
    }
    
    public User getMe() throws Exception
    {
		String responseJson = httpClientExecutor.Get(queryBuilder.buildQuery("getMe"));
		return jsonObjectMapper.readValue(responseJson, User.class);
    }

	public Update[] getUpdates(Optional<GetUpdatesRequest> request) throws Exception
	{
		String requestJson = request.isPresent() 
			? jsonObjectMapper.writeValueAsString(request)
			: "{}";
		
		String responseJson = httpClientExecutor.Post(queryBuilder.buildQuery("getUpdates"), requestJson);
		return jsonObjectMapper.readValue(responseJson, Update[].class);
	}
}
