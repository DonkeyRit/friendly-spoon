package com.github.telegrambotstepfather.models.chat.permissions;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

/**
 * Represents the rights of an administrator in a chat.
 * @see <a href="https://core.telegram.org/bots/api#chatadministratorrights">ChatAdministratorRights</a>
 */
public record ChatAdministratorRights(
    /**
     * True, if the user's presence in the chat is hidden
     */
    @JsonProperty(value = "is_anonymous")
    boolean isAnonymous,
    /**
     * True, if the administrator can access 
     *      - the chat event log, 
     *      - chat statistics, 
     *      - message statistics in channels, 
     *      - see channel members, 
     *      - see anonymous administrators in supergroups 
     *      - and ignore slow mode.
     * Implied by any other administrator privilege
     */
    @JsonProperty(value = "can_manage_chat")
    boolean canManageChat,
    /**
     * True, if the administrator can delete messages of other users
     */
    @JsonProperty(value = "can_delete_messages")
    boolean canDeleteMessages,
    /**
     * True, if the administrator can manage video chats
     */
    @JsonProperty(value = "can_manage_video_chats")
    boolean canManageVideoChats,
    /**
     * True, if the administrator can restrict, ban or unban chat members
     */
    @JsonProperty(value = "can_restrict_members")
    boolean canRestrictMembers,
    /**
     * True, if the administrator can add new administrators with a subset of their own privileges 
     * or demote administrators that they have promoted, 
     * directly or indirectly (promoted by administrators that were appointed by the user)
     */
    @JsonProperty(value = "can_promote_members")
    boolean canPromoteMembers,
    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    @JsonProperty(value = "can_change_info")
    boolean canChangeInfo,
    /**
     * True, if the user is allowed to invite new users to the chat
     */
    @JsonProperty(value = "can_invite_users")
    boolean canInviteUsers,
    /**
     * True, if the administrator can post in the channel; channels only
     */
    @JsonProperty(value = "can_post_messages")
    Optional<Boolean> canPostMessages,
    /**
     * True, if the administrator can edit messages of other users and can pin messages; channels only
     */
    @JsonProperty(value = "can_edit_messages")
    Optional<Boolean> canEditMessages,
    /**
     * True, if the user is allowed to pin messages; groups and supergroups only
     */
    @JsonProperty(value = "can_pin_messages")
    Optional<Boolean> canPinMessages,
    /**
     * True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
     */
    @JsonProperty(value = "can_manage_topics")
    Optional<Boolean> canManageTopics
) 
{
    
}
