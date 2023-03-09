package com.github.telegrambotstepfather.models.response;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 * @see <a href="https://core.telegram.org/bots/api#pollanswer">Telegram API PollAnswer</a>
 */
public record PollAnswer(
    /**
     * Unique poll identifier
     */
    String pollId,
    /**
     * The user, who changed the answer to the poll
     */
    User user,
    /**
     * 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    int[] optionIds
) 
{
    
}
