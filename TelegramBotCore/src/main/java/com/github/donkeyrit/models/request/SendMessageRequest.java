package com.github.donkeyrit.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.donkeyrit.models.message.MessageEntity;

import java.util.Optional;


/**
 * Use this method to send text messages. 
 * On success, the sent Message is returned.
 * @see <a href="https://core.telegram.org/bots/api#sendmessage">Telegram API sendMessage</a>
 */
public record SendMessageRequest<T>(
    /**
     * Unique identifier for the target chat or username of the target channel 
     * (in the format @channelusername)
     */
    @JsonProperty(value = "chat_id")
    String chatId,
    /**
     * Unique identifier for the target message thread (topic) of the forum; 
     * for forum supergroups only
     */
    @JsonProperty(value = "message_thread_id")
    Optional<Integer> messageThreadId,
    /**
     * Text of the message to be sent, 1-4096 characters after entities parsing
     */
    String text,
    /**
     * Mode for parsing entities in the message text. 
     * @see <a href="https://core.telegram.org/bots/api#formatting-options">Formatting options</a>
     */
    @JsonProperty(value = "parse_mode")
    String parseMode,
    /** 
     * A JSON-serialized list of special entities that appear in message text, 
     * which can be specified instead of parse_mode
    */
    Optional<MessageEntity> entities,
    /**
     * Disables link previews for links in this message
     */
    @JsonProperty(value = "disable_web_page_preview")
    Optional<Boolean> disableWebPagePreview,
    /**
     * Sends the message silently. 
     * Users will receive a notification with no sound.
     */
    @JsonProperty(value = "disable_notification")
    Optional<Boolean> disableNotification,
    /**
     * Protects the contents of the sent message from forwarding and saving
     */
    @JsonProperty(value = "protect_content")
    Optional<Boolean> protectContent,
    /**
     * If the message is a reply, ID of the original message
     */
    @JsonProperty(value = "reply_to_message_id")
    Optional<Integer> replyToMessageId,
    /**
     * Pass True if the message should be sent even if the specified replied-to message is not found
     */
    @JsonProperty(value = "allow_sending_without_reply")
    Optional<Boolean> allowSendingWithoutReply,
    /**
     * Additional interface options. 
     * A JSON-serialized object for an 
     *  - inline keyboard, 
     *  - custom reply keyboard, 
     *  - instructions to remove reply keyboard 
     *  - or to force a reply from the user.
     */
    Optional<T> reply_markup
)
{
    
}
