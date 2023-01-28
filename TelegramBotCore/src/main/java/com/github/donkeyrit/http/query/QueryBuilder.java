package com.github.donkeyrit.http.query;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URI;

public interface QueryBuilder 
{
    QueryBuilder setBaseUrl(String url);
    QueryBuilder setApiKey(String apiKey);
    URI buildQuery(String method) throws MalformedURLException, URISyntaxException;   
}
