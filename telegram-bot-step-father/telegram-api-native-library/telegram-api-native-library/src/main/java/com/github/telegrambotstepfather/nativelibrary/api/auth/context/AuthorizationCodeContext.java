package com.github.telegrambotstepfather.nativelibrary.api.auth.context;

import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationContext;

public class AuthorizationCodeContext extends AuthorizationContext {
    
    public AuthorizationCodeContext(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    private final String authorizationCode;

    public String getAuthorizationCode() {
        return authorizationCode;
    }
}
