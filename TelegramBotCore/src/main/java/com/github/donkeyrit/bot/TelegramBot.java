package com.github.donkeyrit.bot;

import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.models.message.Message;
import com.github.donkeyrit.models.request.GetUpdatesRequest;
import com.github.donkeyrit.models.request.SendMessageRequest;
import com.github.donkeyrit.models.response.User;
import com.github.donkeyrit.models.update.Update;

import java.util.Optional;

public interface TelegramBot 
{
    User getMe() throws TelegramApiException, JacksonJsonParsingException;
    Update[] getUpdates(Optional<GetUpdatesRequest> request) throws TelegramApiException, JacksonJsonParsingException;
    <T> Message sendMessage(SendMessageRequest<T> request) throws TelegramApiException, JacksonJsonParsingException;;
}
