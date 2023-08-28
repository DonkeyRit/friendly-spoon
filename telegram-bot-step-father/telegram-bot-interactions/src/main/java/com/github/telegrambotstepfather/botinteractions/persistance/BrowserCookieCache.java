package com.github.telegrambotstepfather.botinteractions.persistance;

import com.github.telegrambotstepfather.botinteractions.json.SimpleJsonFile;
import com.github.telegrambotstepfather.botinteractions.json.deserializers.CookieDeserializer;
import com.github.telegrambotstepfather.botinteractions.json.deserializers.ManualJsonDeserializer;
import com.github.telegrambotstepfather.botinteractions.json.JsonFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.options.Cookie;
import java.util.List;
import java.util.Optional;

public class BrowserCookieCache {

    private final JsonFile<List<Cookie>> cacheFile;

    public BrowserCookieCache(String filePath){
        cacheFile = new SimpleJsonFile<List<Cookie>>(filePath);
    }

    public void save(BrowserContext context)
    {
        List<Cookie> cookies = context.cookies();
        cacheFile.save(cookies);
    }

    public boolean tryRetrieve(BrowserContext context){

        Optional<ManualJsonDeserializer<List<Cookie>>> deserializer = Optional.of(new CookieDeserializer());
        TypeReference<List<Cookie>> type = new TypeReference<List<Cookie>>() {};
        List<Cookie> cachedCookies = cacheFile.read(type, deserializer);

        if(cachedCookies == null || cachedCookies.isEmpty())
        {
            return false;
        }

        context.addCookies(cachedCookies);
        return true;
    }
}
