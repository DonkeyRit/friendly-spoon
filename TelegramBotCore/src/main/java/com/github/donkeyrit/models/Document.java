package com.github.donkeyrit.models;

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
     * File size in bytes. It can be bigger than 2^31 and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this value.
     */
    //TODO: Update precisions
    Optional<Integer> fileSize
) 
{
    
}
