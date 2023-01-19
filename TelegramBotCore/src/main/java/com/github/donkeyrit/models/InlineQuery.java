package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents an incoming inline query. When the user sends an empty query, 
 * your bot could return some default or trending results.
 * @see <a href="https://core.telegram.org/bots/api#inlinequery>Telegram API InlineQuery</a>
 */
public record InlineQuery(
    /**
     * Unique identifier for this query
     */
    int id,
    /**
     * Sender
     */
    User from,
    /**
     * Text of the query (up to 256 characters)
     */
    String query,
    /**
     * Offset of the results to be returned, can be controlled by the bot
     */
    String offset,
    /**
     * Type of the chat from which the inline query was sent. 
     * Can be either “sender” for a private chat with the inline query sender, 
     * “private”, “group”, “supergroup”, or “channel”. 
     * The chat type should be always known for requests sent from official clients and most third-party clients, 
     * unless the request was sent from a secret chat
     */
    //TODO: Use enum
    Optional<String> chatType,
    /**
     * Sender location, only for bots that request user location
     */
    Location location 
) 
{
    
}
