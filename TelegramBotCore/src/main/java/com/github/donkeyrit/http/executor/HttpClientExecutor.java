package com.github.donkeyrit.http.executor;

import com.github.donkeyrit.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;

public interface HttpClientExecutor<T, E>
{
    E Get(URI uri) throws TelegramApiException, IOException, InterruptedException;
    E Post(URI uri, T content) throws TelegramApiException, IOException, InterruptedException;
}
