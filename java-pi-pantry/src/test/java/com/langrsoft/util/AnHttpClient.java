package com.langrsoft.util;

import com.langrsoft.testutil.Slow;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class AnHttpClient {
    @Category(Slow.class)
    @Test
    public void canRetrieveTextResponseFromUrl() {
        HttpClient client = new HttpClient();
        String teapotStatus = "418";
        String text = client.retrieveText("http://httpbin.org/status/" + teapotStatus);

        assertThat(text.toLowerCase(), containsString("teapot"));
    }
}
