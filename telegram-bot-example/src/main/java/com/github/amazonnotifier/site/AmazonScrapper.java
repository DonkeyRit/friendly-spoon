package com.github.amazonnotifier.site;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import org.jsoup.Jsoup;

public class AmazonScrapper 
{

    public void parse(String url) throws IOException
    {
        Document doc = Jsoup
            .connect(url)
            .get();
        
        Element deliveryMessage = doc.selectFirst("#mir-layout-DELIVERY_BLOCK");
        if(deliveryMessage != null)
        {
            String deliveryDate = deliveryMessage
                .text()
                .trim();
            System.out.println(deliveryDate);
        }
    }
}
