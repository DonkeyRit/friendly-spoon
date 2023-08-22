package com.github.telegrambotstepfather.botinteractions.agent;

import java.util.List;

public interface TelegramWebAgent extends AutoCloseable {

    boolean isAlreadyLogin();
    void init();
    byte[] getLoginQrCode();
    void switchToLoginByPhone();
    void fillLoginInformation(String phoneNumber);
    void enterVerificationCode(String verificationCode);
    List<String> readMessagesFromSpecificChat(String chatName);
}
