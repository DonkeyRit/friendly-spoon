package com.github.amazonnotifier;

import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBotFather;
import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.ioc.Providers.ServiceProvider;
import com.github.telegrambotstepfather.models.response.User;
import com.github.amazonnotifier.handlers.UpdateEventListener;
import com.github.amazonnotifier.site.AmazonScrapper;
import com.google.inject.Injector;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;

public class App {

    public static void main(String[] args) throws IOException, TelegramApiException, JacksonJsonParsingException, InterruptedException {
        String url =
        "https://www.amazon.com/gp/product/B0BBHD5D8Y/ref=ox_sc_saved_title_1?smid=ATVPDKIKX0DER&psc=1";

        AmazonScrapper scrapper = new AmazonScrapper();
        scrapper.parse(url, "");

        try {
            Injector injector = ServiceProvider.buildServiceProvider();

            InputStream inputStream = App.class
            .getClassLoader()
            .getResourceAsStream("config.properties");

            Logger logger = injector.getInstance(Logger.class);
            logger.info("Start application...");

            TelegramBotFather botFather = injector.getInstance(TelegramBotFather.class);
            User bot = botFather.init();
            logger.info(() -> "Telegram Bot - " + bot.firstName());

            UpdateEventListener eventListener = new UpdateEventListener(logger,
                    injector.getInstance(TelegramBot.class));
            botFather.getUpdatesEventSource().addListener(eventListener);

        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }

    }
}
