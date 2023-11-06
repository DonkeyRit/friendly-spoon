package com.github.telegrambotstepfather.nativelibrary.api.ioc;

import com.github.telegrambotstepfather.nativelibrary.api.mediators.DefaultMediatorService;
import com.github.telegrambotstepfather.nativelibrary.api.mediators.interfaces.MediatorService;
import com.google.inject.AbstractModule;

public class TelegramNativeLibraryApiModule extends AbstractModule {
    
    @Override
    protected void configure() 
    {
        bind(MediatorService.class).to(DefaultMediatorService.class);
        //bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);
       // bind(TelegramBot.class).to(TelegramBotImpl.class);
        //bind(TelegramBotFather.class).to(TelegramBotFatherImpl.class);
    }
}
