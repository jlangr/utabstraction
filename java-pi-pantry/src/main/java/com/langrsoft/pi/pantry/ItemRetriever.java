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
    private CloseableHttpClient httpClient;

    public ItemRetriever(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ItemRetriever() {
        this(HttpClients.createDefault());
    }

    // TODO test for failure
    // TODO externalize API key
    public String retrieveJson(String upcNumber) throws IOException {
        try {
            String apiKey = "a6f99f4c683845042ae27bbf3e36aeee";
            String url = String.format("%s/json/%s/%s", API_SERVER, apiKey, upcNumber);
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
