package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * Represents a join request sent to a chat.
 * @see <a href="https://core.telegram.org/bots/api#chatjoinrequest">Telegram API ChatJoinRequest</a>
 */
public record ChatJoinRequest(
    /**
     * Chat to which the request was sent
     */
    Chat chat,
    /**
     * User that sent the join request
     */
    User from,
    /**
     * Date the request was sent in Unix time
     */
    //TODO: Use date
    int date,
    /**
     * Bio of the user.
     */
    Optional<String> bio,
    /**
     * Chat invite link that was used by the user to send the join reques
     */
    Optional<ChatInviteLink> inviteLink
) 
{
    
}
