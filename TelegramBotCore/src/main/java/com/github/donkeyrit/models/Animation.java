package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents an animation file (GIF or H.264/MPEG-4 AVC video without sound).
 * @see <a href="https://core.telegram.org/bots/api#animation">Telegram API Animation</a>
 */
public record Animation(
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
     * Animation thumbnail as defined by sender
     */
    Optional<PhotoSize> thumb,
    /**
     * Original animation filename as defined by sender
     */
    Optional<String> fileName,
    /**
     * MIME type of the file as defined by sender
     */
    Optional<String> mimeType,
    /**
     * File size in bytes. It can be bigger than 2^31 and some programming languages 
     * may have difficulty/silent defects in interpreting it. 
     * But it has at most 52 significant bits, 
     * so a signed 64-bit integer or double-precision float type 
     * are safe for storing this value.
     */
    //TODO: Update precision
    Optional<Integer> fileSize
) 
{
    
}
