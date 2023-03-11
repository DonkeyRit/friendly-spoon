package com.github.telegrambotstepfather.events.interfaces;

import com.github.telegrambotstepfather.events.Event;

public interface EventSource<E>
{
    void addListener(EventListener<E> listener);
    void removeListener(EventListener<E> listener);
    void notifyListeners(Event<E> event);
}
