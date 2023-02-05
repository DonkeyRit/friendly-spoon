package com.github.donkeyrit.models.response;

import java.util.Optional;

import com.github.donkeyrit.models.message.Message;

/**
 * This object represents an incoming callback query from a callback button in an inline keyboard. 
 * If the button that originated the query was attached to a message sent by the bot, the field message will be present. 
 * If the button was attached to a message sent via the bot (in inline mode), the field inline_message_id will be present. 
 * Exactly one of the fields data or game_short_name will be present.
 * @see <a href="https://core.telegram.org/bots/api#callbackquery">Telegram API CallbackQuery</a>
 */
public record CallbackQuery(
    /**
     * Unique identifier for this query
     */
    String id,
    /**
     * Unique identifier for this query
     */
    User from,
    /**
     * Message with the callback button that originated the query. 
     * Note that message content and message date will not be available if the message is too old
     */
    Optional<Message> message,
    /**
     * Identifier of the message sent via the bot in inline mode, that originated the query.
     */
    Optional<String> inlineMessageId,
    /**
     * Global identifier, uniquely corresponding to the chat to which the message with the callback button was sent. 
     * Useful for high scores in games.
     */
    String chatInstance,
    /**
     * Data associated with the callback button. 
     * Be aware that the message originated the query can contain no callback buttons with this data.
     */
    Optional<String> data,
    /**
     * Short name of a Game to be returned, serves as the unique identifier for the game
     */
    Optional<String> gameShortName
) {
    
}
