package com.github.telegrambotstepfather.botinteractions;

import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilterImpl;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;
import com.github.telegrambotstepfather.botinteractions.models.ChatMessage;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {

        String cacheFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cache.ch";
        Optional<String> storageStatePath = Optional.of(
            "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/state.json"
        );

        ConsoleLogger logger = new ConsoleLogger();
        Cache cache = new FileCache(cacheFilePath);
        MessageFilter messageFilter = new MessageFilterImpl("Pumping on Binance", 0);

        try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(logger, cache)) {
            String phoneNumber = "+381628120111";
            String phoneRegion = "Serbia";
            String chatBotName = "@whalebotpumps";

            boolean isAuthenticated = telegramWebAgent.init(storageStatePath);
            telegramWebAgent.navigate("https://web.telegram.org");

            if (!isAuthenticated) {
                telegramWebAgent.switchToLoginByPhone();
                telegramWebAgent.fillLoginInformation(phoneRegion, phoneNumber);

                String verificationCode = readLoginCode();
                telegramWebAgent.enterVerificationCode(verificationCode, storageStatePath);
            }


            List<ChatMessage> messages = telegramWebAgent
                    .readMessagesFromSpecificChat(chatBotName)
                    .stream()
                    .filter(m -> messageFilter.filter(m.innerText()))
                    .toList();
            messages.forEach(System.out::println);
            Thread.sleep(10000);


            while (true) {
                messages = telegramWebAgent
                    .readMessagesFromOpenedChat()
                    .stream()
                    .filter(m -> messageFilter.filter(m.innerText()))
                    .toList();
                messages.forEach(System.out::println);
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readLoginCode() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the verification code: ");
            return scanner.nextLine();
        }
    }
}