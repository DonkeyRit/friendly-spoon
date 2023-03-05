package com.github.donkeyrit.models.chat;

import com.github.donkeyrit.models.response.Location;

/**
 * Represents a location to which a chat is connected.
 * @see <a href="https://core.telegram.org/bots/api#chatlocation">Telegram API ChatLocation</a>
 */
public record ChatLocation(
    /**
     * The location to which the supergroup is connected. 
     * Can't be a live location.
     */
    Location location,
    /**
     * Location address; 1-64 characters, as defined by the chat owner
     */
    String address
) 
{
    
}
