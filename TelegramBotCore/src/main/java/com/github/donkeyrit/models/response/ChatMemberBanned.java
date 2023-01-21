package com.github.donkeyrit.models;

/**
 * Represents a chat member that was banned in the chat and can't return to the chat or view chat messages.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberbanned">Telegram API ChatMemberBanned</a>
 */
//TODO: Add get/set
public class ChatMemberBanned extends ChatMember
{
    /**
     * Date when restrictions will be lifted for this user; unix time. If 0, then the user is banned forever
     */
    //TODO: Use date
    int until_date;
}
