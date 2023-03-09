package com.github.telegrambotstepfather.models.chat.member;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.telegrambotstepfather.models.chat.Chat;
import com.github.telegrambotstepfather.models.chat.ChatInviteLink;
import com.github.telegrambotstepfather.models.response.User;
import com.github.utils.Deserializers.UnixTimestampDeserializer;

import java.util.Optional;
import java.time.Instant;

/**
 * This object represents changes in the status of a chat member.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberupdated">Telegram API ChatMemberUpdated</a>
 */
public record ChatMemberUpdated(
    /**
     * Chat the user belongs to
     */
    Chat chat,
    /**
     * Performer of the action, which resulted in the change
     */
    User from,
    /**
     * Date the change was done in Unix time
     */
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    Instant date,
    /**
     * Previous information about the chat member
     */
    ChatMember oldChatMember,
    /**
     * New information about the chat member
     */
    ChatMember newChatMember,
    /**
     * Chat invite link, which was used by the user to join the chat; for joining by invite link events only.
     */
    Optional<ChatInviteLink> inviteLink
) 
{
    
}
