package com.github.donkeyrit.events;

import com.github.donkeyrit.events.models.EventType;

public interface Event<T>
{
    EventType getType();
    T getPayload();
}
