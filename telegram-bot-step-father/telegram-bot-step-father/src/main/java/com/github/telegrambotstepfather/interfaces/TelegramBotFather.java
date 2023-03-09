package com.github.telegrambotstepfather.bot.interfaces;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.events.interfaces.EventSource;
import com.github.telegrambotstepfather.models.response.User;
import com.github.telegrambotstepfather.models.update.Update;

public interface TelegramBotFather 
{
    EventSource<Update> getUpdatesEventSource();
    User init() throws TelegramApiException, JacksonJsonParsingException;
}
