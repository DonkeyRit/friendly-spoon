package com.github.amazonnotifier.site;

public class AmazonScrapper 
{

    public void parse(String url)
    {
        Document doc = Jsoup
            .connect(url)
            .get();
        
        Element deliveryMessage = doc.selectFirst("#ddmDeliveryMessage > b");
        String deliveryDate = deliveryMessage
            .text()
            .trim();
        System.out.println(deliveryDate);
    }
}
