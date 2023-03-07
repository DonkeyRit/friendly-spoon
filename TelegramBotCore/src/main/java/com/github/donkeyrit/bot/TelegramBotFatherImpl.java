package com.github.donkeyrit.bot;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.tasks.GetUpdatesTimerTask;
import com.github.donkeyrit.events.interfaces.EventSource;
import com.github.donkeyrit.events.EventSourceImpl;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.models.update.Update;

import java.util.logging.Logger;
import java.util.Timer;

import com.google.inject.Inject;

public class TelegramBotFatherImpl implements TelegramBotFather
{
    private final TelegramBot bot;
    private final Logger logger;

    private final EventSource<Update> updatesEventSource;
    private final Timer getUpdatesTimer;

    @Inject
    public TelegramBotFatherImpl(TelegramBot bot, Logger logger)
    {
        this.logger = logger;
        this.bot = bot;
        this.updatesEventSource = new EventSourceImpl<>();
        this.getUpdatesTimer = new Timer();
    }

    public EventSource<Update> getUpdatesEventSource()
    {
        return this.updatesEventSource;
    }

    @Override
    public User init() throws TelegramApiException, JacksonJsonParsingException 
    {
        GetUpdatesTimerTask getUpdatesTimerTask = new GetUpdatesTimerTask(bot, updatesEventSource, logger);
        getUpdatesTimer.schedule(getUpdatesTimerTask, 0, 10000);
        
        return bot.getMe();
    }
}
