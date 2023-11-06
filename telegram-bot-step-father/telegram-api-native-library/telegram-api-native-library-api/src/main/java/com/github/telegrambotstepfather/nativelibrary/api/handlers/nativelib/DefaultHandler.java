package com.github.telegrambotstepfather.nativelibrary.api.handlers.nativelib;

import com.github.telegrambotstepfather.nativelibrary.api.Client;
import com.github.telegrambotstepfather.nativelibrary.api.TdApi;

public class DefaultHandler implements Client.ResultHandler {
    @Override
    public void onResult(TdApi.Object object) {
        System.out.println("");
        System.out.println(object.toString());
        System.out.println("");
    }
}
