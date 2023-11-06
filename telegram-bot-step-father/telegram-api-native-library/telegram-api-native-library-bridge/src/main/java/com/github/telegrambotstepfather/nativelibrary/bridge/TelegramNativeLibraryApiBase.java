package com.github.telegrambotstepfather.nativelibrary.bridge;

import com.github.telegrambotstepfather.nativelibrary.api.clients.interfaces.TelegramNativeLibraryClient;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.AuthorizationCodeContext;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.TdlibParametersContext;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.PhoneNumberContext;
import com.github.telegrambotstepfather.nativelibrary.api.models.enums.TelegramNativeLibraryEvent;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.handlers.AuthorizationHandler;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class TelegramNativeLibraryApiBase implements Runnable {

    private final TelegramNativeLibraryClient client;
    private final MediatorService mediator;

    public TelegramNativeLibraryApiBase(TelegramNativeLibraryClient client) {
        this.client = client;
        this.mediator = client.getMediatorService();
    }

    @Override
    public final void run() {
        this.mediator.registerHandler(
            TelegramNativeLibraryEvent.WAIT_TDLIB_PARAMETERS,
            new AuthorizationHandler<TdlibParametersContext>(this.client, this::provideTelegramLibraryParameters));
        this.mediator.registerHandler(
            TelegramNativeLibraryEvent.WAIT_PHONE_NUMBER, 
            new AuthorizationHandler<PhoneNumberContext>(this.client, this::providPhoneNumber));
        this.mediator.registerHandler(
            TelegramNativeLibraryEvent.WAIT_AUTHORIZATION_CODE, 
            new AuthorizationHandler<AuthorizationCodeContext>(this.client, this::provideAuthorizationCode));
        this.client.initialize();
    }

    protected TdlibParametersContext provideTelegramLibraryParameters() {
        return new TdlibParametersContext(94575, "a3406de8d171bb422bb6ddf3bbd800e2");
    }

    protected PhoneNumberContext providPhoneNumber() {
        return new PhoneNumberContext("381628120111");
    }

    protected AuthorizationCodeContext provideAuthorizationCode() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AuthorizationCodeContext(str);
    }
}
