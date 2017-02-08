package com.langrsoft.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {
    private CloseableHttpClient httpClient;

    public HttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClient() {
        this(HttpClients.createDefault());
    }

    // TODO Live test
    public String retrieveText(String url) throws IOException {
        try {
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
}
