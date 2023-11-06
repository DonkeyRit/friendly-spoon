package com.github.telegrambotstepfather.nativelibrary.api.handlers;

import com.github.telegrambotstepfather.nativelibrary.api.clients.interfaces.TelegramNativeLibraryClient;
import com.github.telegrambotstepfather.nativelibrary.api.events.interfaces.TelegramEventHandler;
import com.github.telegrambotstepfather.nativelibrary.api.auth.enums.AuthorizationStateAction;
import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationContext;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.UpdateAuthorizationState;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationState;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Function;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Object;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Ok;

import java.util.function.Supplier;
import java.util.Optional;

public class AuthorizationHandler<T extends AuthorizationContext> implements TelegramEventHandler {

    private final TelegramNativeLibraryClient client;
    private final Supplier<T> supplier;

    public AuthorizationHandler(TelegramNativeLibraryClient client, Supplier<T> supplier) {
        this.client = client;
        this.supplier = supplier;
    }

    @Override
    public void handle(Object event) {
        AuthorizationContext context = supplier.get();
        AuthorizationState authorizationState = ((UpdateAuthorizationState) event).authorizationState;
        Optional<AuthorizationStateAction> action = AuthorizationStateAction.getByConstructorId(authorizationState.getConstructor());
        
        action.ifPresent(a -> {
            Function<Ok> func = a.performAction(context);
            this.client.sendRequest(func);
        });
    }
    
}
