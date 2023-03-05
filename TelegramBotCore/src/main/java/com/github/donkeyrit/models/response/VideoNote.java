package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * This object represents a video message (available in Telegram apps as of v.4.0).
 * @see <a href="https://core.telegram.org/bots/api#videonote">Telegram API VideoNote</a>
 */
public record VideoNote(
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
     * Video width and height (diameter of the video message) as defined by sender
     */
    int length,
    /**
     * Duration of the video in seconds as defined by sender
     */
    int duration,
    /**
     * Video thumbnail
     */
    Optional<PhotoSize> thumb,
    /**
     * File size in bytes
     */
    int fileSize
) 
{
    
}
