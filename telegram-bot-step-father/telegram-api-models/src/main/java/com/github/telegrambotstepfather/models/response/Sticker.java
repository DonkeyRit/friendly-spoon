package com.github.telegrambotstepfather.models.response;

import com.github.telegrambotstepfather.models.response.enums.StickerType;
import java.util.Optional;

/**
 * This object represents a sticker.
 * @see <a href="https://core.telegram.org/bots/api#sticker">Telegram API Sticker</a>
 */
public record Sticker(
    /**
     * Identifier for this file, which can be used to download or reuse the file
     */
    String fileId,
    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. 
     * Can't be used to download or reuse the file.
     */
    String fileUniqueId,
    /**
     * Type of the sticker, currently one of “regular”, “mask”, “custom_emoji”. 
     * The type of the sticker is independent from its format, 
     * which is determined by the fields is_animated and is_video.
     */
    StickerType type,
    /**
     * Sticker width
     */
    int width,
    /**
     * Sticker height
     */
    int height,
    /**
     * True, if the sticker is animated
     */
    boolean isAnimated,
    /**
     * True, if the sticker is a video sticker
     */
    boolean isVideo,
    /**
     * Sticker thumbnail in the .WEBP or .JPG format
     */
    Optional<PhotoSize> thumb,
    /**
     * Emoji associated with the sticker
     */
    Optional<String> emoji,
    /**
     * Name of the sticker set to which the sticker belongs
     */
    Optional<String> setName,
    /**
     * For premium regular stickers, premium animation for the sticker
     */
    Optional<File> premiumAnimation,
    /**
     * For mask stickers, the position where the mask should be placed
     */
    Optional<MaskPosition> maskPosition,
    /**
     * For custom emoji stickers, unique identifier of the custom emoji
     */
    Optional<String> customEmojiId,
    /**
     * File size in bytes
     */
    Optional<Integer> fileSize
) 
{
    
}
