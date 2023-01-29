package com.github.donkeyrit.models.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.donkeyrit.utils.Deserializers.UnixTimestampDeserializer;

import java.time.Instant;

/**
 * Represents a chat member that was banned in the chat and can't return to the chat or view chat messages.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberbanned">Telegram API ChatMemberBanned</a>
 */
//TODO: Add get/set
public class ChatMemberBanned extends ChatMember
{
    /**
     * Date when restrictions will be lifted for this user; unix time. 
     * If 0, then the user is banned forever
     */
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    Instant until_date;
}
