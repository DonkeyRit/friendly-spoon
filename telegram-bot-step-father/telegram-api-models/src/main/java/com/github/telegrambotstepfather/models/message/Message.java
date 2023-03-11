package com.github.telegrambotstepfather.models.message;

import com.github.telegrambotstepfather.utils.Deserializers.UnixTimestampOptionalInstantDeserializer;
import com.github.telegrambotstepfather.models.chat.Chat;
import com.github.telegrambotstepfather.models.game.Game;
import com.github.telegrambotstepfather.models.response.Animation;
import com.github.telegrambotstepfather.models.response.Audio;
import com.github.telegrambotstepfather.models.response.Contact;
import com.github.telegrambotstepfather.models.response.Dice;
import com.github.telegrambotstepfather.models.response.Document;
import com.github.telegrambotstepfather.models.response.Invoice;
import com.github.telegrambotstepfather.models.response.Location;
import com.github.telegrambotstepfather.models.response.PhotoSize;
import com.github.telegrambotstepfather.models.response.Poll;
import com.github.telegrambotstepfather.models.response.Sticker;
import com.github.telegrambotstepfather.models.response.User;
import com.github.telegrambotstepfather.models.response.Venue;
import com.github.telegrambotstepfather.models.response.Video;
import com.github.telegrambotstepfather.models.response.VideoNote;
import com.github.telegrambotstepfather.models.response.Voice;
import com.github.telegrambotstepfather.utils.Deserializers.UnixTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.time.Instant;

/**
 * This object represents a message.
 * @see <a href="https://core.telegram.org/bots/api#message">Telegram API Message</a>
 */
