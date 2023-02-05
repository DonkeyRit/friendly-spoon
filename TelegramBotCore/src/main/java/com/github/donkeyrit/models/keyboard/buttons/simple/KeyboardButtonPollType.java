package com.github.donkeyrit.models.keyboard.buttons.simple;

/**
 * This object represents type of a poll, 
 * which is allowed to be created and sent when the corresponding button is pressed.
 * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonpolltype">KeyboardButtonPollType</a>
 */
public record KeyboardButtonPollType(
    /**
     * If quiz is passed, the user will be allowed to create only polls in the quiz mode. 
     * If regular is passed, only regular polls will be allowed. 
     * Otherwise, the user will be allowed to create a poll of any type.
     */
    String type
) 
{
    
}
