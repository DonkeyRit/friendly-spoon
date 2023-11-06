package com.github.telegrambotstepfather.nativelibrary.api.events.interfaces;

import com.github.telegrambotstepfather.nativelibrary.api.TdApi;

public interface TelegramEventHandler {
    void handle(TdApi.Object event);
}
