package com.github.telegrambotstepfather.botinteractions.agent;

import com.github.telegrambotstepfather.botinteractions.persistance.Cache;
import com.github.telegrambotstepfather.botinteractions.logger.Logger;
import com.github.telegrambotstepfather.botinteractions.models.ChatMessage;
import com.microsoft.playwright.BrowserContext.StorageStateOptions;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Optional;
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
    public boolean init(Optional<String> storageStateFilePath) {

        boolean isAuthenticated = false;

        try {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);
            this.browser = playwright
                .chromium()
                .launch(launchOptions);

            storageStateFilePath.ifPresent(filePath -> {
                Path storageStatePath = Paths.get(filePath);

                if(Files.exists(storageStatePath))
                {
                    NewContextOptions newContextOptions = new Browser.NewContextOptions()
                        .setStorageStatePath(storageStatePath);
                    this.context = browser.newContext(newContextOptions);
                }
            });

            if(this.context == null)
            {
                this.context = browser.newContext();
            }
            else
            {
                isAuthenticated = true;
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
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Override
    public byte[] getLoginQrCode() {
        return page.screenshot();
    }

    @Override
    public void switchToLoginByPhone() {
        logger.info("Try to switch authentication method to phone authentication.");

        String loginByPhoneButtonSelector = "div.input-wrapper > button, div.auth-form > button";
        page.waitForSelector(loginByPhoneButtonSelector).click();
    }

    @Override
    public void fillLoginInformation(String region, String phoneNumber) {

        logger.info("Fill required phone number.");

        String phoneNumberInputFieldSelector = "div.input-field.input-field-phone > div.input-field-input, input#sign-in-phone-number";
        ElementHandle phoneNumberElement = page.waitForSelector(phoneNumberInputFieldSelector);
        phoneNumberElement.fill("");
        phoneNumberElement.type(phoneNumber);

        String nextButtonSelector = "div.input-wrapper > buttom.btn-primary, button:not(.btn-secondary)";
        page.waitForSelector(nextButtonSelector).click();
    }

    @Override
    public void enterVerificationCode(String verificationCode, Optional<String> storageStateFilePath) {

        logger.info("Enter verification code.");

        String verificationCodeInputSelector = "input.sign-in-code";
        ElementHandle verificationCodeElement = page.waitForSelector(verificationCodeInputSelector);
        verificationCodeElement.fill(verificationCode);

        storageStateFilePath.ifPresent(filePath -> {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                StorageStateOptions options = new BrowserContext.StorageStateOptions().setPath(path);
                context.storageState(options);
            }
        });
    }

    @Override
    public List<ChatMessage> readMessagesFromSpecificChat(String chatName) {

        String chatsSelector = "a.ListItem-button";
        page.waitForSelector(chatsSelector);

        String userSearchInputSelector = "input#telegram-search-input";
        ElementHandle userSearchInput = page.waitForSelector(userSearchInputSelector);
        userSearchInput.fill(chatName);

        String foundUserDivSelector = "div.ListItem-button"; //TODO: Assume that we choose the first option
        ElementHandle foundUserDiv = page.waitForSelector(foundUserDivSelector);
        foundUserDiv.click();

        return readMessagesFromOpenedChat();
    }

    @Override
    public List<ChatMessage> readMessagesFromOpenedChat()
    {
        List<ChatMessage> output = new ArrayList<>();
        List<ElementHandle> messageElements = page
                .querySelectorAll("div.text-content");

        for (ElementHandle messageElement : messageElements) {
            String innerHtml = messageElement.innerHTML();
            String innerText = messageElement.innerText();

            Optional<String> hash = cache.containsMessage(innerText);

            if (hash.isPresent()) {
                String internalId = cache.saveMessage(innerText, hash.get());
                output.add(new ChatMessage(internalId, innerText, innerHtml));
            }
        }

        return output;
    }

    @Override
    public void close() throws Exception {
        browser.close();
        playwright.close();
    }
}
