package com.github.donkeyrit.models.keyboard.buttons.simple;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * This object defines the criteria used to request a suitable user. 
 * The identifier of the selected user will be shared with the bot when the corresponding button is pressed.
 * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonrequestuser">KeyboardButtonRequestUser</a>
 */
public record KeyboardButtonRequestUser(
    /**
     * Signed 32-bit identifier of the request, which will be received back in the UserShared object. 
     * Must be unique within the message.
     */
    @JsonProperty(value = "request_id")
    int requestId,
    /**
     * Pass True to request a bot, pass False to request a regular user. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "user_is_bot")
    Optional<Boolean> userIsBot,
    /**
     * Pass True to request a premium user, pass False to request a non-premium user. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "user_is_premium")
    Optional<Boolean> userIsPremium
) 
{
    
}
