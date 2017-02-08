package com.langrsoft.pi.pantry;

import com.langrsoft.util.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ItemRetriever {
    private static final String API_SERVER = "http://api.upcdatabase.org";
    public static final String UPC_API_PROPERTY_KEY = "upcdatabase_api_key";
    private String upcApiKey = System.getProperty(UPC_API_PROPERTY_KEY);
    private CloseableHttpClient httpClient;

    public ItemRetriever(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ItemRetriever() {
        this(HttpClients.createDefault());
    }

    // TODO genericize and move this method to take a url
    String retrieveJson(String upcNumber) throws IOException {
        try {
            String url = url(upcNumber);
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            httpClient.close();
        }
    }

    String url(String upcNumber) {
        return String.format("%s/json/%s/%s", API_SERVER, upcApiKey, upcNumber);
    }

    public Item retrieve(String upcNumber) {
        try {
            String json = retrieveJson(upcNumber);
            return JsonUtil.parse(json, Item.class);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
