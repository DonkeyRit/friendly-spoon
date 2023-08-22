package com.github.telegrambotstepfather.botinteractions.agent;

import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.logger.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelegramWebAgentImpl implements TelegramWebAgent {

    private final Logger logger;
    private final Cache cache;
    private final Playwright playwright;

    private BrowserContext context;
    private Browser browser;
    private Page page;

    public TelegramWebAgentImpl(Logger logger, Cache cache) {
        this.playwright = Playwright.create();
        this.logger = logger;
        this.cache = cache;
    }

    @Override
    public void init() {
        try {
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            this.browser = playwright.chromium().launch(options);
            this.context = browser.newContext();
            page = context.newPage();
            page.navigate("https://web.telegram.org");

        } catch (Exception exception) {
            System.out.println("Failed initialization");
        }
    }

    @Override
    public byte[] getLoginQrCode() {
        return page.screenshot();
    }

    @Override
    public void close() throws Exception {
        browser.close();
        playwright.close();
    }

    @Override
    public void switchToLoginByPhone() {

        String loginByPhoneButtonSelector = "div.input-wrapper > button";
        String loginByPhoneButtonFromQrCodeSelector = "div.auth-form > button";

        try {
            logger.info("Try to switch authentication method to phone authentication.");
            ElementHandle loginByPhoneWrappedButton = page.waitForSelector(loginByPhoneButtonSelector);
            ElementHandle logingByPhoneAuthFormButton = page.waitForSelector(loginByPhoneButtonFromQrCodeSelector);
            
            loginByPhoneWrappedButton.click();
            logingByPhoneAuthFormButton.click();

        } catch (Exception exception) {
            logger.error("Couldn't find any buttons to switch authentication.", exception);
        }
    }

    @Override
    public void fillLoginInformation(String phoneNumber) {

        logger.info("Fill required phone number.");

        String phoneNumberRegionInputFieldselector = "div.input-field.input-field-phone > div.input-field-input";
        String phoneNumberInputFieldSelector = "div.input-field.input-field-select > div.input-field-input";
        String nextButtonSelector = "button:not(.btn-secondary)";

        // Find and interact with the phone input field
        page.fill(phoneNumberRegionInputFieldselector, phoneNumber); // Replace with your phone number
        page.type(phoneNumberInputFieldSelector,phoneNumber); // Replace with your phone number

        // Click the "Next" button
        page.click(nextButtonSelector);
    }

    @Override
    public void enterVerificationCode(String verificationCode) {

        String verificationCodeInputSelector = ".input-wrapper > .input-field > input";

        // Wait for the code input field to appear (simulate verification step)
        page.waitForSelector(verificationCodeInputSelector);
            
        // Simulate entering the verification code (replace with actual code)
        page.fill(verificationCodeInputSelector, verificationCode); // Replace with the verification code

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector("#page-chats");
        List<Cookie> cookies = context.cookies();
        saveCookiesToFile(cookies);
    }

    @Override
    public List<String> readMessagesFromSpecificChat(String chatName) {

        // Find "@WhaleBot Pumps" bot
        page.fill("#column-left > div > div > div.sidebar-header.can-have-forum > div.input-search > input", chatName);

        // Open bot from list
        page.click(
                "#search-container > div.scrollable.scrollable-y > div > div > div.search-super-container-chats.tabs-tab.active > div > div.search-group.search-group-contacts.is-short > ul > a:nth-child(1)");

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector(
                "#column-center > div > div > div.sidebar-header.topbar.is-pinned-message-shown > div.chat-info-container > div.chat-info > div > div > div.top > div");

        List<String> output = new ArrayList<>();
        List<ElementHandle> messageElements = page
                .querySelectorAll(".bubbles-inner .bubbles-date-group .bubbles-group");

        for (ElementHandle messageElement : messageElements) {
            String messageText = messageElement.innerText();

            if (!cache.containsMessage(messageText)) {
                cache.saveMessage(messageText);

                if (filterMessages(messageText, "Pumping on Kucoin", 0)) {
                    output.add(messageText);
                    logger.info("Added a new message");
                    logger.info("--------------------");
                    logger.info(messageText);
                    logger.info("--------------------");

                }
            }
        }

        return output;

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
         * 
         */
    }

    private boolean filterMessages(String messageText, String filterPhrase, double dailyPricePercentThreshold) {
        if (messageText.contains(filterPhrase)) {
            String patt = "24h%: (.*?)%";
            Pattern pattern = Pattern.compile(patt);
            Matcher matcher = pattern.matcher(messageText);

            // Find all matches
            while (matcher.find()) {
                // Get the matching string
                String value = matcher.group(1);
                try {
                    logger.info(value);
                    double price = Double.parseDouble(value);
                    if (price > dailyPricePercentThreshold) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception ex) {
                }

                // if(Integer.parseInt(match))
                // {

                // }
                // }

                return true;
            }
        }
        return false;
    }

    public static void saveCookiesToFile(List<Cookie> cookies) {
        ObjectMapper objectMapper = new ObjectMapper();
        String cookiesJson;

        try {
            // Convert cookies to JSON format
            cookiesJson = objectMapper.writeValueAsString(cookies);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        // Define the path to the file where cookies will be saved
        Path filePath = Path.of("cookies.json");

        try {
            // Write cookies JSON to the file
            Files.write(filePath, cookiesJson.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Cookie> loadCookiesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        String cookiesJson;

        // Define the path to the file where cookies are saved
        Path filePath = Path.of("cookies.json");

        try {
            // Read the cookies JSON from the file
            cookiesJson = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            // Convert JSON to a list of cookies
            return objectMapper.readValue(cookiesJson, new TypeReference<List<Cookie>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
