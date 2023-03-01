package com.github.donkeyrit.events.models;

import com.github.donkeyrit.events.Event;
import com.github.donkeyrit.models.update.Update;

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
