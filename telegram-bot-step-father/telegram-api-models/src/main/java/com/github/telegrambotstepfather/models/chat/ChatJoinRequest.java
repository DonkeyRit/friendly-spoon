package com.github.telegrambotstepfather.models.chat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.telegrambotstepfather.models.response.User;
import com.github.utils.Deserializers.UnixTimestampDeserializer;

import java.util.Optional;
import java.time.Instant;

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
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    Instant date,
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
