package valeria.sedykh.georgia;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
  public static void main(String[] args) {
      // Initialize Api Context
      // Instantiate Telegram Bots API
      
      // Register our bot
      try {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new AmazingBot());
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
