package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * Represents a chat member that owns the chat and has all administrator privileges.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberowner">Telegram API ChatMemberOwner</a>
 */
//TODO: Add get/set
public class ChatMemberOwner extends ChatMember
{
    /**
     * True, if the user's presence in the chat is hidden
     */
    boolean isAnonymous;
    /**
     * Custom title for this user
     */
    Optional<String> customTitle;
}
