package com.github.donkeyrit.models;

import com.github.donkeyrit.models.enums.ChatType;
import java.util.Optional;

/**
 * This object represents a chat.
 * @see <a href="https://core.telegram.org/bots/api#chat">Telegram API Chat</a>
 */
public record Chat(
    /**
     * Unique identifier for this user or bot. 
     * This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent defects in interpreting it.
     * But it has at most 52 significant bits, so a 64-bit integer or double-precision float type
     * are safe for storing this identifier.
     */
    int id,
    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    // TODO: Implement supporting parsing enum
    ChatType type,
    /**
     * Title, for supergroups, channels and group chats
     */
    Optional<String> title,
    /**
     * Username, for private chats, supergroups and channels if available
     */
    Optional<String> username,
    /**
     * First name of the other party in a private chat
     */
    Optional<String> firstName,
    /**
     * Last name of the other party in a private chat
     */
    Optional<String> lastName,
    /**
     * True, if the supergroup chat is a forum (has topics enabled)
     */
    Optional<Boolean> isForum,
    /**
     * Chat photo. Returned only in getChat.
     */
    Optional<ChatPhoto> photo,
    /**
     * If non-empty, the list of all active chat usernames; 
     * for private chats, supergroups and channels. 
     * Returned only in getChat.
     */
    Optional<String[]> activeUsernames,
    /**
     * Custom emoji identifier of emoji status of the other party in a private chat. 
     * Returned only in getChat.
     */
    Optional<String> emojiStatusCustomEmojiId,
    /**
     * Bio of the other party in a private chat. 
     * Returned only in getChat.
     */
    Optional<String> bio,
    /**
     * True, if privacy settings of the other party in the private chat 
     * allows to use tg://user?id=<user_id> links only in chats with the user. 
     * Returned only in getChat.
     */
    Optional<Boolean> hasPrivateForwards,
    /**
     * rue, if the privacy settings of the other party restrict sending voice and video note messages in the private chat. Returned only in getChat.
     */
    Optional<Boolean> hasRestrictedVoiceAndVideoMessages,
    /**
     * True, if users need to join the supergroup before they can send messages. Returned only in getChat.
     */
    Optional<Boolean> joinToSendMessages,
    /**
     * True, if all users directly joining the supergroup need to be approved by supergroup administrators. 
     * Returned only in getChat.
     */
    Optional<Boolean> joinByRequest,
    /**
     * Description, for groups, supergroups and channel chats. Returned only in getChat.
     */
    Optional<String> description,
    /**
     * Primary invite link, for groups, supergroups and channel chats. Returned only in getChat.
     */
    Optional<String> inviteLink,
    /**
     * The most recent pinned message (by sending date). 
     * Returned only in getChat.
     */
    Optional<Message> pinnedMessage,
    /**
     * Default chat member permissions, for groups and supergroups. Returned only in getChat.
     */
    Optional<ChatPermissions> permissions,
    /**
     * For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user; 
     * in seconds. 
     * Returned only in getChat.
     */
    Optional<Integer> slowModeDelay,
    /**
     * The time after which all messages sent to the chat will be automatically deleted; in seconds. 
     * Returned only in getChat.
     */
    Optional<Integer> messageAutoDeleteTime,
    /**
     * True, if aggressive anti-spam checks are enabled in the supergroup. 
     * The field is only available to chat administrators. 
     * Returned only in getChat.
     */
    Optional<Boolean> hasAggressiveAntiSpamEnabled,
    /**
     * True, if non-administrators can only get the list of bots and administrators in the chat. 
     * Returned only in getChat.
     */
    Optional<Boolean> hasHiddenMembers,
    /**
     * True, if messages from the chat can't be forwarded to other chats. 
     * Returned only in getChat.
     */
    Optional<Boolean> hasProtectedContent,
    /**
     * For supergroups, name of group sticker set. 
     * Returned only in getChat.
     */
    Optional<String> stickerSetName,
    /**
     * True, if the bot can change the group sticker set. 
     * Returned only in getChat.
     */
    Optional<Boolean> canSetStickerSet,
    /**
     * Unique identifier for the linked chat, i.e. the discussion group identifier for a channel and vice versa; 
     * for supergroups and channel chats. 
     * This identifier may be greater than 32 bits and some programming languages may have difficulty/silent defects 
     * in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type 
     * are safe for storing this identifier. 
     * Returned only in getChat.
     */
    Optional<Integer> linkedChatId,
    /**
     * For supergroups, the location to which the supergroup is connected. 
     * Returned only in getChat.
     */
    Optional<ChatLocation> location
) 
{
    
}
