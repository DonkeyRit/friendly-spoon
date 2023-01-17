package com.github.donkeyrit.models;

/**
 * This object represents a service message about a change in auto-delete timer settings.
 * @see <a href="https://core.telegram.org/bots/api#messageautodeletetimerchanged">Telegram API MessageAutoDeleteTimerChanged</a>
 */
public record MessageAutoDeleteTimerChanged(
    /**
     * New auto-delete time for messages in the chat; in seconds
     */
    int messageAutoDeleteTime
) 
{
    
}
