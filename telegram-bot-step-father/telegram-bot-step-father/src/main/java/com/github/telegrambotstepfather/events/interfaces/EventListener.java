package com.github.telegrambotstepfather.events.interfaces;

import com.github.telegrambotstepfather.events.models.EventType;
import com.github.telegrambotstepfather.events.Event;

public interface EventListener<T>
{
    EventType getEventType();
    void handleEvent(Event<T> event);
}
