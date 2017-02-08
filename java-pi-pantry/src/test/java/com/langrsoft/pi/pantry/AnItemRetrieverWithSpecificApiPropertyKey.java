package com.langrsoft.pi.pantry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertThat;

public class AnItemRetrieverWithSpecificApiPropertyKey {
    static final String SOME_API_KEY = "SOME_API_KEY";
    String currentApiKey;

    @Before
    public void setApiKey() {
        currentApiKey = System.getProperty(ItemRetriever.UPC_API_PROPERTY_KEY);
        System.setProperty(ItemRetriever.UPC_API_PROPERTY_KEY, SOME_API_KEY);
    }

    @After
    public void resetApiKey() {
        System.setProperty(ItemRetriever.UPC_API_PROPERTY_KEY, currentApiKey);
    }

    @Test
    public void constructsUrlUsingSystemPropertyForApiKey() {
        ItemRetriever retriever = new ItemRetriever(null);

        String url = retriever.url("123");

        assertThat(url, endsWith(SOME_API_KEY + "/123"));
    }
}
