package com.github.telegrambotstepfather.models.chat.member;

import com.github.telegrambotstepfather.utils.Deserializers.UnixTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * Represents a chat member that was banned in the chat and can't return to the chat or view chat messages.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberbanned">Telegram API ChatMemberBanned</a>
 */
public class ChatMemberBanned extends ChatMember
{
    /**
     * Date when restrictions will be lifted for this user; unix time. 
     * If 0, then the user is banned forever
     */
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Instant untilDate;

    @JsonProperty(value = "until_date")
    public Instant getUntilDate()
    {
        return this.untilDate;
    }

    @JsonProperty(value = "until_date")
    public void setUntilDate(Instant untilDate)
    {
        this.untilDate = untilDate;
    }
}
