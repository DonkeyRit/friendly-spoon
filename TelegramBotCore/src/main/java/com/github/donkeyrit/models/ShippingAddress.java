package com.github.donkeyrit.models;

/**
 * This object represents a shipping address.
 * @see <a href="https://core.telegram.org/bots/api#shippingaddress">Telegram API ShippingAddress</a>
 */
public record ShippingAddress(
    /**
     * Two-letter ISO 3166-1 alpha-2 country code
     */
    String countryCode,
    /**
     * State, if applicable
     */
    String state,
    /**
     * City
     */
    String city,
    /**
     * First line for the address
     */
    String streetLine1,
    /**
     * Second line for the address
     */
    String streetLine2,
    /**
     * Address post code
     */
    String postCode
) 
{
    
}
