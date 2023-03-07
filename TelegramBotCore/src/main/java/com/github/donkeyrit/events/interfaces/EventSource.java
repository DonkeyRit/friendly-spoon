package com.github.donkeyrit.events.interfaces;

import com.github.donkeyrit.events.Event;

public interface EventSource<E>
{
    void addListener(EventListener<E> listener);
    void removeListener(EventListener<E> listener);
    void notifyListeners(Event<E> event);
}
