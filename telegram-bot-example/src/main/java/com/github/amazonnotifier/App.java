package com.github.amazonnotifier;

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
import com.github.telegrambotstepfather.models.message.MessageEntity;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.request.enums.ParseMode;
import com.google.inject.Injector;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;

public class App {

    public static void main(String[] args) throws IOException, TelegramApiException, JacksonJsonParsingException {
        try {
            Injector injector = ServiceProvider.buildServiceProvider();

            Logger logger = injector.getInstance(Logger.class);
            logger.info("Start application...");

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

                            String message = m
                                .replace("class=\"emoji\"", "class=\"tg-spoiler\"");


                            String escapedMessageText = m
                                    .replace("!", "\\!")
                                    .replace(".", "\\.")
                                    .replace("(", "\\(")
                                    .replace(")", "\\)")
                                    .replace("=", "\\=")
                                    .replace(">", "\\>")
                                    .replace("<", "\\<")
                                    .replace("-", "\\-");

                            DecimalFormat decimalFormat = new DecimalFormat("#.###");
                            String stringChatId = decimalFormat.format(497848067.000000);
                            SendMessageRequest<Object> sendMessageRequest = new SendMessageRequest<Object>(stringChatId, (Optional<Integer>) null, message,
                                    Optional.of(ParseMode.HTML), (Optional<MessageEntity>) null, (Optional<Boolean>) null,
                                    (Optional<Boolean>) null, (Optional<Boolean>) null, (Optional<Integer>) null, (Optional<Boolean>) null,
                                    (Optional<Object>) null);

                            telegramBot.sendMessage(sendMessageRequest);
                        } catch (TelegramApiException | JacksonJsonParsingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    });
                    Thread.sleep(Duration.ofMinutes(3));
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
