package com.github.telegrambotstepfather.botinteractions.agent;

import java.util.List;

public interface TelegramWebAgent extends AutoCloseable {

    boolean isAlreadyLogin();
    void init();
    void navigate();
    byte[] getLoginQrCode();
    void switchToLoginByPhone();
    void fillLoginInformation(String region, String phoneNumber);
    void enterVerificationCode(String verificationCode);
    List<String> readMessagesFromSpecificChat(String chatName);
}
