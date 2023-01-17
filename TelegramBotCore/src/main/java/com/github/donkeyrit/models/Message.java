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
    Optional<Sticker> sticker,
    /**
     * Message is a video, information about the video
     */
    Optional<Video> video,
    /**
     * Message is a video note, information about the video message
     */
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
    Optional<MessageEntity[]> captionEntities,
    /**
     * True, if the message media is covered by a spoiler animation
     */
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
     * Message is a game, information about the game. More about games »
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
     * New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
     */
    Optional<User[]> newChatMembers,
    /**
     * A member was removed from the group, information about them (this member may be the bot itself)
     */
    Optional<User> leftChatMember,
    /**
     * A chat title was changed to this value
     */
    Optional<String> newChatTitle,
    /**
     * A chat photo was change to this value
     */
    Optional<PhotoSize[]> newChatPhoto,
    /**
     * Service message: the chat photo was deleted
     */
    Optional<Boolean> deleteChatPhoto,
    /**
     * Service message: the group has been created
     */
    Optional<Boolean> groupChatCreated,
    /**
     * Service message: the supergroup has been created. 
     * This field can't be received in a message coming through updates, 
     * because bot can't be a member of a supergroup when it is created. 
     * It can only be found in reply_to_message if someone replies to a very first message 
     * in a directly created supergroup.
     */
    Optional<Boolean> supergroupChatCreated,
    /**
     * Service message: the channel has been created. 
     * This field can't be received in a message coming through updates, 
     * because bot can't be a member of a channel when it is created. 
     * It can only be found in reply_to_message if someone replies to a very first message in a channel.
     */
    Optional<Boolean> channelChatCreated,
    /**
     * Service message: auto-delete timer settings changed in the chat
     */
    Optional<MessageAutoDeleteTimerChanged> messageAutoDeleteTimerChanged,
    /**
     * The group has been migrated to a supergroup with the specified identifier. 
     * This number may have more than 32 significant bits and some programming languages 
     * may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, 
     * so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    //TODO: Update precisions
    Optional<Integer> migrateToChatId,
    /**
     * The supergroup has been migrated from a group with the specified identifier. 
     * This number may have more than 32 significant bits and some programming languages 
     * may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, 
     * so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    //TODO: Update precisions
    Optional<Integer> migrateFromChatId,
    /**
     * Specified message was pinned. Note that the Message object in this field will not contain 
     * further reply_to_message fields even if it is itself a reply.
     */
    Optional<Message> pinnedMessage
) 
{

}
