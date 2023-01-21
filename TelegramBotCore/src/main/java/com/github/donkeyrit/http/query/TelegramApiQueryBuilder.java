package com.github.donkeyrit.http.query;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TelegramApiQueryBuilder implements QueryBuilder 
{
    private String baseUrl;
    private String apiKey;

    @Override
    public QueryBuilder setBaseUrl(String url) {
        this.baseUrl = url;
        return this;
    }

    @Override
    public QueryBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public URI buildQuery(String method) throws MalformedURLException, URISyntaxException {

        URL base = new URL(this.baseUrl, this.apiKey, method);
        return base.toURI();
    }

}
