package com.github.donkeyrit.models.response;

import java.util.Optional;

import com.github.donkeyrit.models.response.enums.MessageEntityType;

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 * @see <a href="https://core.telegram.org/bots/api#messageentity">Telegram API MessageEntity/a>
 */
public record MessageEntity(
    /**
     * Type of the entity. Currently, can be “mention” (@username), “hashtag” (#hashtag), “cashtag” ($USD), 
     * “bot_command” (/start@jobs_bot), “url” (https://telegram.org), “email” (do-not-reply@telegram.org), 
     * “phone_number” (+1-212-555-0123), “bold” (bold text), “italic” (italic text), 
     * “underline” (underlined text), “strikethrough” (strikethrough text), “spoiler” (spoiler message), 
     * “code” (monowidth string), “pre” (monowidth block), “text_link” (for clickable text URLs), 
     * “text_mention” (for users without usernames), “custom_emoji” (for inline custom emoji stickers)
     */
    MessageEntityType type,
    /**
     * Offset in UTF-16 code units to the start of the entity
     */
    int offset,
    /**
     * Length of the entity in UTF-16 code units
     */
    int length,
    /**
     * For “text_link” only, URL that will be opened after user taps on the text
     */
    Optional<String> url,
    /**
     * For “text_mention” only, the mentioned user
     */
    Optional<User> user,
    /**
     * For “pre” only, the programming language of the entity text
     */
    Optional<String> language,
    /**
     * For “custom_emoji” only, unique identifier of the custom emoji. 
     * Use getCustomEmojiStickers to get full information about the sticker
     */
    Optional<String> customEmojiId
) 
{
    
}
