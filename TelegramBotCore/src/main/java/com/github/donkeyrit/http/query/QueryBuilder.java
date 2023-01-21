package com.github.donkeyrit.http.query;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public interface QueryBuilder 
{
    QueryBuilder setBaseUrl(String url);
    QueryBuilder setApiKey(String apiKey);
    URI buildQuery(String method) throws MalformedURLException, URISyntaxException;   
}
