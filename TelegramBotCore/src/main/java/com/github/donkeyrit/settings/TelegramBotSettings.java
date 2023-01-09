package com.github.donkeyrit.settings;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public record TelegramBotSettings(
    long id,
    String firstName,
    String username,
    boolean canJoinGroups,
    boolean can_read_all_group_messages,
    boolean supports_inline_queries
)
{
}
