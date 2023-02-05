package com.github.donkeyrit.models.chat.member;

import com.github.donkeyrit.models.response.User;

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
public class ChatMember
{
    /**
     * The member's status in the chat, always “creator”
     */
    private String status;
    public String getStatus()
    {
        return this.status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    /**
     * Information about the user
     */
    private User user;
    public User getUser()
    {
        return this.user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
}
