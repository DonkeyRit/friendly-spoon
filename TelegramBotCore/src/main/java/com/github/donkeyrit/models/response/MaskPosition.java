package com.github.donkeyrit.models;

/**
 * This object describes the position on faces where a mask should be placed by default.
 * @see <a href="https://core.telegram.org/bots/api#maskposition">Telegram API MaskPosition</a>
 */
public record MaskPosition(
    /**
     * The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”, “mouth”, or “chin”.
     */
    String point,
    /**
     * Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. 
     * For example, choosing -1.0 will place mask just to the left of the default mask position.
     */
    float xShift,
    /**
     * Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. 
     * For example, 1.0 will place the mask just below the default mask position.
     */
    float yShift,
    /**
     * Mask scaling coefficient. For example, 2.0 means double size.
     */
    float scale
) 
{
    
}
