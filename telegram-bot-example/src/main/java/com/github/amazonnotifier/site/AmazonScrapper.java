package com.github.amazonnotifier.site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class AmazonScrapper 
{

    public String parse(String url) throws IOException
    {
        // Set up the request headers and cookies
        Connection connection = Jsoup.connect(url)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
            .header("Accept-Language", "en-US,en;q=0.5")
            .header("Referer", "https://www.google.com/")
            .header("Connection", "keep-alive")
            .cookie("name", "value")
            .cookie("another_name", "another_value");

        // Send the request and retrieve the response
        Document doc = connection.get();
        
        System.out.println(doc.outerHtml());

        Element deliveryMessage = doc.selectFirst("#deliveryBlockMessage");
        if(deliveryMessage != null)
        {
            return deliveryMessage
                .text()
                .trim();
        }

        return null;
    }
}
