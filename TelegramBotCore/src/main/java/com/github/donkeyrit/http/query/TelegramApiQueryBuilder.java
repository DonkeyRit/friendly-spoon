package com.github.donkeyrit.http.query;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;

public class TelegramApiQueryBuilder implements QueryBuilder 
{
    private String host;
    private String apiKey;
    
    @Override
    public QueryBuilder setBaseUrl(String url) 
    {
        this.host = url;
        return this;
    }

    @Override
    public QueryBuilder setApiKey(String apiKey) 
    {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    public URI buildQuery(String method) throws MalformedURLException, URISyntaxException 
    {
        //TODO: Reduce memory allocations
        return new URI(this.host + "/bot" + this.apiKey + "/" + method);
    }
}
