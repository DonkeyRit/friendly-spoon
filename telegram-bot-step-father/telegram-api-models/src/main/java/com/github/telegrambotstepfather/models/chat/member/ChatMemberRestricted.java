package com.github.telegrambotstepfather.models.chat.member;

import com.github.telegrambotstepfather.utils.Deserializers.UnixTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * Represents a chat member that is under certain restrictions in the chat. Supergroups only.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberrestricted">Telegram API ChatMemberRestricted</a>
 */
public class ChatMemberRestricted extends ChatMember
{
    /**
     * True, if the user is a member of the chat at the moment of the request
     */
    private boolean isMember;

    @JsonProperty(value = "is_member")
    public boolean isMember()
    {
        return this.isMember;
    }

    @JsonProperty(value = "is_member")
    public void setIsMember(boolean isMember)
    {
        this.isMember = isMember;
    }
    
    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    private boolean canChangeInfo;

    @JsonProperty(value = "can_change_info")
    public boolean canChangeInfo()
    {
        return this.canChangeInfo;
    }

    @JsonProperty(value = "can_change_info")
    public void setCanChangeInfo(boolean canChangeInfo)
    {
        this.canChangeInfo = canChangeInfo;
    }

    /**
     * True, if the user is allowed to invite new users to the chat
     */
    private boolean canInviteUsers;

    @JsonProperty(value = "can_invite_users")
    public boolean canInviteUsers()
    {
        return this.canInviteUsers;
    }

    @JsonProperty(value = "can_invite_users")
    public void setCanInviteUsers(boolean canInviteUsers)
    {
        this.canInviteUsers = canInviteUsers;
    }
    
    /**
     * True, if the user is allowed to pin messages
     */
    private boolean canPinMessages;

    @JsonProperty(value = "can_pin_messages")
    public boolean canPinMessages()
    {
        return this.canPinMessages;
    }

    @JsonProperty(value = "can_pin_messages")
    public void setCanPinMessages(boolean canPinMessages)
    {
        this.canPinMessages = canPinMessages;
    }
    /**
     * True, if the user is allowed to create forum topics
     */
    private boolean canManageTopics;

    @JsonProperty(value = "can_manage_topics")
    public boolean canManageTopics()
    {
        return this.canManageTopics;
    }

    @JsonProperty(value = "can_manage_topics")
    public void setCanManageTopics(boolean canManageTopics)
    {
        this.canManageTopics = canManageTopics;
    }

    /**
     * True, if the user is allowed to send text messages, contacts, locations and venues
     */
    private boolean canSendMessages;

    @JsonProperty(value = "can_send_messages")
    public boolean canSendMessages()
    {
        return this.canSendMessages;
    }

    @JsonProperty(value = "can_send_messages")
    public void setCanSendMessages(boolean canSendMessages)
    {
        this.canSendMessages = canSendMessages;
    }

    /**
     * True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes
     */
    private boolean canSendMediaMessages;

    @JsonProperty(value = "can_send_media_messages")
    public boolean canSendMediaMessages()
    {
        return this.canSendMediaMessages;
    }

    @JsonProperty(value = "can_send_media_messages")
    public void setCanSendMediaMessages(boolean canSendMediaMessages)
    {
        this.canSendMediaMessages = canSendMediaMessages;
    }

    /**
     * True, if the user is allowed to send polls
     */
    private boolean canSendPolls;

    @JsonProperty(value = "can_send_polls")
    public boolean canSendPolls()
    {
        return this.canSendPolls;
    }

    @JsonProperty(value = "can_send_polls")
    public void setCanSendPolls(boolean canSendPolls)
    {
        this.canSendPolls = canSendPolls;
    }

    /**
     * True, if the user is allowed to send animations, games, stickers and use inline bots
     */
    private boolean canSendOtherMessages;

    @JsonProperty(value = "can_send_other_messages")
    public boolean canSendOtherMessages()
    {
        return this.canSendOtherMessages;
    }

    @JsonProperty(value = "can_send_other_messages")
    public void setCanSendOtherMessages(boolean canSendOtherMessages)
    {
        this.canSendOtherMessages = canSendOtherMessages;
    }

    /**
     * True, if the user is allowed to add web page previews to their messages
     */
    private boolean canAddWebPagePreviews;

    @JsonProperty(value = "can_add_web_page_previews")
    public boolean canAddWebPagePreviews()
    {
        return this.canAddWebPagePreviews;
    }

    @JsonProperty(value = "can_add_web_page_previews")
    public void setCanAddWebPagePreviews(boolean canAddWebPagePreviews)
    {
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    /**
     * Date when restrictions will be lifted for this user; unix time. 
     * If 0, then the user is restricted forever
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
