package com.github.donkeyrit.bot.tasks;

import java.util.Optional;
import java.util.TimerTask;

import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.models.UpdateReceivedEvent;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.models.update.Update;

public class CustomTimerTask extends TimerTask 
{
    private final TelegramBot bot;
    private final EventListener<Update> listener;
    
    public CustomTimerTask(TelegramBot bot, EventListener<Update> listener) 
    {
        this.bot = bot;
        this.listener = listener;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            Update[] updates = bot.getUpdates(Optional.empty());
            for (Update update : updates) 
            {
                listener.handleEvent(new UpdateReceivedEvent(update));
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
    }
}
