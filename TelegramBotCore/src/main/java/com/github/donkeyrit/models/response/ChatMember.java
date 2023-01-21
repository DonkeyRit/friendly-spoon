package com.github.donkeyrit.models;

/**
 * This object contains information about one member of a chat. 
 * Currently, the following 6 types of chat members are supported:
 * ChatMemberOwner
 * ChatMemberAdministrator
 * ChatMemberMember
 * ChatMemberRestricted
 * ChatMemberLeft
 * ChatMemberBanned
 * @see <a href="https://core.telegram.org/bots/api#chatmember">Telegram API ChatMember</a>
 */
//TODO: Work with polymorphisms
public class ChatMember
{
    /**
     * The member's status in the chat, always “creator”
     */
    private String status;
    /**
     * Information about the user
     */
    private User user;
    
}
