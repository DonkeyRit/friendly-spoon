package com.github.donkeyrit.models.response.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PollType {
    REGULAR,
    QUIZ;

    @JsonCreator
    public static PollType fromString(String pollType)
    {
        return valueOf(pollType.toUpperCase());
    }
}
