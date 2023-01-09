package com.github.donkeyrit.http.executor;

import java.io.IOException;
import java.net.URI;

public abstract class HttpClientExecutor
{
    public abstract String Get(URI uri) throws IOException, InterruptedException;
    public abstract String Post(URI uri);
}
