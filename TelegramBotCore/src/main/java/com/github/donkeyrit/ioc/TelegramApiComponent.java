package com.github.donkeyrit.ioc;

import com.github.donkeyrit.bot.interfaces.TelegramBotFather;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = TelegramApiModule.class)
public interface TelegramApiComponent 
{
    TelegramBotFather buildTelegramBotFather();
}
