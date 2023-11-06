package com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces;

import com.github.telegrambotstepfather.nativelibrary.api.models.enums.TelegramNativeLibraryEvent;
import com.github.telegrambotstepfather.nativelibrary.api.events.interfaces.TelegramEventHandler;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi;

public interface MediatorService {
    void registerHandler(TelegramNativeLibraryEvent eventType, TelegramEventHandler handler);
    void notify(TdApi.Object event);
}
