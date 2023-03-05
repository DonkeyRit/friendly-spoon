package com.github.donkeyrit.events;

import com.github.donkeyrit.events.models.EventType;

public interface EventListener<T>
{
    EventType getEventType();
    void handleEvent(Event<T> event);
}
