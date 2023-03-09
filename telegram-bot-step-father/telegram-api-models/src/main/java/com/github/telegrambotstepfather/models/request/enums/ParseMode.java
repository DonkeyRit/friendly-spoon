package com.github.telegrambotstepfather.models.request.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ParseMode 
{
    MarkdownV2,
    HTML,
    Markdown;

    @JsonCreator
    public static ParseMode fromString(String parseMode)
    {
        return valueOf(parseMode.toUpperCase());
    }
}
