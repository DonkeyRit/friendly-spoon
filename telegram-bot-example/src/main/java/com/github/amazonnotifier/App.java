package com.github.amazonnotifier;

import com.github.amazonnotifier.handlers.UpdateEventListener;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.bot.interfaces.TelegramBotFather;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilterImpl;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.ioc.Providers.ServiceProvider;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.response.User;
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
            // User bot = botFather.init();
            // logger.info(() -> "Telegram Bot - " + bot.firstName());

            // UpdateEventListener eventListener = new UpdateEventListener(logger,
            // injector.getInstance(TelegramBot.class));
            // botFather.getUpdatesEventSource().addListener(eventListener);

            String cacheFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cache.ch";
            String storageStatePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/state.json";

            ConsoleLogger consoleLogger = new ConsoleLogger();
            Cache cache = new FileCache(cacheFilePath);
            MessageFilter messageFilter = new MessageFilterImpl("Pumping on Binance", 0);

            try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(consoleLogger, cache)) {
                String phoneNumber = "+381611360678";
                String phoneRegion = "Serbia";
                String chatBotName = "@WhaleBot Pumps";

                boolean isAuthenticated = telegramWebAgent.init(storageStatePath);
                telegramWebAgent.navigate("https://web.telegram.org");

                if (!isAuthenticated) {
                    telegramWebAgent.switchToLoginByPhone();
                    telegramWebAgent.fillLoginInformation(phoneRegion, phoneNumber);

                    String verificationCode = readLoginCode();
                    telegramWebAgent.enterVerificationCode(verificationCode);
                }

                while (true) {
                    List<String> messages = telegramWebAgent.readMessagesFromSpecificChat(chatBotName, messageFilter);
                    messages.forEach(m -> {
                        try {
                            String escapedMessageText = m
                                .replace("!", "\\!")
                                .replace(".", "\\.")
                                .replace("(", "\\(")
                                .replace(")", "\\)");
                            telegramBot.sendMessage(SendMessageRequest.of(497848067.000000, escapedMessageText));
                        } catch (TelegramApiException | JacksonJsonParsingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readLoginCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the verification code: ");
        return scanner.nextLine();
    }
}
