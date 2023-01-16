package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.
 * @see <a href="https://core.telegram.org/bots/api#update">Telegram API Update</a>
 */
public record Update(
    /**
     * The update's unique identifier.
     */
    int updateId,
    /**
     * New incoming message of any kind - text, photo, sticker, etc.
     */
    Optional<Message> message
)
{
}
