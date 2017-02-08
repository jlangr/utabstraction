package com.langrsoft.pi.pantry;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnItemRetrieverWithMockedHttpClient {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private CloseableHttpClient mockHttpClient;
    private ItemRetriever retriever;

    @Before
    public void create() {
        mockHttpClient = mock(CloseableHttpClient.class);
    }

    @Test
    public void throwsRuntimeExceptionOnHttpClientFailure() throws IOException {
        retriever = new ItemRetriever(mockHttpClient);
        when(mockHttpClient.execute(anyObject())).thenThrow(new IOException());
        thrown.expect(RuntimeException.class);
        thrown.expectCause(instanceOf(IOException.class));

        retriever.retrieve("");
    }

    // "a6f99f4c683845042ae27bbf3e36aeee";
    @Test
    public void constructsUrlUsingSystemPropertyForApiKey() {
        System.setProperty(ItemRetriever.UPC_API_PROPERTY_KEY, "APIKEY");
        retriever = new ItemRetriever(mockHttpClient);
        String url = retriever.url("123");

        assertThat(url, endsWith("APIKEY/123"));

    }
}
