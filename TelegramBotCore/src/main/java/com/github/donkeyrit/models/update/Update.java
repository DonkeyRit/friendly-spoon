package com.github.donkeyrit.models.update;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.donkeyrit.models.chat.ChatJoinRequest;
import com.github.donkeyrit.models.chat.member.ChatMemberUpdated;
import com.github.donkeyrit.models.message.Message;
import com.github.donkeyrit.models.response.CallbackQuery;
import com.github.donkeyrit.models.response.ChosenInlineResult;
import com.github.donkeyrit.models.response.InlineQuery;
import com.github.donkeyrit.models.response.Poll;
import com.github.donkeyrit.models.response.PollAnswer;
import com.github.donkeyrit.models.response.PreCheckoutQuery;
import com.github.donkeyrit.models.response.ShippingQuery;

/**
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.
 * @see <a href="https://core.telegram.org/bots/api#update">Telegram API Update</a>
 */
public record Update(
    /**
     * The update's unique identifier.
     */
    @JsonProperty(value = "update_id")
    int updateId,
    /**
     * New incoming message of any kind - text, photo, sticker, etc.
     */
    Optional<Message> message,
    /**
     * New version of a message that is known to the bot and was edited
     */
    @JsonProperty(value = "edited_message")
    Optional<Message> editedMessage,
    /**
     * New incoming channel post of any kind - text, photo, sticker, etc.
     */
    @JsonProperty(value = "channel_post")
    Optional<Message> channelPost,
    /**
     * New version of a channel post that is known to the bot and was edited
     */
    @JsonProperty(value = "edited_channel_post")
    Optional<Message> editedChannelPost,
    /**
     * New incoming inline query
     */
    @JsonProperty(value = "inline_query")
    Optional<InlineQuery> inlineQuery,
    /**
     * The result of an inline query that was chosen by a user and sent to their chat partner. 
     * Please see our documentation on the feedback collecting for details 
     * on how to enable these updates for your bot.
     */
    @JsonProperty(value = "chosen_inline_result")
    Optional<ChosenInlineResult> chosenInlineResult,
    /**
     * New incoming callback query
     */
    @JsonProperty(value = "callback_query")
    Optional<CallbackQuery> callbackQuery,
    /**
     * New incoming shipping query. Only for invoices with flexible price
     */
    @JsonProperty(value = "shipping_query")
    Optional<ShippingQuery> shippingQuery,
    /**
     * New incoming pre-checkout query. Contains full information about checkout
     */
    @JsonProperty(value = "pre_checkout_query")
    Optional<PreCheckoutQuery> preCheckoutQuery,
    /**
     * New poll state. Bots receive only updates about stopped polls and polls, which are sent by the bot 
     */ 
    Optional<Poll> poll,
    /**
     * A user changed their answer in a non-anonymous poll. 
     * Bots receive new votes only in polls that were sent by the bot itself.
     */
    @JsonProperty(value = "poll_answer")
    Optional<PollAnswer> pollAnswer,
    /**
     * The bot's chat member status was updated in a chat. 
     * For private chats, this update is received only when the bot is blocked or unblocked by the user.
     */
    @JsonProperty(value = "my_chat_member")
    Optional<ChatMemberUpdated> myChatMember,
    /**
     * A chat member's status was updated in a chat. 
     * The bot must be an administrator in the chat and must explicitly specify “chat_member” 
     * in the list of allowed_updates to receive these updates.
     */
    @JsonProperty(value = "chat_member")
    Optional<ChatMemberUpdated> chatMember,
    /**
     * A request to join the chat has been sent. 
     * The bot must have the can_invite_users administrator right in the chat to receive these updates.
     */
    @JsonProperty(value = "chat_join_request")
    Optional<ChatJoinRequest> chatJoinRequest
)
{
}
