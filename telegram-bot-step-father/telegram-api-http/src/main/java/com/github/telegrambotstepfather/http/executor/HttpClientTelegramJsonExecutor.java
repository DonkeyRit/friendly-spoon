package com.github.telegrambotstepfather.http.executor;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.URI;

import com.google.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;

public class HttpClientTelegramJsonExecutor implements HttpClientExecutor<String, JsonNode>
{
    private static final String STATUS_FIELD = "ok";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String RESULT_FiELD = "result";

    private final ObjectMapper objectMapper;
    private final HttpClient client;
    private final Logger logger;

    @Inject
    public HttpClientTelegramJsonExecutor(ObjectMapper objectMapper, Logger logger)
    {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
        this.logger = logger;
    }

    @Override
    public JsonNode Get(URI uri) throws TelegramApiException, IOException, InterruptedException, JacksonJsonParsingException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        logger.log(Level.FINEST, "Send get request by next uri - ", request.uri());
        return GetResult(response.body());
    }

    @Override
    public JsonNode Post(URI uri, String json) throws TelegramApiException, IOException, InterruptedException, JacksonJsonParsingException
    {
        BodyPublisher bodyPublisher = BodyPublishers.ofByteArray(json.getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Content-Type", "application/json")
            .POST(bodyPublisher)
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        logger.log(Level.FINEST, "Send post request by next uri - ", request.uri());
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
    
            logger.log(Level.FINEST, "Result output - ", json);
            return root.get(RESULT_FiELD);
        }
        catch(JsonProcessingException e) 
        {
            throw new JacksonJsonParsingException(e);
        }
    }
}
