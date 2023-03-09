package com.github.telegrambotstepfather.models.response;

/**
 * This object represents one size of a photo or a file / sticker thumbnail.
 * @see <a href="https://core.telegram.org/bots/api#photosize">Telegram API PhotoSize</a>
 */
public record PhotoSize(
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
     * Photo width
     */
    int width,
    /**
     * Photo height
     */
    int height,
    /**
     * File size in bytes
     */
    int fileSize
)
{
    
}
