package com.langrsoft.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {
    public String retrieveText(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            return get(url, httpClient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String get(String url, CloseableHttpClient httpClient) throws IOException {
        HttpResponse response = httpClient.execute(new HttpGet(url));
        return EntityUtils.toString(response.getEntity());
    }
}
