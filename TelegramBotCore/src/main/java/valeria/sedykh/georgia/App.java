package valeria.sedykh.georgia;

import java.net.HttpURLConnection;
import java.net.URL;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import main.java.valeria.sedykh.georgia.TelegramPoolingBot;
import main.java.valeria.sedykh.georgia.settings.TelegramBotConfigurationSettings;

public class App {
  public static void main(String[] args) {
      // Initialize Api Context
      // Instantiate Telegram Bots API
      System.out.println("Hello World!");
      // Register our bot
      try {

        TelegramBotConfigurationSettings configurationSettings = new TelegramBotConfigurationSettings("608401780:AAEhF8Rjtkm4B7V46Ovt3U-M7A-LpMqQp0Y");
        TelegramPoolingBot telegramPoolingBot = new TelegramPoolingBot(configurationSettings);
        telegramPoolingBot.GetMe();

        // TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        // telegramBotsApi.registerBot(new AmazingBot());
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
