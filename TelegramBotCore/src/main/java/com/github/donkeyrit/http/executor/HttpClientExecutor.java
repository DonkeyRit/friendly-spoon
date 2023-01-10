package com.github.donkeyrit.http.executor;

import com.github.donkeyrit.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.net.URI;

public abstract class HttpClientExecutor
{
    public abstract String Get(URI uri) throws TelegramApiRequestException, IOException, InterruptedException;
    public abstract String Post(URI uri, String JSON) throws TelegramApiRequestException, IOException, InterruptedException;
}
