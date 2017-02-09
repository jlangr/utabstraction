package com.langrsoft.pi.pantry;

import com.langrsoft.util.HttpClient;

import java.io.IOException;

public class ItemRetriever {
    private static final String API_SERVER = "http://api.upcdatabase.org";
    public static final String UPC_API_KEY_PROPERTY_NAME = "upcdatabase_api_key";
    private String upcApiKey = System.getProperty(UPC_API_KEY_PROPERTY_NAME);
    private HttpClient httpClient;
    private ItemFactory itemFactory = new ItemFactory();

    public ItemRetriever(HttpClient client) {
        this.httpClient = client;
    }

    String url(String upcNumber) {
        return String.format("%s/json/%s/%s", API_SERVER, upcApiKey, upcNumber);
    }

    public Item retrieve(String upcNumber) {
        String json = httpClient.retrieveText(url(upcNumber));
        return itemFactory.create(json);
    }
}
