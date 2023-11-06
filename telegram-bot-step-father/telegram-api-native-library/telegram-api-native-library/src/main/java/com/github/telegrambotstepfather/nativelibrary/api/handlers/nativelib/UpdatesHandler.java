package com.github.telegrambotstepfather.nativelibrary.api.handlers.nativelib;

import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.Client;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi;

public class UpdatesHandler implements Client.ResultHandler {

    private final MediatorService mediatorService;

    public UpdatesHandler(MediatorService mediatorService) {
        this.mediatorService = mediatorService;
    }

    @Override
    public void onResult(TdApi.Object object) {
        mediatorService.notify(object);
    }
}
