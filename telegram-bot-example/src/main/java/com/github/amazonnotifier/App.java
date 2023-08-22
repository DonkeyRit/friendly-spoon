package com.github.amazonnotifier;

import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBotFather;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.ioc.Providers.ServiceProvider;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.response.User;
import com.github.amazonnotifier.handlers.UpdateEventListener;

import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;

import com.google.inject.Injector;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, TelegramApiException, JacksonJsonParsingException {
        // String url =
        // "https://www.amazon.com/gp/product/B0BBHD5D8Y/ref=ox_sc_saved_title_1?smid=ATVPDKIKX0DER&psc=1";

        // AmazonScrapper scrapper = new AmazonScrapper();
        // scrapper.parse(url);

        try {
            Injector injector = ServiceProvider.buildServiceProvider();

            Logger logger = injector.getInstance(Logger.class);
            logger.info("Start application...");

            TelegramBotFather botFather = injector.getInstance(TelegramBotFather.class);
            TelegramBot telegramBot = injector.getInstance(TelegramBot.class);
            //User bot = botFather.init();
            ///logger.info(() -> "Telegram Bot - " + bot.firstName());

            //UpdateEventListener eventListener = new UpdateEventListener(logger,
                    //injector.getInstance(TelegramBot.class));
            //botFather.getUpdatesEventSource().addListener(eventListener);

            ConsoleLogger consoleLogger = new ConsoleLogger();
            Cache cache = new FileCache(
                    "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/target/cache.ch");

            try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(consoleLogger, cache)) {
                String phoneNumber = "+381611360678";
                String chatBotName = "@WhaleBot Pumps";

                telegramWebAgent.init();

                telegramWebAgent.switchToLoginByPhone();
                telegramWebAgent.fillLoginInformation(phoneNumber);

                String verificationCode = readLoginCode();
                telegramWebAgent.enterVerificationCode(verificationCode);

                while (true) {
                    List<String> messages = telegramWebAgent.readMessagesFromSpecificChat(chatBotName);
                    for (String message : messages) {
                        double chatId = 497848067.000000;
                        SendMessageRequest<Object> messageRequest = SendMessageRequest.of(chatId, "Test empty");
                        telegramBot.sendMessage(messageRequest);
                    }
                    
                    messages.forEach(System.out::println);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }

    }

    private static String readLoginCode()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the verification code: ");
        return scanner.nextLine();
    }
}
