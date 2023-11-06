package com.github.telegrambotstepfather.nativelibrary.bridge.example;

import com.github.telegrambotstepfather.nativelibrary.api.clients.DefaultTelegramNativeLibraryClient;
import com.github.telegrambotstepfather.nativelibrary.api.clients.interfaces.TelegramNativeLibraryClient;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.DefaultMediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.github.telegrambotstepfather.nativelibrary.bridge.TelegramNativeLibraryApiBase;

public class TelegramNativeLibraryExample {

    public static void main(String[] args) {
        MediatorService mediatorService = new DefaultMediatorService();
        TelegramNativeLibraryClient client = new DefaultTelegramNativeLibraryClient(mediatorService);
        TelegramNativeLibraryApiBase nativeLibraryApi = new TelegramNativeLibraryApiExample(client);
        nativeLibraryApi.run();
    }

    public static class TelegramNativeLibraryApiExample extends TelegramNativeLibraryApiBase {

        public TelegramNativeLibraryApiExample(TelegramNativeLibraryClient client) {
            super(client);
        }

        
    }
    
}
