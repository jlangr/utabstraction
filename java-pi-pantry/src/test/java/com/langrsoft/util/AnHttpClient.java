package com.langrsoft.util;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class AnHttpClient {
    HttpClient client;
    @Before
    public void init() {
        client = new HttpClient();
    }
    @Test
    public void canRetrieveTextResponseFromUrl() {
        String teapotStatus = "418";
        String text = client.retrieveText("http://httpbin.org/status/" + teapotStatus);

        assertThat(text.toLowerCase(), containsString("teapot"));
    }
}
