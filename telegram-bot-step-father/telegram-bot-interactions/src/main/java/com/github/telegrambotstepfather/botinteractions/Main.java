package com.github.telegrambotstepfather.botinteractions;

import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilterImpl;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String cacheFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cache.ch";

        ConsoleLogger logger = new ConsoleLogger();
        Cache cache = new FileCache(cacheFilePath);
        MessageFilter messageFilter = new MessageFilterImpl("Pumping on Binance", 0);

        try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(logger, cache)) {
            String phoneNumber = "+381611360678";
            String phoneRegion = "Serbia";
            String chatBotName = "@WhaleBot Pumps";

            telegramWebAgent.init();
            telegramWebAgent.navigate("https://web.telegram.org");
            telegramWebAgent.switchToLoginByPhone();
            telegramWebAgent.fillLoginInformation(phoneRegion, phoneNumber);

            String verificationCode = readLoginCode();
            telegramWebAgent.enterVerificationCode(verificationCode);
            
            while (true) {
                List<String> messages = telegramWebAgent.readMessagesFromSpecificChat(chatBotName, messageFilter);
                messages.forEach(System.out::println);
            }
        }catch(Exception e)
        {
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