package com.github.telegrambotstepfather.botinteractions.agent;

import java.util.List;

import com.github.telegrambotstepfather.botinteractions.filter.MessageFilter;

public interface TelegramWebAgent extends AutoCloseable {

    void init();
    void navigate(String url);
    byte[] getLoginQrCode();
    void switchToLoginByPhone();
    void fillLoginInformation(String region, String phoneNumber);
    void enterVerificationCode(String verificationCode);
    List<String> readMessagesFromSpecificChat(String chatName, MessageFilter messageFilter);
}
