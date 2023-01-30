package com.github.donkeyrit.models.response.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StickerType {
    REGULAR, 
    MASK, 
    CUSTOM_EMOJI;

    @JsonCreator
    public static StickerType fromString(String stickerType)
    {
        return valueOf(stickerType.toUpperCase());
    }
}
