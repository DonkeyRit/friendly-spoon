package com.github.telegrambotstepfather.nativelibrary.api.clients.interfaces;

import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Function;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Ok;

public interface TelegramNativeLibraryClient {
    void initialize();
    void sendRequest(Function<Ok> query);
    MediatorService getMediatorService();
}
