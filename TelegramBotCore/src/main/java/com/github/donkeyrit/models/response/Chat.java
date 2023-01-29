package com.github.donkeyrit.models.response;

import com.github.donkeyrit.models.response.enums.ChatType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

/**
 * This object represents a chat.
 * @see <a href="https://core.telegram.org/bots/api#chat">Telegram API Chat</a>
 */
public record Chat(
    /**
     * Unique identifier for this user or bot. 
     * This number may have more than 32 significant bits.
     */
    double id,
    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
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
    @JsonProperty(value = "first_name")
    Optional<String> firstName,
    /**
     * Last name of the other party in a private chat
     */
    @JsonProperty(value = "last_name")
    Optional<String> lastName,
    /**
     * True, if the supergroup chat is a forum (has topics enabled)
     */
    @JsonProperty(value = "is_forum")
    Optional<Boolean> isForum,
    /**
     * Chat photo. 
     * @see TelegramBot#getChat getChat
     */
    Optional<ChatPhoto> photo,
    /**
     * If non-empty, the list of all active chat usernames; for private chats, supergroups and channels. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "active_usernames")
    Optional<String[]> activeUsernames,
    /**
     * Custom emoji identifier of emoji status of the other party in a private chat. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "emoji_status_custom_emoji_id")
    Optional<String> emojiStatusCustomEmojiId,
    /**
     * Bio of the other party in a private chat. 
     * @see TelegramBot#getChat getChat
     */
    Optional<String> bio,
    /**
     * True, if privacy settings of the other party in the private chat 
     * allows to use tg://user?id=<user_id> links only in chats with the user. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "has_private_forwards")
    Optional<Boolean> hasPrivateForwards,
    /**
     * True, if the privacy settings of the other party restrict sending voice 
     * and video note messages in the private chat. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "has_restricted_voice_and_video_messages")
    Optional<Boolean> hasRestrictedVoiceAndVideoMessages,
    /**
     * True, if users need to join the supergroup before they can send messages. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "join_to_send_messages")
    Optional<Boolean> joinToSendMessages,
    /**
     * True, if all users directly joining the supergroup need to be approved by supergroup administrators. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "join_by_request")
    Optional<Boolean> joinByRequest,
    /**
     * Description, for groups, supergroups and channel chats. 
     * @see TelegramBot#getChat getChat
     */
    Optional<String> description,
    /**
     * Primary invite link, for groups, supergroups and channel chats. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "invite_link")
    Optional<String> inviteLink,
    /**
     * The most recent pinned message (by sending date). 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "pinned_message")
    Optional<Message> pinnedMessage,
    /**
     * Default chat member permissions, for groups and supergroups. 
     * @see TelegramBot#getChat getChat
     */
    Optional<ChatPermissions> permissions,
    /**
     * For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user; in seconds. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "slow_mode_delay")
    Optional<Integer> slowModeDelay,
    /**
     * The time after which all messages sent to the chat will be automatically deleted; in seconds. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "message_auto_delete_time")
    Optional<Integer> messageAutoDeleteTime,
    /**
     * True, if aggressive anti-spam checks are enabled in the supergroup. 
     * The field is only available to chat administrators. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "has_aggressive_anti_spam_enabled")
    Optional<Boolean> hasAggressiveAntiSpamEnabled,
    /**
     * True, if non-administrators can only get the list of bots and administrators in the chat. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "has_hidden_members")
    Optional<Boolean> hasHiddenMembers,
    /**
     * True, if messages from the chat can't be forwarded to other chats. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "has_protected_content")
    Optional<Boolean> hasProtectedContent,
    /**
     * For supergroups, name of group sticker set. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "sticker_set_name")
    Optional<String> stickerSetName,
    /**
     * True, if the bot can change the group sticker set. 
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "can_set_sticker_set")
    Optional<Boolean> canSetStickerSet,
    /**
     * Unique identifier for the linked chat, i.e. the discussion group identifier for a channel and vice versa; 
     * for supergroups and channel chats. 
     * This identifier may be greater than 32 bits.
     * @see TelegramBot#getChat getChat
     */
    @JsonProperty(value = "linked_chat_id")
    Optional<Double> linkedChatId,
    /**
     * For supergroups, the location to which the supergroup is connected. 
     * @see TelegramBot#getChat getChat
     */
    Optional<ChatLocation> location
) 
{
    
}
