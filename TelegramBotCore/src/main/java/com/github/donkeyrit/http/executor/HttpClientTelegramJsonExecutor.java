package com.github.donkeyrit.http.executor;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.URI;
import java.io.IOException;

public class HttpClientTelegramJsonExecutor implements HttpClientExecutor<String, JsonNode>
{
    private static final String STATUS_FIELD = "ok";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String RESULT_FiELD = "result";

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public HttpClientTelegramJsonExecutor(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public JsonNode Get(URI uri) throws TelegramApiException, IOException, InterruptedException, JacksonJsonParsingException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        return GetResult(response.body());
    }

    @Override
    public JsonNode Post(URI uri, String json) throws TelegramApiException, IOException, InterruptedException, JacksonJsonParsingException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .POST(BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        return GetResult(response.body());   
    }

    private JsonNode GetResult(String json) throws JacksonJsonParsingException, TelegramApiException
    {
        try
        {
            JsonNode root = this.objectMapper.readTree(json);
            boolean isSuccess = root
                .get(STATUS_FIELD)
                .asBoolean();
    
            if(!isSuccess)
            {
                String description = root
                    .get(DESCRIPTION_FIELD)
                    .asText("Something went wrong.");
    
                throw new TelegramApiException(description);
            }
    
            return root.get(RESULT_FiELD);
        }
        catch(JsonProcessingException e) 
        {
            throw new JacksonJsonParsingException(e);
        }
    }
}
