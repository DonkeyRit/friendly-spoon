package com.github.donkeyrit.models;

import java.util.Optional;

/**
 * This object represents a point on the map.
 */
public record Location(
    /**
     * Longitude as defined by sender
     */
    float longitude,
    /**
     * Latitude as defined by sender
     */
    float latitude,
    /**
     * The radius of uncertainty for the location, 
     * measured in meters; 
     * 0-1500
     */
    Optional<Float> horizontalAccuracy,
    /**
     * Time relative to the message sending date, during which the location can be updated; in seconds. 
     * For active live locations only.
     */
    Optional<Integer> livePeriod,
    /**
     * The direction in which user is moving, in degrees; 1-360. 
     * For active live locations only.
     */
    Optional<Integer> heading,
    /**
     * The maximum distance for proximity alerts about approaching another chat member, in meters. 
     * For sent live locations only.
     */
    Optional<Integer> proximityAlertRadius
) 
{
    
}
