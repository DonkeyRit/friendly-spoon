package com.github.donkeyrit.models.keyboard.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.donkeyrit.models.keyboard.buttons.inline.InlineKeyboardButton;

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardmarkup">InlineKeyboardMarkup</a>
 */
public record InlineKeyboardMarkup(
    /**
     * Array of button rows, each represented by an Array of InlineKeyboardButton objects
     */
    @JsonProperty(value = "inline_keyboard")
    InlineKeyboardButton[] inlineKeyboard
) 
{
    
}
