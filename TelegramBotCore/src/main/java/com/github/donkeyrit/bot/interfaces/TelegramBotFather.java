package com.github.donkeyrit.bot.interfaces;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.listeners.UpdateEventListener;
import com.github.donkeyrit.models.response.User;

public interface TelegramBotFather 
{
    void registerUpdateEventListener(UpdateEventListener listener);
    User init() throws TelegramApiException, JacksonJsonParsingException;
}
