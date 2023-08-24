package com.github.telegrambotstepfather.botinteractions;

import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgent;
import com.github.telegrambotstepfather.botinteractions.agent.TelegramWebAgentImpl;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilterImpl;
import com.github.telegrambotstepfather.botinteractions.logger.ConsoleLogger;
import com.github.telegrambotstepfather.botinteractions.persistance.BrowserCookieCache;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.persistance.FileCache;
import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        String cookiesFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cookies.json";
        String cacheFilePath = "/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/cache.ch";

        ConsoleLogger logger = new ConsoleLogger();
        Cache cache = new FileCache(cacheFilePath);
        BrowserCookieCache browserCookieCache = new BrowserCookieCache(cookiesFilePath);
        MessageFilter messageFilter = new MessageFilterImpl("Pumping on Binance", 0);

        try (TelegramWebAgent telegramWebAgent = new TelegramWebAgentImpl(logger, cache)) {
            String phoneNumber = "+381611360678";
            String phoneRegion = "Serbia";
            String chatBotName = "@WhaleBot Pumps";

            telegramWebAgent.init();
            telegramWebAgent.switchToLoginByPhone();
            telegramWebAgent.fillLoginInformation(phoneRegion, phoneNumber);

            String verificationCode = readLoginCode();
            telegramWebAgent.enterVerificationCode(verificationCode);
            
            while (true) {
                List<String> messages = telegramWebAgent.readMessagesFromSpecificChat(chatBotName, messageFilter);
                messages.forEach(System.out::println);
            }
        }

        /*
         * try (Playwright playwright = Playwright.create()) {
         * 
         * BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
         * //options.setHeadless(false);
         * Browser browser = playwright.chromium().launch(options);
         * BrowserContext context = browser.newContext();
         * Page page = browser.newPage();
         * 
         * // Navigate to the Telegram login page
         * page.navigate("https://web.telegram.org");
         * logger.info("Navigate to Telegram Page");
         * 
         * // Wait for the login page to load
         * page.waitForLoadState();
         * switchToLoginByPhone(page, logger);
         * fillLoginInformation("", page, logger);
         * 
         * String verificationCode = readLoginCode();
         * enterVerificationCode(verificationCode, page, logger);
         * openSpecificChat(page, logger);
         * 
         * 
         * List<String> messages = readMessages(page);
         * messages.forEach(System.out::println);
         * 
         * // Close the browser
         * System.out.println(page.title());
         * browser.close();
         * }
         */
    }

    private static void switchToLoginByPhone(Page page, ConsoleLogger logger) {

        String loginByPhoneButtonSelector = "div.input-wrapper > button";

        try {
            logger.info("Try to switch authentication method to phone authentication.");
            page.waitForSelector(loginByPhoneButtonSelector);
            page.click(loginByPhoneButtonSelector);

        } catch (Exception exception) {
            logger.error("Couldn't find 'Login by phone' button.", exception);
        }
    }

    private static void fillLoginInformation(String phoneNumber, Page page, ConsoleLogger logger) {

        logger.info("Fill required phone number.");

        // Find and interact with the phone input field
        page.fill(
                "#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > div.input-field.input-field-phone > div.input-field-input",
                phoneNumber); // Replace with your phone number
        page.type(
                "#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > div.input-field.input-field-phone > div.input-field-input",
                phoneNumber); // Replace with your phone number

        // Click the "Next" button
        page.click(
                "#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > button.btn-primary.btn-color-primary.rp");

        // Wait for the code input field to appear (simulate verification step)
        page.waitForSelector(
                "#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-authCode.active > div > div.input-wrapper > div > input");

    }

    private static void enterVerificationCode(String verificationCode, Page page, ConsoleLogger logger) {
        // Simulate entering the verification code (replace with actual code)
        page.fill(
                "#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-authCode.active > div > div.input-wrapper > div > input",
                verificationCode); // Replace with the verification code

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector("#page-chats");
    }

    private static void openSpecificChat(Page page, ConsoleLogger logger) {
        // Find "@WhaleBot Pumps" bot
        page.fill("#column-left > div > div > div.sidebar-header.can-have-forum > div.input-search > input",
                "@WhaleBot Pumps");

        // Open bot from list
        page.click(
                "#search-container > div.scrollable.scrollable-y > div > div > div.search-super-container-chats.tabs-tab.active > div > div.search-group.search-group-contacts.is-short > ul > a:nth-child(1)");

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector(
                "#column-center > div > div > div.sidebar-header.topbar.is-pinned-message-shown > div.chat-info-container > div.chat-info > div > div > div.top > div");
    }

    private static String readLoginCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the verification code: ");
        return scanner.nextLine();
    }

    private static List<String> readMessages(Page page) {
        List<String> output = new ArrayList<>();
        List<ElementHandle> messageElements = page
                .querySelectorAll(".bubbles-inner .bubbles-date-group .bubbles-group");

        for (ElementHandle messageElement : messageElements) {
            String messageText = messageElement.innerText();
            System.out.println(messageText);
            output.add(messageText);
        }

        /*
         * // Scroll down multiple times to load more messages
         * for (int i = 0; i < 5; i++) {
         * // Scroll to the bottom of the page
         * page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
         * 
         * // Wait for the messages div to refresh after scrolling
         * page.
         * waitForSelector("#column-center > div > div > div.bubbles.has-groups.is-chat-input-hidden.has-sticky-dates > div > div > section:nth-child(2) > div:nth-child(8)"
         * ); // Replace with the appropriate selector
         * 
         * // Pause briefly to allow messages to load (adjust as needed)
         * page.waitForTimeout(1000);
         * }
         * 
         * // Retrieve and print the messages
         * System.out.println("Retrieved messages:");
         * List<ElementHandle> messageElements =
         * page.querySelectorAll(".bubbles-group .message");
         * 
         * for (ElementHandle messageElement : messageElements) {
         * String messageText = messageElement.innerText();
         * System.out.println(messageText);
         * output.add(messageText);
         * }
         */
        return output;
    }
}