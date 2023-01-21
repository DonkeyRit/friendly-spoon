package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * This object represents a game. Use BotFather to create and edit games, 
 * their short names will act as unique identifiers.
 * @see <a href="https://core.telegram.org/bots/api#game">Telegram API Game</a>
 */
public record Game(
    /**
     * Title of the game
     */
    String title,
    /**
     * Description of the game
     */
    String description,
    /**
     * Photo that will be displayed in the game message in chats.
     */
    PhotoSize[] photo,
    /**
     * Brief description of the game or high scores included in the game message. 
     * Can be automatically edited to include current high scores for the game 
     * when the bot calls setGameScore, or manually edited using editMessageText. 
     * 0-4096 characters.
     */
    Optional<String> text,
    /**
     * Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     */
    Optional<MessageEntity[]> textEntities,
    /**
     * Animation that will be displayed in the game message in chats. 
     * Upload via BotFather
     */
    Optional<Animation> animation
)
{
    
}