public record Message(
    /**
     * Unique message identifier inside this chat
     */
    @JsonProperty(value = "message_id")
    int messageId,
    /**
     * Unique identifier of a message thread to which the message belongs; for supergroups only
     */
    @JsonProperty(value = "message_thread_id")
    Optional<Integer> messageThreadId,
    /**
     * Sender of the message; empty for messages sent to channels. 
     * For backward compatibility, the field contains a fake sender user in non-channel chats, 
     * if the message was sent on behalf of a chat.
     */
    Optional<User> from,
    /**
     * Sender of the message, sent on behalf of a chat. For example, the channel itself for channel posts, 
     * the supergroup itself for messages from anonymous group administrators, 
     * the linked channel for messages automatically forwarded to the discussion group. 
     * For backward compatibility, the field from contains a fake sender user in non-channel chats, 
     * if the message was sent on behalf of a chat.
     */
    @JsonProperty(value = "sender_chat")
    Optional<Chat> senderChat,
    /**
     * Date the message was sent in Unix time
     */
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    Instant date,
    /**
     * Conversation the message belongs to
     */
    Chat chat,
    /**
     * For forwarded messages, sender of the original message
     */
    @JsonProperty(value = "forward_from")
    Optional<User> forwardFrom,
    /**
     * For messages forwarded from channels or from anonymous administrators, 
     * information about the original sender chat
     */
    @JsonProperty(value = "forward_from_chat")
    Optional<Chat> forwardFromChat,
    /**
     * For messages forwarded from channels, identifier of the original message in the channel
     */
    @JsonProperty(value = "forward_from_message_id")
    Optional<Integer> forwardFromMessageId,
    /**
     * For forwarded messages that were originally sent in channels or by an anonymous chat administrator, 
     * signature of the message sender if present
     */
    @JsonProperty(value = "forward_signature")
    Optional<String> forwardSignature,
    /**
     * Sender's name for messages forwarded from users who disallow adding a link 
     * to their account in forwarded messages
     */
    @JsonProperty(value = "forward_sender_name")
    Optional<String> forwardSenderName,
    /**
     * For forwarded messages, date the original message was sent in Unix time
     */
    @JsonDeserialize(using = UnixTimestampOptionalInstantDeserializer.class)
    Optional<Instant> forwardDate,
    /**
     * True, if the message is sent to a forum topic
     */
    @JsonProperty(value = "is_topic_Message")
    Optional<Boolean> isTopicMessage,
    /**
     * True, if the message is a channel post that was automatically forwarded to the connected discussion group
     */
    @JsonProperty(value = "is_automatic_forward")
    Optional<Boolean> isAutomaticForward,
    /**
     * For replies, the original message. 
     * Note that the Message object in this field will not contain further reply_to_message fields 
     * even if it itself is a reply.
     */
    @JsonProperty(value = "reply_to_message,")
    Optional<Message> replyToMessage,
    /**
     * Bot through which the message was sent
     */
    @JsonProperty(value = "via_bot")
    Optional<User> viaBot,
    /**
     * Date the message was last edited in Unix time
     */
    @JsonProperty(value = "edit_date")
    Optional<Integer> editDate,
    /**
     * True, if the message can't be forwarded
     */
    @JsonProperty(value = "has_protected_content")
    Optional<Boolean> hasProtectedContent,
    /**
     * The unique identifier of a media message group this message belongs to
     */
    @JsonProperty(value = "media_group_id")
    Optional<String> mediaGroupId,
    /**
     * Signature of the post author for messages in channels, or the custom title of an anonymous group administrator
     */
    @JsonProperty(value = "author_signature")
    Optional<String> authorSignature,
    /**
     * For text messages, the actual UTF-8 text of the message
     */
    Optional<String> text,
    /**
     * For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
     */
    Optional<MessageEntity[]> entities,
    /**
     * Message is an animation, information about the animation. 
     * For backward compatibility, when this field is set, the document field will also be set
     */
    Optional<Animation> animation,
    /**
     * Message is an audio file, information about the file
     */
    Optional<Audio> audio,
    /**
     * Message is a general file, information about the file
     */
    Optional<Document> document,
    /**
     * Message is a photo, available sizes of the photo
     */
    Optional<PhotoSize[]> photo,
    /**
     * Message is a sticker, information about the sticker
     */
    Optional<Sticker> sticker,
    /**
     * Message is a video, information about the video
     */
    Optional<Video> video,
    /**
     * Message is a video note, information about the video message
     */
    @JsonProperty(value = "video_note")
    Optional<VideoNote> videoNote,
    /**
     * Message is a voice message, information about the file
     */
    Optional<Voice> voice,
    /**
     * Caption for the animation, audio, document, photo, video or voice
     */
    Optional<String> caption,
    /**
     * For messages with a caption, special entities like usernames, URLs, bot commands, etc. 
     * that appear in the caption
     */
    @JsonProperty(value = "caption_entities")
    Optional<MessageEntity[]> captionEntities,
    /**
     * True, if the message media is covered by a spoiler animation
     */
    @JsonProperty(value = "has_media_spoiler")
    Optional<Boolean> hasMediaSpoiler,
    /**
     * Message is a shared contact, information about the contact
     */
    Optional<Contact> contact,
    /**
     * Message is a dice with random value
     */
    Optional<Dice> dice,
    /**
     * Message is a game, information about the game.
     */
    Optional<Game> game,
    /**
     * Message is a native poll, information about the poll
     */
    Optional<Poll> poll,
    /**
     * Message is a venue, information about the venue. 
     * For backward compatibility, when this field is set, the location field will also be set
     */
    Optional<Venue> venue,
    /**
     * Message is a shared location, information about the location
     */
    Optional<Location> location,
    /**
     * New members that were added to the group or supergroup and information about them 
     * (the bot itself may be one of these members)
     */
    @JsonProperty(value = "new_chat_members")
    Optional<User[]> newChatMembers,
    /**
     * A member was removed from the group, information about them (this member may be the bot itself)
     */
    @JsonProperty(value = "left_chat_member")
    Optional<User> leftChatMember,
    /**
     * A chat title was changed to this value
     */
    @JsonProperty(value = "new_chat_title")
    Optional<String> newChatTitle,
    /**
     * A chat photo was change to this value
     */
    @JsonProperty(value = "new_chat_photo")
    Optional<PhotoSize[]> newChatPhoto,
    /**
     * Service message: the chat photo was deleted
     */
    @JsonProperty(value = "delete_chat_photo")
    Optional<Boolean> deleteChatPhoto,
    /**
     * Service message: the group has been created
     */
    @JsonProperty(value = "group_chat_created")
    Optional<Boolean> groupChatCreated,
    /**
     * Service message: the supergroup has been created. 
     * This field can't be received in a message coming through updates, 
     * because bot can't be a member of a supergroup when it is created. 
     * It can only be found in reply_to_message if someone replies to a very first message 
     * in a directly created supergroup.
     */
    @JsonProperty(value = "supergroup_chat_created")
    Optional<Boolean> supergroupChatCreated,
    /**
     * Service message: the channel has been created. 
     * This field can't be received in a message coming through updates, 
     * because bot can't be a member of a channel when it is created. 
     * It can only be found in reply_to_message if someone replies to a very first message in a channel.
     */
    @JsonProperty(value = "channel_chat_created")
    Optional<Boolean> channelChatCreated,
    /**
     * Service message: auto-delete timer settings changed in the chat
     */
    @JsonProperty(value = "message_auto_delete_timer_changed")
    Optional<MessageAutoDeleteTimerChanged> messageAutoDeleteTimerChanged,
    /**
     * The group has been migrated to a supergroup with the specified identifier. 
     * This number may have more than 32.
     */
    @JsonProperty(value = "migrate_to_chat_id")
    Optional<Double> migrateToChatId,
    /**
     * The supergroup has been migrated from a group with the specified identifier. 
     * This number may have more than 32.
     */
    @JsonProperty(value = "migrate_from_chat_id")
    Optional<Double> migrateFromChatId,
    /**
     * Specified message was pinned. Note that the Message object in this field will not contain 
     * further reply_to_message fields even if it is itself a reply.
     */
    @JsonProperty(value = "pinned_message")
    Optional<Message> pinnedMessage,
    /**
     * Message is an invoice for a payment, information about the invoice. 
     * More about payments »
     */
    Optional<Invoice> invoice
) 
{

}
