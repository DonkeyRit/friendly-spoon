package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a message.
 * @see <a href="https://core.telegram.org/bots/api#message">Telegram API Message</a>
 */
public record Message(
    /**
     * Unique message identifier inside this chat
     */
    int messageId,
    /**
     * Unique identifier of a message thread to which the message belongs; for supergroups only
     */
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
    Optional<Chat> senderChat,
    /**
     * Date the message was sent in Unix time
     */
    // TODO: Convert to real datetime
    int date,
    /**
     * Conversation the message belongs to
     */
    Chat chat,
    /**
     * For forwarded messages, sender of the original message
     */
    Optional<User> forwardFrom,
    /**
     * For messages forwarded from channels or from anonymous administrators, information about the original sender chat
     */
    Optional<Chat> forwardFromChat,
    /**
     * For messages forwarded from channels, identifier of the original message in the channel
     */
    Optional<Integer> forwardFromMessageId,
    /**
     * For forwarded messages that were originally sent in channels or by an anonymous chat administrator, 
     * signature of the message sender if present
     */
    Optional<String> forwardSignature,
    /**
     * Sender's name for messages forwarded from users who disallow adding a link to their account in forwarded messages
     */
    Optional<String> forwardSenderName,
    /**
     * For forwarded messages, date the original message was sent in Unix time
     */
    // TODO: Convert to real datetime
    Optional<Integer> forwardDate,
    /**
     * True, if the message is sent to a forum topic
     */
    Optional<Boolean> isTopicMessage,
    /**
     * True, if the message is a channel post that was automatically forwarded to the connected discussion group
     */
    Optional<Boolean> isAutomaticForward,
    /**
     * For replies, the original message. 
     * Note that the Message object in this field will not contain further reply_to_message fields 
     * even if it itself is a reply.
     */
    Optional<Message> replyToMessage,
    /**
     * Bot through which the message was sent
     */
    Optional<User> viaBot,
    /**
     * Date the message was last edited in Unix time
     */
    // TODO: Convert to real datetime
    Optional<Integer> editDate,
    /**
     * True, if the message can't be forwarded
     */
    Optional<Boolean> hasProtectedContent,
    /**
     * The unique identifier of a media message group this message belongs to
     */
    Optional<String> mediaGroupId,
    /**
     * Signature of the post author for messages in channels, or the custom title of an anonymous group administrator
     */
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
    Optional<Sticker> sticker
) 
{

}
