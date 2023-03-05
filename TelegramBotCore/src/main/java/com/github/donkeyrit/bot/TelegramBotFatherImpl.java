package com.github.donkeyrit.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.tasks.GetUpdatesTimerTask;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;

public class TelegramBotFatherImpl implements TelegramBotFather
{
    private final TelegramBot bot;
    private final List<UpdateEventListener> updateEventListeners;

    private final Timer getUpdatesTimer;

    @Inject
    public TelegramBotFatherImpl(TelegramBot bot)
    {
        this.bot = bot;
        updateEventListeners = new ArrayList<>();
        getUpdatesTimer = new Timer();
    }

    @Override
    public void registerUpdateEventListener(UpdateEventListener listener) 
    {
        //TODO: Make thread-safe and avoid duplicates
        updateEventListeners.add(listener);
    }

    @Override
    public User init() throws TelegramApiException, JacksonJsonParsingException 
    {
        GetUpdatesTimerTask getUpdatesTimerTask = new GetUpdatesTimerTask(bot, updateEventListeners);
        getUpdatesTimer.schedule(getUpdatesTimerTask, 0, 10000);
        
        return bot.getMe();
    }
}
