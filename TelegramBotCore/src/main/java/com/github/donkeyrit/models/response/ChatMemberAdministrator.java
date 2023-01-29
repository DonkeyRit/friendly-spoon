package com.github.donkeyrit.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

/**
 * Represents a chat member that has some additional privileges.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberadministrator">Telegram API ChatMemberAdministrator</a>
 */
public class ChatMemberAdministrator extends ChatMember 
{
    /**
     * True, if the bot is allowed to edit administrator privileges of that user  
     */    
    private boolean canBeEdited;
    
    @JsonProperty(value = "can_be_edited")
    public boolean isCanBeEdited()
    {
        return this.canBeEdited;
    }

    @JsonProperty(value = "can_be_edited")
    public void setCanBeEdited(boolean canBeEdited)
    {
        this.canBeEdited = canBeEdited;
    }

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
     * True, if the administrator can access the chat event log, chat statistics, message statistics in channels, 
     * see channel members, see anonymous administrators in supergroups and ignore slow mode. 
     * Implied by any other administrator privilege
     */
    private boolean canManageChat;

    @JsonProperty(value = "can_manage_chat")
    public boolean canManageChat()
    {
        return this.canManageChat;
    }

    @JsonProperty(value = "can_manage_chat")
    public void setCanManageChat(boolean canManageChat)
    {
        this.canManageChat = canManageChat;
    }

    /**
     * True, if the administrator can delete messages of other users
     */
    private boolean canDeleteMessages;

    @JsonProperty(value = "can_delete_messages")
    public boolean canDeleteMessages()
    {
        return this.canDeleteMessages;
    }

    @JsonProperty(value = "can_delete_messages")
    public void setCanDeleteMessages(boolean canDeleteMessages)
    {
        this.canDeleteMessages = canDeleteMessages;
    }

    /**
     * True, if the administrator can manage video chats
     */
    private boolean canManageVideoChats;

    @JsonProperty(value = "can_manage_video_chats")
    public boolean canManageVideoChats()
    {
        return this.canManageVideoChats;
    }

    @JsonProperty(value = "can_manage_video_chats")
    public void setCanManageVideoChats(boolean canManageVideoChats)
    {
        this.canManageVideoChats = canManageVideoChats;
    }

    /**
     * True, if the administrator can restrict, ban or unban chat members
     */
    private boolean canRestrictMembers;

    @JsonProperty(value = "can_restrict_members")
    public boolean canRestrictMembers()
    {
        return this.canRestrictMembers;
    }

    @JsonProperty(value = "can_restrict_members")
    public void setCanRestrictMembers(boolean canRestrictMembers)
    {
        this.canRestrictMembers = canRestrictMembers;
    }

    /**
     * True, if the administrator can add new administrators with a subset of their own privileges 
     * or demote administrators that they have promoted, directly or indirectly 
     * (promoted by administrators that were appointed by the user)
     */
    private boolean canPromoteMembers;

    @JsonProperty(value = "can_promote_members")
    public boolean canPromoteMembers()
    {
        return this.canPromoteMembers;
    }

    @JsonProperty(value = "can_promote_members")
    public void setCanPromoteMembers(boolean canPromoteMembers)
    {
        this.canPromoteMembers = canPromoteMembers;
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
     * True, if the administrator can post in the channel; channels only
     */
    private Optional<Boolean> canPostMessages;

    @JsonProperty(value = "can_post_messages")
    public Optional<Boolean> canPostMessages()
    {
        return this.canPostMessages;
    }

    @JsonProperty(value = "can_post_messages")
    public void setCanPostMessages(Optional<Boolean> canPostMessages)
    {
        this.canPostMessages = canPostMessages;
    }

    /**
     * True, if the administrator can edit messages of other users and can pin messages; channels only
     */
    private Optional<Boolean> canEditMessages;

    @JsonProperty(value = "can_edit_messages")
    public Optional<Boolean> canEditMessages()
    {
        return this.canEditMessages;
    }

    @JsonProperty(value = "can_edit_messages")
    public void setCanEditMessages(Optional<Boolean> canEditMessages)
    {
        this.canEditMessages = canEditMessages;
    }

    /**
     * True, if the user is allowed to pin messages; groups and supergroups only
     */
    private Optional<Boolean> canPinMessages;

    @JsonProperty(value = "can_pin_messages")
    public Optional<Boolean> canPinMessages()
    {
        return this.canPinMessages;
    }

    @JsonProperty(value = "can_pin_messages")
    public void setCanPinMessages(Optional<Boolean> canPinMessages)
    {
        this.canPinMessages = canPinMessages;
    }

    /**
     * True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
     */
    private Optional<Boolean> canManageTopics;

    @JsonProperty(value = "can_manage_topics")
    public Optional<Boolean> canManageTopics()
    {
        return this.canManageTopics;
    }

    @JsonProperty(value = "can_manage_topics")
    public void setCanManageTopics(Optional<Boolean> canManageTopics)
    {
        this.canManageTopics = canManageTopics;
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
