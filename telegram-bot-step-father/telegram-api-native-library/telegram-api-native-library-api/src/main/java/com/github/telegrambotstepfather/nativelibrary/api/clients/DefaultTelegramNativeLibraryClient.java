package com.github.telegrambotstepfather.nativelibrary.api.clients;

import com.github.telegrambotstepfather.nativelibrary.api.clients.interfaces.TelegramNativeLibraryClient;
import com.github.telegrambotstepfather.nativelibrary.api.handlers.nativelib.DefaultHandler;
import com.github.telegrambotstepfather.nativelibrary.api.handlers.nativelib.LogMessageHandler;
import com.github.telegrambotstepfather.nativelibrary.api.handlers.nativelib.UpdatesHandler;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.Client;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Function;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Ok;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.IOError;

public class DefaultTelegramNativeLibraryClient implements TelegramNativeLibraryClient {

    private static final DefaultHandler defaultHandler = new DefaultHandler();

    private final Client.ResultHandler updatesHandler;
    private final MediatorService mediatorService;
    private Client client;

    @Inject
    public DefaultTelegramNativeLibraryClient(MediatorService mediatorService) {
        this.mediatorService = mediatorService;
        this.updatesHandler = new UpdatesHandler(mediatorService);
    }

    @Override
    public void initialize() {
        this.initializeLibrary();
        this.client = Client.create(this.updatesHandler, null, null);
    }

    private void initializeLibrary() {
        // set log message handler to handle only fatal errors (0) and plain log
        // messages (-1)
        Client.setLogMessageHandler(0, new LogMessageHandler());

        // disable TDLib log and redirect fatal errors and plain log messages to a file
        Client.execute(new TdApi.SetLogVerbosityLevel(0));
        if (Client.execute(new TdApi.SetLogStream(new TdApi.LogStreamFile("tdlib.log", 1 << 27, false))) instanceof TdApi.Error) {
            throw new IOError(new IOException("Write access to the current directory is required"));
        }
    }

    @Override
    public void sendRequest(Function<Ok> query) {
        if (this.client == null) {
            return;
        }
        this.client.send(query, defaultHandler);
    }

    @Override
    public MediatorService getMediatorService() {
        return this.mediatorService;
    }
}
