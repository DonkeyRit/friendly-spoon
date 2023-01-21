package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * Describes actions that a non-administrator user is allowed to take in a chat.
 * @see <a href="https://core.telegram.org/bots/api#chatpermissions">Telegram API ChatPermissions</a>
 */
public record ChatPermissions(
    /**
     * True, if the user is allowed to send text messages, contacts, locations and venues
     */
    Optional<Boolean> canSendMessages,
    /**
     * True, if the user is allowed to send audios, documents, 
     * photos, videos, video notes and voice notes, implies can_send_messages
     */
    Optional<Boolean> canSendMediaMessages,
    /**
     * True, if the user is allowed to send polls, implies can_send_messages
     */
    Optional<Boolean> canSendPolls,
    /**
     * True, if the user is allowed to send animations, games, 
     * stickers and use inline bots, implies can_send_media_messages
     */
    Optional<Boolean> canSendOtherMessages,
    /**
     * True, if the user is allowed to add web page previews to their messages, 
     * implies can_send_media_messages
     */
    Optional<Boolean> canAddWebPagePreviews,
    /**
     * True, if the user is allowed to change the chat title, photo and other settings. 
     * Ignored in public supergroups
     */
    Optional<Boolean> canChangeInfo,
    /**
     * True, if the user is allowed to invite new users to the chat
     */
    Optional<Boolean> canInviteUsers,
    /**
     * True, if the user is allowed to pin messages. Ignored in public supergroups
     */
    Optional<Boolean> canPinMessages,
    /**
     * True, if the user is allowed to create forum topics. 
     * If omitted defaults to the value of can_pin_messages
     */
    Optional<Boolean> canManageTopics
) 
{
    
}
