package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a message.
 * @see <a href="https://core.telegram.org/bots/api#message">Telegram API Message</a>
 */
public record Message(
    /**
     * Unique message identifier inside this chat
     */
    int messageId,
    /**
     * Unique identifier of a message thread to which the message belongs; for supergroups only
     */
    Optional<Integer> messageThreadId,
    /**
     * Sender of the message; empty for messages sent to channels. 
     * For backward compatibility, the field contains a fake sender user in non-channel chats, 
     * if the message was sent on behalf of a chat.
     */
    Optional<User> from
) 
{

}
