package com.github.telegrambotstepfather.botinteractions.agent;

import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.logger.Logger;
import com.microsoft.playwright.*;
import java.util.ArrayList;
import java.util.List;

public class TelegramWebAgentImpl implements TelegramWebAgent{

    private final Logger logger;
    private final Cache cache;
    private final Playwright playwright;
    private Browser browser;
    private Page page;

    public TelegramWebAgentImpl(Logger logger, Cache cache) {
        this.playwright = Playwright.create();
        this.logger = logger;
        this.cache = cache;
    }

    @Override
    public void init() {
        try{
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            browser = playwright.chromium().launch(options);
            BrowserContext context = browser.newContext();
            page = browser.newPage();
            page.navigate("https://web.telegram.org");

        }catch (Exception exception){
            System.out.println("Failed initialization");
        }
    }

    @Override
    public void close() throws Exception {
        browser.close();
        playwright.close();
    }

    @Override
    public void switchToLoginByPhone() {
        String loginByPhoneButtonSelector = "div.input-wrapper > button";

        try{
            logger.info("Try to switch authentication method to phone authentication.");
            page.waitForSelector(loginByPhoneButtonSelector);
            page.click(loginByPhoneButtonSelector);

        }catch (Exception exception)
        {
            logger.error("Couldn't find 'Login by phone' button.", exception);
        }
    }

    @Override
    public void fillLoginInformation(String phoneNumber) {

        logger.info("Fill required phone number.");

// Find and interact with the phone input field
        page.fill("#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > div.input-field.input-field-phone > div.input-field-input", phoneNumber); // Replace with your phone number
        page.type("#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > div.input-field.input-field-phone > div.input-field-input", phoneNumber); // Replace with your phone number

        // Click the "Next" button
        page.click("#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-sign.active > div > div.input-wrapper > button.btn-primary.btn-color-primary.rp");

        // Wait for the code input field to appear (simulate verification step)
        page.waitForSelector("#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-authCode.active > div > div.input-wrapper > div > input");
    }

    @Override
    public void enterVerificationCode(String verificationCode) {
        // Simulate entering the verification code (replace with actual code)
        page.fill("#auth-pages > div > div.tabs-container.auth-pages__container > div.tabs-tab.page-authCode.active > div > div.input-wrapper > div > input", verificationCode); // Replace with the verification code

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector("#page-chats");
    }

    @Override
    public List<String> readMessagesFromSpecificChat(String chatName) {

        // Find "@WhaleBot Pumps" bot
        page.fill("#column-left > div > div > div.sidebar-header.can-have-forum > div.input-search > input", chatName);

        // Open bot from list
        page.click("#search-container > div.scrollable.scrollable-y > div > div > div.search-super-container-chats.tabs-tab.active > div > div.search-group.search-group-contacts.is-short > ul > a:nth-child(1)");

        // Wait for successful authentication (replace with appropriate selector)
        page.waitForSelector("#column-center > div > div > div.sidebar-header.topbar.is-pinned-message-shown > div.chat-info-container > div.chat-info > div > div > div.top > div");


        List<String> output = new ArrayList<>();
        List<ElementHandle> messageElements = page.querySelectorAll(".bubbles-inner .bubbles-date-group .bubbles-group");

        for (ElementHandle messageElement : messageElements) {
            String messageText = messageElement.innerText();

            if(!cache.containsMessage(messageText)){
                cache.saveMessage(messageText);
                logger.info("Added a new message");
                logger.info("--------------------");
                logger.info(messageText);
                logger.info("--------------------");
                output.add(messageText);
            }
        }

        return output;

        /*
        // Scroll down multiple times to load more messages
        for (int i = 0; i < 5; i++) {
            // Scroll to the bottom of the page
            page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

            // Wait for the messages div to refresh after scrolling
            page.waitForSelector("#column-center > div > div > div.bubbles.has-groups.is-chat-input-hidden.has-sticky-dates > div > div > section:nth-child(2) > div:nth-child(8)"); // Replace with the appropriate selector

            // Pause briefly to allow messages to load (adjust as needed)
            page.waitForTimeout(1000);

         */
    }
}
