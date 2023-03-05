package com.github.donkeyrit.listeners;

import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.models.EventType;
import com.github.donkeyrit.events.Event;
import com.github.donkeyrit.models.update.Update;

public class UpdateEventListener implements EventListener<Update>
{
    @Override
    public void handleEvent(Event<Update> event) 
    {
        System.out.println(event.getPayload());
    }

    @Override
    public EventType getEventType() 
    {
        return EventType.UPDATE_RECEIVED;
    }
}
