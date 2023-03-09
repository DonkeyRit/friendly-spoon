package com.github.telegrambotstepfather.models.response;

import java.util.Optional;

/**
 * This object represents a general file (as opposed to photos, voice messages and audio files).
 * @see <a href="https://core.telegram.org/bots/api#document">Telegram API Document</a>
 */
public record Document(
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
     * Document thumbnail as defined by sender
     */
    Optional<PhotoSize> thumb,
    /**
     * Original filename as defined by sender
     */
    Optional<String> fileName,
    /**
     * MIME type of the file as defined by sender
     */
    Optional<String> mimeType,
    /**
     * File size in bytes. 
     * It can be bigger than 2^31.
     */
    Optional<Double> fileSize
) 
{
    
}
