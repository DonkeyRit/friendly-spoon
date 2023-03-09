package com.github.telegrambotstepfather.models.response;

import java.util.Optional;

/**
 * This object represents a video file.
 * @see <a href="https://core.telegram.org/bots/api#video">Telegram API Video</a>
 */
public record Video(
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
     * Video width as defined by sender
     */
    int width,
    /**
     * Video height as defined by sender
     */
    int height,
    /**
     * Duration of the video in seconds as defined by sender
     */
    int duration,
    /**
     * Video thumbnail
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
