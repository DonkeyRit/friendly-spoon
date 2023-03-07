package com.github.donkeyrit.bot.tasks;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.events.models.UpdateReceivedEvent;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.update.Update;
import com.github.donkeyrit.models.update.UpdateType;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TimerTask;
import java.util.Optional;
import java.util.List;

public class GetUpdatesTimerTask extends TimerTask 
{
    private final List<UpdateEventListener> listeners;
    private final TelegramBot bot;
    private final Logger logger;
    
    private Optional<Integer> offset;

    public GetUpdatesTimerTask(TelegramBot bot, List<UpdateEventListener> listeners, Logger logger) 
    {
        this.listeners = listeners;
        this.logger = logger;
        this.bot = bot;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            logger.log(Level.FINER, () -> "Update offset - " + (offset.isPresent() ? offset.get() : "empty"));
            GetUpdatesRequest request = new GetUpdatesRequest(
                offset, 
                null, 
                null, 
                Optional.of(new UpdateType[] {UpdateType.MESSAGE}));

            Update[] updates = bot.getUpdates(Optional.of(request));
            for (Update update : updates) 
            {
                offset = Optional.of(update.updateId());
                UpdateReceivedEvent updateReceivedEvent = new UpdateReceivedEvent(update);
                listeners.forEach(listener -> listener.handleEvent(updateReceivedEvent));
            }
        } 
        catch (TelegramApiException e) 
        {
            e.printStackTrace();
        } 
        catch (JacksonJsonParsingException e) 
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Something went wrong", e);
        }
    }
}
