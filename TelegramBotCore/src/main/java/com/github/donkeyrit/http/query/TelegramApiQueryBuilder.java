package com.github.donkeyrit.http.query;

public class TelegramApiQueryBuilder implements QueryBuilder
{
    private String url;
    private String botApiKey;

    public TelegramApiQueryBuilder(String url, String botApiKey)
    {
        this.url = url;
        this.botApiKey = botApiKey;
    }

    @Override
    public String BuildQuery() 
    {
        return String.format(url, botApiKey);
    }
    
}
