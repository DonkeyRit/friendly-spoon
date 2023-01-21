package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * Represents a chat member that has some additional privileges.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberadministrator">Telegram API ChatMemberAdministrator</a>
 */
//TODO: Add get/set
public class ChatMemberAdministrator extends ChatMember 
{
    /**
     * True, if the bot is allowed to edit administrator privileges of that user  
     */    
    boolean canBeEdited;
    /**
     * True, if the user's presence in the chat is hidden
     */
    boolean isAnonymous;
    /**
     * True, if the administrator can access the chat event log, chat statistics, message statistics in channels, 
     * see channel members, see anonymous administrators in supergroups and ignore slow mode. 
     * Implied by any other administrator privilege
     */
    boolean canManageChat;
    /**
     * True, if the administrator can delete messages of other users
     */
    boolean canDeleteMessages;
    /**
     * True, if the administrator can manage video chats
     */
    boolean canManageVideoChats;
    /**
     * True, if the administrator can restrict, ban or unban chat members
     */
    boolean canRestrictMembers;
    /**
     * True, if the administrator can add new administrators with a subset of their own privileges 
     * or demote administrators that they have promoted, directly or indirectly 
     * (promoted by administrators that were appointed by the user)
     */
    boolean canPromoteMembers;
    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    boolean canChangeInfo;
    /**
     * True, if the user is allowed to invite new users to the chat
     */
    boolean canInviteUsers;
    /**
     * True, if the administrator can post in the channel; channels only
     */
    Optional<Boolean> canPostMessages;
    /**
     * True, if the administrator can edit messages of other users and can pin messages; channels only
     */
    Optional<Boolean> canEditMessages;
    /**
     * True, if the user is allowed to pin messages; groups and supergroups only
     */
    Optional<Boolean> canPinMessages;
    /**
     * True, if the user is allowed to create, rename, close, and reopen forum topics; supergroups only
     */
    Optional<Boolean> canManageTopics;
    /**
     * Custom title for this user
     */
    Optional<String> customTitle;
}
