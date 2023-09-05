package com.github.amazonnotifier;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.telegrambotstepfather.bot.interfaces.TelegramBot;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilterImpl;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;
import com.github.telegrambotstepfather.botinteractions.models.ChatMessage;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.github.telegrambotstepfather.exceptions.JacksonJsonParsingException;
import com.github.telegrambotstepfather.exceptions.TelegramApiException;
import com.github.telegrambotstepfather.ioc.Providers.ServiceProvider;
import com.github.telegrambotstepfather.models.message.MessageEntity;
import com.github.telegrambotstepfather.models.request.SendMessageRequest;
import com.github.telegrambotstepfather.models.request.enums.ParseMode;
import com.google.inject.Injector;

public class App {

    public static void main(String[] args) throws IOException, TelegramApiException, JacksonJsonParsingException {
        try {
            Injector injector = ServiceProvider.buildServiceProvider();

            Logger logger = injector.getInstance(Logger.class);
            logger.info("Start application...");

            TelegramBot telegramBot = injector.getInstance(TelegramBot.class);
            String cacheFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cache.ch";
            String storageStatePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/state.json";
            String chatBotName = "@WhaleBot Pumps";

            ConsoleLogger consoleLogger = new ConsoleLogger();
            Cache cache = new FileCache(cacheFilePath);
            MessageFilter messageFilter = new MessageFilterImpl("Pumping on Binance", 0);

            try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(consoleLogger, cache)) {

                telegramWebAgent.init(Optional.of(storageStatePath));
                telegramWebAgent.navigate("https://web.telegram.org");

                while (true) {

                    List<String> messages = telegramWebAgent
                            .readMessagesFromSpecificChat(chatBotName)
                            .stream()
                            .filter(m -> messageFilter.filter(m.innerText()))
                            .map(App::convertTelegramMessage)
                            .toList();
                    messages.forEach(m -> sendTelegramMessage(telegramBot, m));
                    Thread.sleep(Duration.ofMinutes(1));

                    while (true) {
                        messages = telegramWebAgent
                                .readMessagesFromOpenedChat()
                                .stream()
                                .filter(m -> messageFilter.filter(m.innerText()))
                                .map(App::convertTelegramMessage)
                                .toList();
                        messages.forEach(m -> sendTelegramMessage(telegramBot, m));
                        Thread.sleep(Duration.ofMinutes(1));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendTelegramMessage(TelegramBot telegramBot, String message) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String stringChatId = decimalFormat.format(6597283274.0000);
        SendMessageRequest<Object> sendMessageRequest = new SendMessageRequest<Object>(stringChatId,
                (Optional<Integer>) null, message,
                Optional.of(ParseMode.HTML), (Optional<MessageEntity>) null,
                (Optional<Boolean>) null,
                (Optional<Boolean>) null, (Optional<Boolean>) null, (Optional<Integer>) null,
                (Optional<Boolean>) null,
                (Optional<Object>) null);

        try {
            telegramBot.sendMessage(sendMessageRequest);
        } catch (TelegramApiException | JacksonJsonParsingException e) {
            e.printStackTrace();
        }
    }

    private static String convertTelegramMessage(ChatMessage chatMessage){
        Pattern pattern = Pattern.compile("(<a [^>]*>([^<]*)</a>)");
        Matcher matcher = pattern.matcher(chatMessage.innerHtml());

        while (matcher.find()) {
            String fullLink = matcher.group(1);
            String linkText = matcher.group(2);

            if (chatMessage.innerText().contains(linkText)) {
                String resultMessage = chatMessage.innerText().replace(linkText, fullLink);
                System.out.println(resultMessage);
                return resultMessage;
            }
        }

        return chatMessage.innerText();
    }
}
