package com.github.telegrambotstepfather.botinteractions.agent;

import com.github.telegrambotstepfather.botinteractions.models.ChatMessage;

import java.util.Optional;
import java.util.List;

public interface TelegramWebAgent extends AutoCloseable {

    boolean init(Optional<String> storageStateFilePath);
    void navigate(String url);
    byte[] getLoginQrCode();
    void switchToLoginByPhone();
    void fillLoginInformation(String region, String phoneNumber);
    void enterVerificationCode(String verificationCode, Optional<String> storageStateFilePath);
    List<ChatMessage> readMessagesFromOpenedChat();
    List<ChatMessage> readMessagesFromSpecificChat(String chatName);
}
