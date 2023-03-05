package com.github.donkeyrit.models.response;

import java.util.Optional;

/**
 * This object represents a venue.
 * @see <a href="https://core.telegram.org/bots/api#venue">Telegram API Venue</a>
 */
public record Venue(
    /**
     * Venue location. Can't be a live location
     */
    Location location,
    /**
     * Name of the venue
     */
    String title,
    /**
     * Address of the venue
     */
    String address,
    /**
     * Foursquare identifier of the venue
     */
    Optional<String> foursquareId,
    /**
     * Foursquare type of the venue. 
     * (For example, “arts_entertainment/default”, “arts_entertainment/aquarium” or “food/icecream”.)
     */
    Optional<String> foursquareType,
    /**
     * Google Places identifier of the venue
     */
    Optional<String> googlePlaceId,
    /**
     * Google Places type of the venue. (See supported types.)
     */
    //TODO: Use enums
    Optional<String> googlePlaceType
) 
{
    
}
