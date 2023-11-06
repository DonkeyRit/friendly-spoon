package com.github.telegrambotstepfather.nativelibrary.api.auth.context;

import com.github.telegrambotstepfather.nativelibrary.api.auth.interfaces.AuthorizationContext;

public class PhoneNumberContext extends AuthorizationContext {
    
    public PhoneNumberContext(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private final String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
