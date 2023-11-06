package com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces;

import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Function;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi.Ok;

@FunctionalInterface
public interface AuthorizationAction {
    Function<Ok> apply(AuthorizationContext context);
}
