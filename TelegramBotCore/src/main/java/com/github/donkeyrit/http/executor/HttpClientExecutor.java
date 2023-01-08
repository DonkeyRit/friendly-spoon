package com.github.donkeyrit.http.executor;

public abstract class HttpClientExecutor
{
    public abstract String Get(String url);
    public abstract String Post(String url);
}
