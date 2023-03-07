package com.github.donkeyrit.bot.tasks;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.events.models.UpdateReceivedEvent;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.update.Update;

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
        this.offset = Optional.empty();
    }
    
    @Override
    public void run() 
    {
        //TODO: Avoid collisions when bot received a lot of updates
        try 
        {
            logger.log(Level.INFO, () -> "Update offset - " + (offset.isPresent() ? offset.get() : "null"));
            GetUpdatesRequest request = GetUpdatesRequest.of(this.offset);
            Update[] updates = bot.getUpdates(Optional.of(request));
            for (Update update : updates) 
            {
                logger.log(Level.INFO, () -> "Update id - " + update.updateId());
                offset = Optional.of(update.updateId() + 1);
                UpdateReceivedEvent updateReceivedEvent = new UpdateReceivedEvent(update);
                listeners.forEach(listener -> listener.handleEvent(updateReceivedEvent));
            }
        } 
        catch (TelegramApiException e) 
        {
            logger.log(Level.WARNING, "An unsuccessful request to Telegram API.", e);
            e.printStackTrace();
        } 
        catch (JacksonJsonParsingException e) 
        {
            logger.log(Level.SEVERE, "Couldn't parse model. Please take a look on models.", e);
            e.printStackTrace();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, "Something went wrong.", e);
        }
    }
}
