package com.github.telegrambotstepfather.bot.interfaces;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.models.message.Message;
import com.github.telegrambotstepfather.models.request.GetUpdatesRequest;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.response.User;
import com.github.telegrambotstepfather.models.update.Update;

import java.util.Optional;

public interface TelegramBot 
{
    User getMe() throws TelegramApiException, JacksonJsonParsingException;
    Update[] getUpdates(Optional<GetUpdatesRequest> request) throws TelegramApiException, JacksonJsonParsingException;
    <T> Message sendMessage(SendMessageRequest<T> request) throws TelegramApiException, JacksonJsonParsingException;;
}
