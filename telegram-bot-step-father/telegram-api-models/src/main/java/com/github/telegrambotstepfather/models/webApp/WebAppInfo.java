package com.github.telegrambotstepfather.models.webApp;

/**
 * Describes a Web App.
 * @see <a href="https://core.telegram.org/bots/api#webappinfo">WebAppInfo</a>
 */
public record WebAppInfo(
    /**
     * An HTTPS URL of a Web App to be opened with additional data as specified in Initializing Web Apps
     */
    String url
)
{
    
}
