package com.github.donkeyrit.models.response;

/**
 * This object contains information about one answer option in a poll.
 * @see <a href="https://core.telegram.org/bots/api#polloption">Telegram API PollOption</a>
 */
public record PollOption(
    /**
     * Option text, 1-100 characters
     */
    String text,
    /**
     * Number of users that voted for this option
     */
    int voterCount
) 
{
    
}
