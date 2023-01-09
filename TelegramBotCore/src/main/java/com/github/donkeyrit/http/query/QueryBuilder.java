package com.github.donkeyrit.http.query;

import java.net.URI;

public interface QueryBuilder 
{
    QueryBuilder SetBaseUrl(String url);
    URI BuildQuery();   
}
