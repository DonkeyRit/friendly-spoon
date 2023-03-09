package com.github.telegrambotstepfather.events;

import com.github.telegrambotstepfather.events.interfaces.EventListener;
import com.github.telegrambotstepfather.events.interfaces.EventSource;

import java.util.ArrayList;
import java.util.List;

public class EventSourceImpl<E> implements EventSource<E>
{
    private final List<EventListener<E>> listeners;

    public EventSourceImpl()
    {
        this.listeners = new ArrayList<>();
    }

    public void addListener(EventListener<E> listener) 
    {
        this.listeners.add(listener);
    }

    public void removeListener(EventListener<E> listener) 
    {
        listeners.remove(listener);
    }

    public void notifyListeners(Event<E> event)
    {
        listeners.forEach(listener -> listener.handleEvent(event));
    }
}
