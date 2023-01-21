package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * Represents a chat member that is under certain restrictions in the chat. Supergroups only.
 * @see <a href="https://core.telegram.org/bots/api#chatmemberrestricted">Telegram API ChatMemberRestricted</a>
 */
//TODO: Add get/set
public class ChatMemberRestricted extends ChatMember
{
    /**
     * True, if the user is a member of the chat at the moment of the request
     */
    boolean isMember;
    /**
     * True, if the user is allowed to change the chat title, photo and other settings
     */
    boolean canChangeInfo;
    /**
     * True, if the user is allowed to invite new users to the chat
     */
    boolean canInviteUsers;
    /**
     * True, if the user is allowed to pin messages
     */
    boolean canPinMessages;
    /**
     * True, if the user is allowed to create forum topics
     */
    boolean canManageTopics;
    /**
     * True, if the user is allowed to send text messages, contacts, locations and venues
     */
    boolean canSendMessages;
    /**
     * True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes
     */
    boolean canSendMediaMessages;
    /**
     * True, if the user is allowed to send polls
     */
    boolean canSendPolls;
    /**
     * True, if the user is allowed to send animations, games, stickers and use inline bots
     */
    boolean canSendOtherMessages;
    /**
     * True, if the user is allowed to add web page previews to their messages
     */
    boolean canAddWebPagePreviews;
    /**
     * Date when restrictions will be lifted for this user; unix time. If 0, then the user is restricted forever
     */
    //TODO: Use date
    int until_date;
}
