package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * 
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
