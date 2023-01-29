package com.github.donkeyrit.models.response.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChatType 
{
    PRIVATE,
    GROUP,
    SUPERGROUP, 
    CHANNEL;

    @JsonCreator
    public static ChatType fromString(String chatType)
    {
        return valueOf(chatType.toUpperCase());
    }
}
