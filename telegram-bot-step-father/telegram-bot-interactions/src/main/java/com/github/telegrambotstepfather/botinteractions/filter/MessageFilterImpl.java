package com.github.telegrambotstepfather.botinteractions.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFilterImpl implements MessageFilter {

    private final double dailyPricePercentThreshold;
    private final String filterPhrase;
    
    public MessageFilterImpl(String filterPhrase, double dailyPricePercentThreshold) {
        this.filterPhrase = filterPhrase;
        this.dailyPricePercentThreshold = dailyPricePercentThreshold;
    }

    @Override
    public boolean filter(String messageText) {
        if (messageText.contains(this.filterPhrase)) {
            String patternStr = "24h%: (.*?)%";
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(messageText);
    
            while (matcher.find()) {
                String value = matcher.group(1);
                try {
                    double price = Double.parseDouble(value);
                    if (price > this.dailyPricePercentThreshold) {
                        return true;
                    }
                } catch (NumberFormatException ex) {
                    // Handle parsing error if needed
                }
            }
        }
        return false;
    }    
}
