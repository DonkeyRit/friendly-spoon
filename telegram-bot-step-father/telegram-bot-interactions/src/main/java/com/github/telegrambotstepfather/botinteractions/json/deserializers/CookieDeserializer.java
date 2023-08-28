package com.github.telegrambotstepfather.botinteractions.json.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.SameSiteAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;

public class CookieDeserializer implements ManualJsonDeserializer<List<Cookie>> {

    @Override
    public List<Cookie> deserialize(String json) {
        List<Cookie> cookies = new ArrayList<>();

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(json);

            // Verify that the first token is the start of an array
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IOException("Expected start of array");
            }

            // Iterate through each object in the array
            while (parser.nextToken() != JsonToken.END_ARRAY) {
                Cookie cookie = parseCookie(parser);
                cookies.add(cookie);
            }

            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cookies;
    }

    private static Cookie parseCookie(JsonParser parser) throws IOException {
        String name = null;
        String value = null;
        String url = null;
        String domain = null;
        String path = null;
        long expires = 0;
        boolean httpOnly = false;
        boolean secure = false;
        String sameSite = null;

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            parser.nextToken(); // Move to the field value

            switch (fieldName) {
                case "name":
                    name = parser.getText();
                    break;
                case "value":
                    value = parser.getText();
                    break;
                case "url":
                    url = parser.getText();
                    break;
                case "domain":
                    domain = parser.getText();
                    break;
                case "path":
                    path = parser.getText();
                    break;
                case "expires":
                    expires = parser.getLongValue();
                    break;
                case "httpOnly":
                    httpOnly = parser.getBooleanValue();
                    break;
                case "secure":
                    secure = parser.getBooleanValue();
                    break;
                case "sameSite":
                    sameSite = parser.getText();
                    break;
                default:
                    // Handle other fields as needed
                    break;
            }
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setUrl("null".equals(url) ? null : url);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setExpires(expires);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        cookie.setSameSite(SameSiteAttribute.valueOf(sameSite));

        return cookie;
    }
}
