package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * This object represents information about an order.
 * @see <a href="https://core.telegram.org/bots/api#orderinfo">Telegram API OrderInfo</a>
 */
public record OrderInfo(
    /**
     * User name
     */
    Optional<String> name,
    /**
     * User's phone number
     */
    Optional<String> phoneNumber,
    /**
     * User email
     */
    Optional<String> email,
    /**
     * User shipping address
     */
    Optional<ShippingAddress> shippingAddress
) 
{
    
}
