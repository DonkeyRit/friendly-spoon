package com.github.donkeyrit.settings;

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
