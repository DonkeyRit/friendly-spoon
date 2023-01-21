package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * Represents a result of an inline query that was chosen by the user and sent to their chat partner.
 * It is necessary to enable inline feedback via @BotFather in order to receive these objects in updates.
 * @see <a href="https://core.telegram.org/bots/api#choseninlineresult">Telegram API ChosenInlineResult</a>
 */
public record ChosenInlineResult(
    /**
     * The unique identifier for the result that was chosen
     */
    String resultId,
    /**
     * The user that chose the result
     */
    User from,
    /**
     * Sender location, only for bots that require user location
     */
    Optional<Location> location,
    /**
     * Identifier of the sent inline message. Available only if there is an inline keyboard attached to the message. 
     * Will be also received in callback queries and can be used to edit the message.
     */
    Optional<String> inlineMessageId,
    /**
     * The query that was used to obtain the result
     */
    String query
) 
{
    
}
