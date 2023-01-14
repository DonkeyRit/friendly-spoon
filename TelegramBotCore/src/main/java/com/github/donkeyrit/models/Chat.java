package com.github.donkeyrit.models;

import java.util.Optional;
import com.github.donkeyrit.models.enums.ChatType;

/**
 * This object represents a chat.
 */
public record Chat(
    /**
     * Unique identifier for this user or bot. 
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits, so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    int id,
    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    ChatType type, //How to parse JSON
    /**
     * Title, for supergroups, channels and group chats
     */
    Optional<String> title,
    /**
     * Username, for private chats, supergroups and channels if available
     */
    Optional<String> username,
    /**
     * First name of the other party in a private chat
     */
    Optional<String> firstName,
    /**
     * Last name of the other party in a private chat
     */
    Optional<String> lastName,
    /**
     * True, if the supergroup chat is a forum (has topics enabled)
     */
    Optional<Boolean> isForum,
    /**
     * Chat photo. Returned only in getChat.
     */
    Optional<ChatPhoto> photo
) 
{
    
}
