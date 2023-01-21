package com.github.donkeyrit.models.response;

/**
 * This object represents an animated emoji that displays a random value.
 * @see <a href="https://core.telegram.org/bots/api#dice">Telegram API Dice</a>
 */
public record Dice(
    /**
     * Emoji on which the dice throw animation is based
     */
    String emoji,
    /**
     * Value of the dice, 1-6 for “🎲”, “🎯” 
     * and “🎳” base emoji, 1-5 for “🏀” 
     * and “⚽” base emoji, 1-64 for “🎰” base emoji
     */
    int value
)
{
    
}
