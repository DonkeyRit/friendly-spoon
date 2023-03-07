package com.github.donkeyrit.events.interfaces;

import com.github.donkeyrit.events.models.EventType;
import com.github.donkeyrit.events.Event;

public interface EventListener<T>
{
    EventType getEventType();
    void handleEvent(Event<T> event);
}
