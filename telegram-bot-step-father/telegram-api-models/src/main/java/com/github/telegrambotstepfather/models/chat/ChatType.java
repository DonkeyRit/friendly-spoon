package com.github.telegrambotstepfather.models.chat;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChatType 
{
    SENDER,
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
