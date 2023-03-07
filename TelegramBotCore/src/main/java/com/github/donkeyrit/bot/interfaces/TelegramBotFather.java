package com.github.donkeyrit.bot.interfaces;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.events.interfaces.EventSource;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.models.update.Update;

public interface TelegramBotFather 
{
    EventSource<Update> getUpdatesEventSource();
    User init() throws TelegramApiException, JacksonJsonParsingException;
}
