package com.github.telegrambotstepfather.bot;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBotFather;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.bot.tasks.GetUpdatesTimerTask;
import com.github.telegrambotstepfather.events.interfaces.EventSource;
import com.github.telegrambotstepfather.events.EventSourceImpl;
import com.github.telegrambotstepfather.models.response.User;
import com.github.telegrambotstepfather.models.update.Update;

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
