package com.github.donkeyrit.listeners;

import com.github.donkeyrit.events.models.EventType;
import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.Event;
import com.github.donkeyrit.models.update.Update;

import com.google.inject.Inject;
import java.util.logging.Logger;
import java.util.Optional;

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

        Update update = event.getPayload();
        Optional<com.github.donkeyrit.models.message.Message> message = update.message();

        if(message.isPresent())
        {
            logger.info(message.get().text().orElse("Empty message"));
        }
    }

    @Override
    public EventType getEventType() 
    {
        return EventType.UPDATE_RECEIVED;
    }
}