package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents an audio file to be treated as music by the Telegram clients.
 * @see <a href="https://core.telegram.org/bots/api#audio">Telegram API Audio/a>
 */
public record Audio(
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
     * Duration of the audio in seconds as defined by sender
     */
    int duration,
    /**
     * Performer of the audio as defined by sender or by audio tags
     */
    Optional<String> performer,
    /**
     * Title of the audio as defined by sender or by audio tags
     */
    Optional<String> title,
    /**
     * Original filename as defined by sender
     */
    Optional<String> fileName,
    /**
     * MIME type of the file as defined by sender
     */
    Optional<String> mimeType,
    /**
     * File size in bytes. It can be bigger than 2^31 and some programming languages 
     * may have difficulty/silent defects in interpreting it. 
     * But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type 
     * are safe for storing this value.
     */
    //TODO: Update precision
    Optional<Integer> fileSize,
    /**
     * Thumbnail of the album cover to which the music file belongs
     */
    Optional<PhotoSize> thumb
) 
{
    
}
