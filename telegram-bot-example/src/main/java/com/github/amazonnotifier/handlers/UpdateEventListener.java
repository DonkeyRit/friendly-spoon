package com.github.amazonnotifier.handlers;

import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.message.Message;
import com.github.telegrambotstepfather.models.update.Update;
import com.github.telegrambotstepfather.events.interfaces.EventListener;
import com.github.telegrambotstepfather.events.models.EventType;
import com.github.telegrambotstepfather.events.Event;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.amazonnotifier.site.AmazonScrapper;

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
        Optional<Message> message = update.message();

        if(message.isPresent())
        {
            Message receivedMessage = message.get();
            String messageText = receivedMessage.text().orElse("Empty message");
            logger.info("Message text - " + messageText);

            try
            {
                AmazonScrapper scrapper = new AmazonScrapper();
                String deliveryDate = scrapper.parse(messageText);
                SendMessagBack(receivedMessage, deliveryDate);
            }
            catch(Exception ex)
            {
                logger.severe("Something went wrong. " + ex.getMessage());
            }
        }
    }

    @Override
    public EventType getEventType() 
    {
        return EventType.UPDATE_RECEIVED;
    }

    private void SendMessagBack(Message receivedMessage, String messageText)
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