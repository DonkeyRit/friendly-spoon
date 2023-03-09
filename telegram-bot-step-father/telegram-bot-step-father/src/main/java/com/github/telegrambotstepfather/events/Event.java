package com.github.telegrambotstepfather.events;

import com.github.telegrambotstepfather.events.models.EventType;

public interface Event<T>
{
    EventType getType();
    T getPayload();
}
