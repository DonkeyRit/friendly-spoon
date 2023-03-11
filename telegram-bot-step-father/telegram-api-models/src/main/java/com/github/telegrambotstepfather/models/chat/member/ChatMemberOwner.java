package com.github.telegrambotstepfather.models.chat.member;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Represents a chat member that owns the chat and has all administrator privileges.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberowner">Telegram API ChatMemberOwner</a>
 */
public class ChatMemberOwner extends ChatMember
{
    /**
     * True, if the user's presence in the chat is hidden
     */
    private boolean isAnonymous;

    @JsonProperty(value = "is_anonymous")
    public boolean isAnonymous()
    {
        return this.isAnonymous;
    }

    @JsonProperty(value = "is_anonymous")
    public void setAnonymous(boolean isAnonymous)
    {
        this.isAnonymous = isAnonymous;
    }
    
    /**
     * Custom title for this user
     */
    private Optional<String> customTitle;

    @JsonProperty(value = "custom_title")
    public Optional<String> getCustomTitle()
    {
        return this.customTitle;
    }

    @JsonProperty(value = "custom_title")
    public void setCustomTitle(Optional<String> customTitle)
    {
        this.customTitle = customTitle;
    }
}
