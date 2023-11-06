package com.github.telegrambotstepfather.nativelibrary.api.auth.enums;

import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitTdlibParameters;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitPhoneNumber;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.AuthorizationStateWaitCode;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.SetAuthenticationPhoneNumber;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.CheckAuthenticationCode;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.SetTdlibParameters;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.AuthorizationCodeContext;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.PhoneNumberContext;
import com.github.telegrambotstepfather.nativelibrary.api.auth.context.TdlibParametersContext;
import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationAction;
import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationContext;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Function;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Ok;

import java.util.Optional;

public enum AuthorizationStateAction {

    WAIT_TDLIB_PARAMETERS(AuthorizationStateWaitTdlibParameters.CONSTRUCTOR,
            (context) -> {
                TdlibParametersContext ctx = (TdlibParametersContext) context;
                SetTdlibParameters request = new SetTdlibParameters();
                request.databaseDirectory = "tdlib";
                request.useMessageDatabase = true;
                request.useSecretChats = true;
                request.apiId = ctx.getApiId(); // 94575;
                request.apiHash = ctx.getApiHash(); // "a3406de8d171bb422bb6ddf3bbd800e2";
                request.systemLanguageCode = "en";
                request.deviceModel = "Desktop";
                request.applicationVersion = "1.0";
                request.enableStorageOptimizer = true;

                return request;
            }),
    WAIT_PHONE_NUMBER(AuthorizationStateWaitPhoneNumber.CONSTRUCTOR,
            (context) -> {
                PhoneNumberContext ctx = (PhoneNumberContext) context;
                return new SetAuthenticationPhoneNumber(ctx.getPhoneNumber(), null);
            }),
    WAIT_AUTHORIZATION_CODE(AuthorizationStateWaitCode.CONSTRUCTOR,
            (context) -> {
                AuthorizationCodeContext ctx = (AuthorizationCodeContext) context;
                return new CheckAuthenticationCode(ctx.getAuthorizationCode());
            });

    private final long constructorId;
    private final AuthorizationAction action;

    AuthorizationStateAction(int constructorId, AuthorizationAction action) {
        this.constructorId = constructorId;
        this.action = action;
    }

    public static Optional<AuthorizationStateAction> getByConstructorId(long constructorId) {
        for (AuthorizationStateAction instance : AuthorizationStateAction.values()) {
            if (instance.constructorId == constructorId) {
                System.out.println("Action with constructorId - " + constructorId);
                return Optional.of(instance);
            }
        }
        System.out.println("Couldn't find action with constructorId - " + constructorId);
        return Optional.empty();
    }

    public Function<Ok> performAction(AuthorizationContext context) {
        return this.action.apply(context);
    }
}
