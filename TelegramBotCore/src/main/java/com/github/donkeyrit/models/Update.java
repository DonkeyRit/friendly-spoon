package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents an incoming update.
 * At most one of the optional parameters can be present in any given update.
 * @see <a href="https://core.telegram.org/bots/api#update">Telegram API Update</a>
 */
public record Update(
    /**
     * The update's unique identifier.
     */
    int updateId,
    /**
     * New incoming message of any kind - text, photo, sticker, etc.
     */
    Optional<Message> message,
    /**
     * New version of a message that is known to the bot and was edited
     */
    Optional<Message> editedMessage,
    /**
     * New incoming channel post of any kind - text, photo, sticker, etc.
     */
    Optional<Message> channelPost,
    /**
     * New version of a channel post that is known to the bot and was edited
     */
    Optional<Message> editedChannelPost,
    /**
     * New incoming inline query
     */
    Optional<InlineQuery> inlineQuery,
    /**
     * The result of an inline query that was chosen by a user and sent to their chat partner. 
     * Please see our documentation on the feedback collecting for details on how to enable these updates for your bot.
     */
    Optional<ChosenInlineResult> chosenInlineResult,
    /**
     * New incoming callback query
     */
    Optional<CallbackQuery> callbackQuery,
    /**
     * New incoming shipping query. Only for invoices with flexible price
     */
    Optional<ShippingQuery> shippingQuery,
    /**
     * New incoming pre-checkout query. Contains full information about checkout
     */
    Optional<PreCheckoutQuery> preCheckoutQuery,
    /**
     * New poll state. Bots receive only updates about stopped polls and polls, which are sent by the bot 
     */ 
    Optional<Poll> poll,
    /**
     * A user changed their answer in a non-anonymous poll. 
     * Bots receive new votes only in polls that were sent by the bot itself.
     */
    Optional<PollAnswer> pollAnswer,
    /**
     * The bot's chat member status was updated in a chat. 
     * For private chats, this update is received only when the bot is blocked or unblocked by the user.
     */
    Optional<ChatMemberUpdated> myChatMember,
    /**
     * A chat member's status was updated in a chat. 
     * The bot must be an administrator in the chat and must explicitly specify “chat_member” 
     * in the list of allowed_updates to receive these updates.
     */
    Optional<ChatMemberUpdated> chatMember,
    /**
     * A request to join the chat has been sent. 
     * The bot must have the can_invite_users administrator right in the chat to receive these updates.
     */
    Optional<ChatJoinRequest> chatJoinRequest
)
{
}
