package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * This object contains information about an incoming pre-checkout query.
 * @see <a href="https://core.telegram.org/bots/api#precheckoutquery">Telegram API PreCheckoutQuery</a>
 */
public record PreCheckoutQuery(
    /**
     * Unique query identifier
     */
    String id,
    /**
     * User who sent the query
     */
    User from,
    /**
     * Three-letter ISO 4217 currency code
     */
    String currency,
    /**
     * Total price in the smallest units of the currency (integer, not float/double). 
     * For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, 
     * it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    int totalAmount,
    /**
     * Bot specified invoice payload
     */
    String invoicePayload,
    /**
     * Identifier of the shipping option chosen by the user
     */
    Optional<String> shippingOptionId,
    /**
     * Order information provided by the user
     */
    Optional<OrderInfo> orderInfo
) 
{
    
}
