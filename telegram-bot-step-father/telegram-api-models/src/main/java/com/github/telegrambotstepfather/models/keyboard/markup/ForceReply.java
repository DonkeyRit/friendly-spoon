package com.github.telegrambotstepfather.models.keyboard.markup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * Upon receiving a message with this object, 
 * Telegram clients will display a reply interface to the user 
 * (act as if the user has selected the bot's message and tapped 'Reply'). 
 * This can be extremely useful if you want to create user-friendly step-by-step interfaces 
 * without having to sacrifice privacy mode.
 * @see <a href="https://core.telegram.org/bots/api#forcereply">ForceReply</a>
 */
public record ForceReply(
    /**
     * Shows reply interface to the user, as if they manually selected the bot's message and tapped 'Reply'
     */
    @JsonProperty(value = "force_reply")
    boolean forceReply,
    /**
     * The placeholder to be shown in the input field when the reply is active; 1-64 characters
     */
    @JsonProperty(value = "input_field_placeholder")
    Optional<String> inputFieldPlaceholder,
    /**
     * Use this parameter if you want to force reply from specific users only. 
     * 
     * Targets: 
     *      1) users that are @mentioned in the text of the Message object; 
     *      2) if the bot's message is a reply (has reply_to_message_id), sender of the original message.
     */
    Optional<Boolean> selective
) 
{
    
}
