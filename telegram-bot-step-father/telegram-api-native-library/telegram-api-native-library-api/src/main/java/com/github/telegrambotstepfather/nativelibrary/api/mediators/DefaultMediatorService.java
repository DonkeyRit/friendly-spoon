package com.github.telegrambotstepfather.nativelibrary.api.mediators;

import com.github.telegrambotstepfather.nativelibrary.api.events.interfaces.TelegramEventHandler;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.models.enums.TelegramNativeLibraryEvent;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultMediatorService implements MediatorService {

    private final Map<TelegramNativeLibraryEvent, List<TelegramEventHandler>> handlers;
    
    public DefaultMediatorService() {
        this.handlers = new ConcurrentHashMap<>();
    }

    public void registerHandler(TelegramNativeLibraryEvent eventType, TelegramEventHandler handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    public void notify(TdApi.Object event) {
        TelegramNativeLibraryEvent eventType = TelegramNativeLibraryEvent.fromEventConstructorId(event.getConstructor());
        List<TelegramEventHandler> eventHandlers = handlers.getOrDefault(eventType, null);
        if (eventHandlers != null) {
            for (TelegramEventHandler handler : eventHandlers) {
                handler.handle(event);
            }
        }
    }
}
