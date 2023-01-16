package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a Telegram user or bot.
 * @see <a href="https://core.telegram.org/bots/api#user">Telegram API User</a>
 */
public record User (
    /**
     * Unique identifier for this user or bot.
     * This number may have more than 32 significant bits 
     * and some programming languages may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits, so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    int id,
    /**
     * True, if this user is a bot 
     */
    boolean isBot,
    /**
     * User's or bot's first name
     */
    String firstName,
    /**
     * User's or bot's last name
     */
    Optional<String> lastName,
    /**
     * User's or bot's username
     */
    Optional<String> username,
    /**
     * IETF language tag of the user's language
     */
    Optional<String> languageCode,
    /**
     * True, if this user is a Telegram Premium user
     */
    Optional<Boolean> isPremium,
    /**
     * True, if this user added the bot to the attachment menu
     */
    Optional<Boolean> addedToAttachmentMenu,
    /**
     * True, if the bot can be invited to groups. Returned only in getMe
     */
    Optional<Boolean> canJoinGroups,
    /**
     * True, if privacy mode is disabled for the bot. Returned only in getMe.
     */
    Optional<Boolean> canReadAllGroupMessages,
    /**
     * True, if the bot supports inline queries. Returned only in getMe.
     */
    Optional<Boolean> supportsInlineQueries
)
{
    
}
