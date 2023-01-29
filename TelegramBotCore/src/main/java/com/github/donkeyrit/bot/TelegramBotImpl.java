package com.github.donkeyrit.bot;

import com.github.donkeyrit.configurations.models.TelegramBotConfigurationSettings;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.http.executor.HttpClientExecutor;
import com.github.donkeyrit.http.query.QueryBuilder;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.response.Update;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.utils.ThrowingFunction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Optional;

public class TelegramBotImpl implements TelegramBot
{
	private final static String BASE_URL = "https://api.telegram.org"; 
	private final static String GET_ME_METHOD = "getMe";
	private final static String GET_UPDATES = "getUpdates";

    private final TelegramBotConfigurationSettings configurationSettings;
	private final HttpClientExecutor<String, JsonNode> httpClientExecutor;
	private final ObjectMapper jsonObjectMapper;
	private final QueryBuilder queryBuilder;

    public TelegramBotImpl(
		TelegramBotConfigurationSettings configurationSettings, 
		HttpClientExecutor<String, JsonNode> httpClientExecutor,
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
    
    public User getMe() throws TelegramApiException, JacksonJsonParsingException
    {
		ThrowingFunction<Object, User> getMe = (r) ->
		{
			JsonNode responseJsonNode = httpClientExecutor.Get(queryBuilder.buildQuery(GET_ME_METHOD));
			return jsonObjectMapper.treeToValue(responseJsonNode, User.class);
		};

		return sendRequest(getMe, null);
    }

	public Update[] getUpdates(Optional<GetUpdatesRequest> request) throws TelegramApiException, JacksonJsonParsingException
	{
		ThrowingFunction<Optional<GetUpdatesRequest>, Update[]> getUpdates = (r) -> 
		{
			String requestJson = r.isPresent() 
				? jsonObjectMapper.writeValueAsString(r)
				: "{}";
				
			JsonNode responseJsonNode = httpClientExecutor.Post(queryBuilder.buildQuery(GET_UPDATES), requestJson);
			return jsonObjectMapper.treeToValue(responseJsonNode, Update[].class);
		};

		return sendRequest(getUpdates, request);
	}

	private <T,E> E sendRequest(ThrowingFunction<T,E> sendRequest, T request) throws TelegramApiException, JacksonJsonParsingException
	{
		try
		{
			return sendRequest.apply(request);
		}
		catch (TelegramApiException taEx)
		{
			throw taEx;
		}
		catch(JacksonJsonParsingException jjpEx)
		{
			throw jjpEx;
		}
		catch (Exception e)
		{
			throw new TelegramApiException(e);
		}
	}
}
