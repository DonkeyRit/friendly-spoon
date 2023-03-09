package com.github.telegrambotstepfather.exceptions;

public class TelegramApiException extends Exception
{
    private static final String PREFIX = "Request to Telegram API was failed. ";

    public TelegramApiException(String message) 
    {
        super(PREFIX + message);
    }

    public TelegramApiException(Throwable cause) 
    {
        super(PREFIX, cause);
    }

    public TelegramApiException(String message, Throwable cause) 
    {
        super(PREFIX + message, cause);
    }
}
