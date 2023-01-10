package com.github.donkeyrit.http.executor;

import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.donkeyrit.exceptions.TelegramApiRequestException;

import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.URI;

public class HttpClientJsonExecutor extends HttpClientExecutor
{
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public HttpClientJsonExecutor(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public String Get(URI uri) throws TelegramApiRequestException, IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        String json = response.body();
        boolean isSuccess = this.objectMapper.readTree(json).get("ok").asBoolean();

        if(!isSuccess)
        {
            throw new TelegramApiRequestException();
        }

        return json;
    }

    @Override
    public String Post(URI uri, String JSON) throws TelegramApiRequestException, IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .POST(BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = this.client.send(request, BodyHandlers.ofString());
        String json = response.body();
        boolean isSuccess = this.objectMapper.readTree(json).get("ok").asBoolean();

        if(!isSuccess)
        {
            throw new TelegramApiRequestException();
        }

        return json;   
    }
}
