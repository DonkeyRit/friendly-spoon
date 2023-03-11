package com.github.telegrambotstepfather.events.models;

import com.github.telegrambotstepfather.events.Event;
import com.github.telegrambotstepfather.models.update.Update;

public class UpdateReceivedEvent implements Event<Update>
{
    private Update payload;

    public UpdateReceivedEvent(Update payload)
    {
        this.payload = payload;
    }


    @Override
    public EventType getType() 
    {
        return EventType.UPDATE_RECEIVED;
    }

    @Override
    public Update getPayload() 
    {
        return payload;
    }
}
