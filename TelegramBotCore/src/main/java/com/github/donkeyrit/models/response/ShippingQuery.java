package com.github.donkeyrit.models.response;

/**
 * This object contains information about an incoming shipping query.
 * @see <a href="https://core.telegram.org/bots/api#shippingquery">Telegram API ShippingQuery</a>
 */
public record ShippingQuery(
    /**
     * Unique query identifier
     */
    String id,
    /**
     * User who sent the query
     */
    User from,
    /**
     * Bot specified invoice payload
     */
    String invoicePayload,
    /**
     * User specified shipping address
     */
    ShippingAddress shippingAddress
) 
{
    
}
