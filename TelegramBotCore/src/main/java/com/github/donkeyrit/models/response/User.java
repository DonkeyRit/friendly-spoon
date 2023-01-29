package com.github.donkeyrit.models.response;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(value = "is_bot")
    boolean isBot,
    /**
     * User's or bot's first name
     */
    @JsonProperty(value = "first_name")
    String firstName,
    /**
     * User's or bot's last name
     */
    @JsonProperty(value = "last_name")
    Optional<String> lastName,
    /**
     * User's or bot's username
     */
    Optional<String> username,
    /**
     * IETF language tag of the user's language
     */
    @JsonProperty(value = "language_code")
    Optional<String> languageCode,
    /**
     * True, if this user is a Telegram Premium user
     */
    @JsonProperty(value = "is_premium")
    Optional<Boolean> isPremium,
    /**
     * True, if this user added the bot to the attachment menu
     */
    @JsonProperty(value = "added_to_attachment_menu")
    Optional<Boolean> addedToAttachmentMenu,
    /**
     * True, if the bot can be invited to groups. Returned only in getMe
     */
    @JsonProperty(value = "can_join_groups")
    Optional<Boolean> canJoinGroups,
    /**
     * True, if privacy mode is disabled for the bot. Returned only in getMe.
     */
    @JsonProperty(value = "can_read_all_group_messages")
    Optional<Boolean> canReadAllGroupMessages,
    /**
     * True, if the bot supports inline queries. Returned only in getMe.
     */
    @JsonProperty(value = "supports_inline_queries")
    Optional<Boolean> supportsInlineQueries
)
{
    
}
