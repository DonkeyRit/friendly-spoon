package com.github.donkeyrit.listeners;

import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.models.EventType;

import java.util.logging.Logger;

import com.github.donkeyrit.events.Event;
import com.github.donkeyrit.models.update.Update;
import com.google.inject.Inject;

public class UpdateEventListener implements EventListener<Update>
{
    private Logger logger;

    @Inject
    public UpdateEventListener(Logger logger)
    {
        this.logger = logger;
    }

    @Override
    public void handleEvent(Event<Update> event) 
    {
        logger.info(() -> "Received update from bot...");
        logger.info(() -> event.getPayload().toString());
    }

    @Override
    public EventType getEventType() 
    {
        return EventType.UPDATE_RECEIVED;
    }
}
