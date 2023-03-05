package com.github.donkeyrit.models.keyboard.buttons.inline;

import com.github.donkeyrit.models.LoginUrl;
import com.github.donkeyrit.models.game.CallbackGame;
import com.github.donkeyrit.models.webApp.WebAppInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

/**
 * This object represents one button of an inline keyboard. 
 * You must use exactly one of the optional fields.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardbutton">InlineKeyboardButton</a>
 */
public record InlineKeyboardButton(
    /**
     * Label text on the button
     */
    String text,
    /**
     * HTTP or tg:// URL to be opened when the button is pressed. 
     * Links tg://user?id=<user_id> can be used to mention a user by their ID without using a username, 
     * if this is allowed by their privacy settings.
     */
    Optional<String> url,
    /**
     * Data to be sent in a callback query to the bot when button is pressed, 1-64 bytes
     */
    @JsonProperty(value = "callback_data")
    Optional<String> callbackData,
    /**
     * Description of the Web App that will be launched when the user presses the button. 
     * The Web App will be able to send an arbitrary message on behalf of the user using the method answerWebAppQuery. 
     * Available only in private chats between a user and the bot.
     */
    @JsonProperty(value = "web_app")
    Optional<WebAppInfo> webApp,
    /**
     * An HTTPS URL used to automatically authorize the user. 
     * Can be used as a replacement for the Telegram Login Widget.
     */
    @JsonProperty(value = "login_url")
    Optional<LoginUrl> loginUrl,
    /**
     * If set, pressing the button will prompt the user to select one of their chats, 
     * open that chat and insert the bot's username and the specified inline query in the input field. 
     * May be empty, in which case just the bot's username will be inserted.
     * 
     * Note: 
     * This offers an easy way for users to start using your bot in inline mode when they are currently 
     * in a private chat with it. Especially useful when combined with switch_pm… actions - in this case 
     * the user will be automatically returned to the chat they switched from, 
     * skipping the chat selection screen.
     */
    @JsonProperty(value = "switch_inline_query")
    Optional<String> switchInlineQuery,
    /**
     * If set, pressing the button will insert the bot's username and the specified inline query 
     * in the current chat's input field. 
     * May be empty, in which case only the bot's username will be inserted.
     * 
     * Note:
     * This offers a quick way for the user to open your bot in inline mode in the same chat - 
     * good for selecting something from multiple options.
     */
    @JsonProperty(value = "switch_inline_query_current_chat")
    Optional<String> switchInlineQueryCurrentChat,
    /**
     * Description of the game that will be launched when the user presses the button.
     * 
     * Note: 
     * This type of button must always be the first button in the first row.
     */
    @JsonProperty(value = "callback_game")
    Optional<CallbackGame> callbackGame,
    /**
     * Specify True, to send a Pay button.
     * 
     * Note:
     * This type of button must always be the first button in the first row and can only be used in invoice messages.
     */
    Optional<Boolean> pay  
) 
{
    
}
