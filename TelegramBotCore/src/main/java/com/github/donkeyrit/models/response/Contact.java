package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a phone contact.
 * @see <a href="https://core.telegram.org/bots/api#contact">Telegram API Contact</a>
 */
public record Contact(
    /**
     * Contact's phone number
     */
    String phoneNumber,
    /**
     * Contact's first name
     */
    String firstName,
    /**
     * Contact's last name
     */
    Optional<String> lastName,
    /**
     * Contact's user identifier in Telegram. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    Optional<Integer> userId,
    /**
     * Additional data about the contact in the form of a vCard
     */
    Optional<String> vCard
)
{
    
}
