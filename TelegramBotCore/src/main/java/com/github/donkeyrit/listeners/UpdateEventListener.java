package com.github.donkeyrit.listeners;

import com.github.donkeyrit.models.request.SendMessageRequest;
import com.github.donkeyrit.models.update.Update;
import com.github.donkeyrit.events.interfaces.EventListener;
import com.github.donkeyrit.events.models.EventType;
import com.github.donkeyrit.exceptions.JacksonJsonParsingException;
import com.github.donkeyrit.exceptions.TelegramApiException;
import com.github.donkeyrit.events.Event;
import com.github.donkeyrit.bot.interfaces.TelegramBot;

import com.google.inject.Inject;
import java.util.logging.Logger;
import java.util.Optional;

public class UpdateEventListener implements EventListener<Update>
{
    private TelegramBot telegramBot;
    private Logger logger;

    @Inject
    public UpdateEventListener(Logger logger, TelegramBot telegramBot)
    {
        this.telegramBot = telegramBot;
        this.logger = logger;
    }

    @Override
    public void handleEvent(Event<Update> event) 
    {
        logger.info(() -> "Received update from bot...");

        Update update = event.getPayload();
        Optional<com.github.donkeyrit.models.message.Message> message = update.message();

        if(message.isPresent())
        {
            com.github.donkeyrit.models.message.Message receivedMessage = message.get();
            String messageText = receivedMessage.text().orElse("Empty message");
            logger.info("Message text - " + messageText);
            
            SendMessagBack(receivedMessage, messageText);
        }
    }

    @Override
    public EventType getEventType() 
    {
        return EventType.UPDATE_RECEIVED;
    }

    private void SendMessagBack(com.github.donkeyrit.models.message.Message receivedMessage, String messageText)
    {
        double chatId = receivedMessage
            .chat()
            .id();

        SendMessageRequest<Object> sendMessageRequest = SendMessageRequest.of(chatId, "||" + messageText + "||");
        try 
        {
            telegramBot.sendMessage(sendMessageRequest);
        } 
        catch (TelegramApiException | JacksonJsonParsingException e) 
        {
            logger.severe(() -> "Something went wrong. " + e.getMessage());
        }
    }
}