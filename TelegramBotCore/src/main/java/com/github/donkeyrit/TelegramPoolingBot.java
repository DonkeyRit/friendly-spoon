package com.github.donkeyrit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.donkeyrit.settings.TelegramBotConfigurationSettings;
import com.github.donkeyrit.settings.TelegramBotSettings;

public class TelegramPoolingBot 
{
    private final TelegramBotConfigurationSettings configurationSettings;

    public TelegramPoolingBot(TelegramBotConfigurationSettings configurationSettings)
    {
        this.configurationSettings = configurationSettings;
    }
    
    public TelegramBotSettings GetMe() throws Exception
    {
        String urlStr = String.format("https://api.telegram.org/bot%s/getMe", this.configurationSettings.botApiKey());
        URL obj = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request did not work.");
		}

        return null;
    }

}
