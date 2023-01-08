package valeria.sedykh.georgia;

import main.java.valeria.sedykh.georgia.TelegramPoolingBot;
import main.java.valeria.sedykh.georgia.configurations.ConfigurationManager;
import main.java.valeria.sedykh.georgia.settings.TelegramBotConfigurationSettings;

public class App 
{
  public static void main(String[] args) throws Exception 
  {

    TelegramBotConfigurationSettings configurationSettings = ConfigurationManager.GetTelegramBotConfiguration();
    TelegramPoolingBot telegramPoolingBot = new TelegramPoolingBot(configurationSettings);
    telegramPoolingBot.GetMe();
  }
}
