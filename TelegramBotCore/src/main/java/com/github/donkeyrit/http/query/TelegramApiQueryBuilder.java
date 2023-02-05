package com.github.donkeyrit.http.query;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;

public class TelegramApiQueryBuilder implements QueryBuilder 
{
    private String host;
    private String apiKey;

    private String baseUrl;
    
    @Override
    public QueryBuilder setBaseUrl(String url) 
    {
        this.host = url;
        this.baseUrl = null;
        return this;
    }

    @Override
    public QueryBuilder setApiKey(String apiKey) 
    {
        this.apiKey = apiKey;
        this.baseUrl = null;
        return this;
    }

    @Override
    public URI buildQuery(String method) throws MalformedURLException, URISyntaxException 
    {
        if(baseUrl == null)
        {
            this.baseUrl = this.host + "/bot" + this.apiKey + "/";
        }
        
        return new URI(this.baseUrl + method);
    }
}
