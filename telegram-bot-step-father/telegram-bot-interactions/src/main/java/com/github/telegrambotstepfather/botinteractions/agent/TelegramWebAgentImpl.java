package com.github.telegrambotstepfather.botinteractions.agent;

import com.github.telegrambotstepfather.botinteractions.extensions.PageExtensions;
import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;
import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.logger.Logger;

import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

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
    public boolean init(String storageStateFilePath) {

        boolean isAuthenticated = false;

        try {
            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
            options.setHeadless(false);
            this.browser = playwright.chromium().launch(options);

            Path storageStatePath = Paths.get(storageStateFilePath);
            if(Files.exists(storageStatePath)){
                this.context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(storageStatePath));
                isAuthenticated = true;
            }
            else{
                this.context = browser.newContext();
            }

            page = context.newPage();            

        } catch (Exception exception) {
            System.out.println("Failed initialization");
        }

        return isAuthenticated;
    }

    @Override
    public void navigate(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.waitForLoadState(LoadState.LOAD);
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

        logger.info("Try to switch authentication method to phone authentication.");

        PageExtensions
            .waitForSelectorsAsync(page, loginByPhoneButtonSelector, loginByPhoneButtonFromQrCodeSelector)
            .click();
    }

    @Override
    public void fillLoginInformation(String region, String phoneNumber) {

        logger.info("Fill required phone number.");

        String phoneNumberInputFieldSelector = "div.input-field.input-field-phone > div.input-field-input";
        String phoneNumberInputFieldAlternativeSelector = "input#sign-in-phone-number";
        
        String nextButtonSelector = "button:not(.btn-secondary)";
        String nextButtonAlternativeSelector = "div.input-wrapper > buttom.btn-primary";

        // Find and interact with the phone input field

        //String phoneNumberRegionInputFieldselector = "div.input-field.input-select > div.input-field-input";
        //String phoneNumberRegionInputFieldAlternativeSelector = "input#sign-in-phone-code";

        //PageExtensions
            //.waitForSelectorsAsync(page, phoneNumberRegionInputFieldselector, phoneNumberRegionInputFieldAlternativeSelector)
            //.type(region);

        //ElementHandle phoneNumberRegionElement = PageExtensions.waitForElement(page, phoneNumberRegionInputFieldselector, phoneNumberRegionInputFieldAlternativeSelector);
        //phoneNumberRegionElement.fill("");
        //phoneNumberRegionElement.fill(region);

        //page.click("li:not([style*='display: none'])");
        
        // Update focus

        ElementHandle phoneNumberElement = PageExtensions.waitForSelectorsAsync(page, phoneNumberInputFieldSelector, phoneNumberInputFieldAlternativeSelector);
        phoneNumberElement.fill("");
        phoneNumberElement.type(phoneNumber);

        // Click the "Next" button
        PageExtensions
            .waitForSelectorsAsync(page, nextButtonSelector, nextButtonAlternativeSelector)
            .click();
    }

    @Override
    public void enterVerificationCode(String verificationCode) {

        String verificationCodeInputSelector = "div.input-field > input";
        String verificationCodeInputAlternativeSelector = "div.auth-form > div.input-group > input";

        // Wait for the code input field to appear (simulate verification step)
        ElementHandle verificationCodeElement = PageExtensions.waitForElement(page, verificationCodeInputSelector, verificationCodeInputAlternativeSelector);
            
        // Simulate entering the verification code (replace with actual code)
        verificationCodeElement.fill(verificationCode);

        // Wait for successful authentication (replace with appropriate selector)
        //page.waitForSelector("#page-chats");
        //browserCookieCache.save(context);

        context.storageState(
            new BrowserContext.StorageStateOptions()
                .setPath(Paths.get("/Users/dimaalekseev/Reps/friendly-spoon/telegram-bot-step-father/telegram-bot-interactions/assets/state.json")));
    }

    @Override
    public List<String> readMessagesFromSpecificChat(String chatName, MessageFilter messageFilter) {

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

                if(messageFilter.filter(messageText)){
                    output.add(messageText);
                    logger.info("Added a new message");
                    logger.info("--------------------");
                    logger.info(messageText);
                    logger.info("--------------------");
                }
            }
        }

        return output;
    }
}
