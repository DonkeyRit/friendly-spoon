package com.github.telegrambotstepfather.http.executor;

import java.io.IOException;
import java.net.URI;

public interface HttpClientExecutor<T, E>
{
    E Get(URI uri) throws IOException, InterruptedException, Exception;
    E Post(URI uri, T content) throws IOException, InterruptedException, Exception;
}
