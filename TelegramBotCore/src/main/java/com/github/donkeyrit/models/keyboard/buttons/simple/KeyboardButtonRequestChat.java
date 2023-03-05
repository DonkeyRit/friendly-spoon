package com.github.donkeyrit.models.keyboard.buttons.simple;

import com.github.donkeyrit.models.chat.permissions.ChatAdministratorRights;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * This object defines the criteria used to request a suitable chat. 
 * The identifier of the selected chat will be shared with the bot when the corresponding button is pressed.
 * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonrequestchat">KeyboardButtonRequestChat</a>
 */
public record KeyboardButtonRequestChat(
    /**
     * Signed 32-bit identifier of the request, which will be received back in the ChatShared object. 
     * Must be unique within the message
     */
    @JsonProperty(value = "request_id")
    int requestId,
    /**
     * Pass True to request a channel chat, pass False to request a group or a supergroup chat.
     */
    @JsonProperty(value = "chat_is_channel")
    Optional<Boolean> chatIsChannel,
    /**
     * Pass True to request a forum supergroup, pass False to request a non-forum chat. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "chat_is_forum")
    Optional<Boolean> chatIsForum,
    /**
     * Pass True to request a supergroup or a channel with a username, pass False to request a chat without a username. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "chat_has_username")
    Optional<Boolean> chatHasUsername,
    /**
     * Pass True to request a chat owned by the user. 
     * Otherwise, no additional restrictions are applied.
     */
    @JsonProperty(value = "chat_is_created")
    Optional<Boolean> chatIsCreated,
    /**
     * A JSON-serialized object listing the required administrator rights of the user in the chat. 
     * The rights must be a superset of bot_administrator_rights. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "user_administrator_rights")
    Optional<ChatAdministratorRights> userAdministratorRights,
    /**
     * A JSON-serialized object listing the required administrator rights of the bot in the chat. 
     * The rights must be a subset of user_administrator_rights. 
     * If not specified, no additional restrictions are applied.
     */
    @JsonProperty(value = "bot_administrator_rights")
    Optional<ChatAdministratorRights> botAdministratorRights,
    /**
     * Pass True to request a chat with the bot as a member. 
     * Otherwise, no additional restrictions are applied.
     */
    @JsonProperty(value = "bot_is_member")
    Optional<Boolean> botIsMember
) 
{
    
}
