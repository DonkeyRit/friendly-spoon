package com.github.telegrambotstepfather.nativelibrary.api.auth.context;

import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationContext;

public class TdlibParametersContext extends AuthorizationContext {
    
    public TdlibParametersContext(int apiId, String apiHash) {
        this.apiId = apiId;
        this.apiHash = apiHash;
    }

    private final int apiId;
    private final String apiHash;


    public int getApiId() {
        return apiId;
    }
    public String getApiHash() {
        return apiHash;
    }
}