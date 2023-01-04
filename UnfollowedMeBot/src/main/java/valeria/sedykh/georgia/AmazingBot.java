package valeria.sedykh.georgia;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;


public class AmazingBot extends TelegramLongPollingBot {
    
    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            
            try {
                String user_first_name = update.getMessage().getChat().getFirstName();
                String user_last_name = update.getMessage().getChat().getLastName();
                long user_id = update.getMessage().getChat().getId();
                String message_text = update.getMessage().getText();
                String answer = "Please type in emoji";

                if(message_text.equals("/start"))
                {
                    answer = "Valeria Sedykh is greeting " + user_first_name + " " + user_last_name;
                } 
                if(message_text.contains("/knowemoji")){
                    answer = "Please type in emoji";
                }

                if(EmojiManager.containsEmoji(message_text)){
                    answer = "Emoji - " + EmojiParser.parseToAliases(message_text);
                }

                long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(chat_id);
                message.setText(answer);
                execute(message); // Sending our message object to user
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        // TODO
        return "botUsername";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "token";
    }
}

