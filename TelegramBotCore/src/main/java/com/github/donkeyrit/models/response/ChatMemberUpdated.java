package com.github.donkeyrit.models;

import java.util.Optional;

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
    //TODO: Use data
    int date,
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
