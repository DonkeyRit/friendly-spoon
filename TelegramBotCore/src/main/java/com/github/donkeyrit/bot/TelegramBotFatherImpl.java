package com.github.donkeyrit.bot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;

import com.github.donkeyrit.bot.interfaces.TelegramBot;
import com.github.donkeyrit.bot.interfaces.TelegramBotFather;
import com.github.donkeyrit.bot.tasks.CustomTimerTask;
import com.github.donkeyrit.events.EventListener;
import com.github.donkeyrit.events.models.EventType;
import com.github.donkeyrit.events.models.UpdateReceivedEvent;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.models.update.Update;

public class TelegramBotFatherImpl implements TelegramBotFather
{
    private final TelegramBot bot;
    private final Map<EventType, EventListener<Update>> subscribers;
    private final Timer getUpdatesTimer;


    public TelegramBotFatherImpl(TelegramBot bot)
    {
        this.bot = bot;
        subscribers = new HashMap<>();
        getUpdatesTimer = new Timer();
    }

    @Override
    public void registerListener(EventListener<Update> listener) 
    {
        subscribers.putIfAbsent(listener.getEventType(), listener);
    }

    @Override
    public User init() throws TelegramApiException, JacksonJsonParsingException 
    {
        getUpdatesTimer.schedule(new CustomTimerTask(bot, subscribers.get(EventType.UPDATE_RECEIVED)), 0, 10000);
        return bot.getMe();
    }
}
