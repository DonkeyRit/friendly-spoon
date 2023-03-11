package com.github.telegrambotstepfather.models.response;

import java.util.Optional;

import com.github.telegrambotstepfather.models.message.MessageEntity;
import com.github.telegrambotstepfather.models.response.enums.PollType;

/**
 * This object contains information about a poll.
 * @see <a href="https://core.telegram.org/bots/api#poll">Telegram API Poll</a>
 */
public record Poll(
    /**
     * Unique poll identifier
     */
    String id,
    /**
     * Poll question, 1-300 characters
     */
    String question,
    /**
     * List of poll options
     */
    PollOption[] options,
    /**
     * Total number of users that voted in the poll
     */
    int totalVoterCount,
    /**
     * True, if the poll is closed
     */
    boolean isClosed,
    /**
     * True, if the poll is anonymous
     */
    boolean isAnonymous,
    /**
     * Poll type, currently can be “regular” or “quiz”
     */
    PollType type,
    /**
     * True, if the poll allows multiple answers
     */
    boolean allowsMultipleAnswers,
    /**
     * 0-based identifier of the correct answer option. Available only for polls in the quiz mode, 
     * which are closed, or was sent (not forwarded) by the bot or to the private chat with the bot.
     */
    Optional<Integer> correctOptionId,
    /**
     * Text that is shown when a user chooses an incorrect answer or taps on the lamp icon in a quiz-style poll, 
     * 0-200 characters
     */
    Optional<String> explanation,
    /**
     * Special entities like usernames, URLs, bot commands, etc. that appear in the explanation
     */
    Optional<MessageEntity[]> explanationEntities,
    /**
     * Amount of time in seconds the poll will be active after creation
     */
    Optional<Integer> openPeriod,
    /**
     * Point in time (Unix timestamp) when the poll will be automatically closed
     */
    Optional<Integer> closeDate
) 
{
    
}
