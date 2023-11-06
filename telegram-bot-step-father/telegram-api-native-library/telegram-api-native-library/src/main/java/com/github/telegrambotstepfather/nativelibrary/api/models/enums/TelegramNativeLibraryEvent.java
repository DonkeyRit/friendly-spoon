package com.github.telegrambotstepfather.nativelibrary.api.models.enums;

import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitTdlibParameters;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitPhoneNumber;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitCode;

public enum TelegramNativeLibraryEvent {

    WAIT_TDLIB_PARAMETERS(AuthorizationStateWaitTdlibParameters.CONSTRUCTOR),
    WAIT_PHONE_NUMBER(AuthorizationStateWaitPhoneNumber.CONSTRUCTOR),
    WAIT_AUTHORIZATION_CODE(AuthorizationStateWaitCode.CONSTRUCTOR);

    private final int constructorId;

    private TelegramNativeLibraryEvent(int constructorId) {
        this.constructorId = constructorId;
    }

    public static TelegramNativeLibraryEvent fromEventConstructorId(int constructorId) {
        for (TelegramNativeLibraryEvent event : TelegramNativeLibraryEvent.values()) {
            if (event.constructorId == constructorId) {
                return event;
            }
        }

        return null;
    }
}
