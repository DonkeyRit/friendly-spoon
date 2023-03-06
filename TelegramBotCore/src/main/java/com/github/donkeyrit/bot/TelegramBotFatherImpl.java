package com.github.donkeyrit.bot;

import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.tasks.GetUpdatesTimerTask;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

import com.google.inject.Inject;

public class TelegramBotFatherImpl implements TelegramBotFather
{
    private final TelegramBot bot;
    private final Logger logger;
    private final List<UpdateEventListener> updateEventListeners;

    private final Timer getUpdatesTimer;

    @Inject
    public TelegramBotFatherImpl(TelegramBot bot, Logger logger)
    {
        this.logger = logger;
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
