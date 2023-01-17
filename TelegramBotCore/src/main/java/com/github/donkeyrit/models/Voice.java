package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a voice note.
 * @see <a href="https://core.telegram.org/bots/api#voice">Telegram API Voice</a>
 */
public record Voice(
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
    Optional<Integer> fileSize
) 
{
    
}
