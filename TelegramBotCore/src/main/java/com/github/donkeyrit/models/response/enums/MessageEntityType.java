package com.github.donkeyrit.models.response.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MessageEntityType 
{

    MENTION,
    HASHTAG,
    CASHTAG,
    BOT_COMMAND,
    URL,
    EMAIL,
    PHONE_NUMBER,
    BOLD,
    ITALIC,
    UNDERLINE,
    STRIKETHROUGH,
    SPOILER,
    CODE,
    PRE,
    TEXT_LINK,
    TEXT_MENTION,
    CUSTOM_EMOJI;

    @JsonCreator
    public static MessageEntityType fromString(String messageEntityType)
    {
        return valueOf(messageEntityType.toUpperCase());
    }
}
