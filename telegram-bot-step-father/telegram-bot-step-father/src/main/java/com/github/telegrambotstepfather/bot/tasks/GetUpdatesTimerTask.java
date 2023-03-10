package com.github.telegrambotstepfather.bot.tasks;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.events.interfaces.EventSource;
import com.github.telegrambotstepfather.events.models.UpdateReceivedEvent;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.models.request.GetUpdatesRequest;
import com.github.telegrambotstepfather.models.update.Update;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TimerTask;
import java.util.Optional;

public class GetUpdatesTimerTask extends TimerTask 
{
    private final EventSource<Update> updatesEventSource;
    private final TelegramBot bot;
    private final Logger logger;
    
    private Optional<Integer> offset;

    public GetUpdatesTimerTask(TelegramBot bot, EventSource<Update> updatesEventSource, Logger logger) 
    {
        this.updatesEventSource = updatesEventSource;
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
                this.updatesEventSource.notifyListeners(updateReceivedEvent);
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
