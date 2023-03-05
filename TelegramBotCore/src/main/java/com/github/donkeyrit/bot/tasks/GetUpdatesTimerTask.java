package com.github.donkeyrit.bot.tasks;

import java.util.List;
import java.util.Optional;
import java.util.TimerTask;

import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.models.UpdateReceivedEvent;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.update.Update;

public class GetUpdatesTimerTask extends TimerTask 
{
    private final TelegramBot bot;
    private final List<UpdateEventListener> listeners;
    
    public GetUpdatesTimerTask(TelegramBot bot, List<UpdateEventListener> listeners) 
    {
        this.bot = bot;
        this.listeners = listeners;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            Update[] updates = bot.getUpdates(Optional.empty());
            for (Update update : updates) 
            {
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
            System.out.println("Something went wrong");
            System.out.println(e);
        }
    }
}
