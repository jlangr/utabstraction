package com.langrsoft.pi.pantry;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnItemRetrieverWithMockedHttpClient {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsRuntimeExceptionOnHttpClientFailure() throws IOException {
        thrown.expect(RuntimeException.class);
        thrown.expectCause(instanceOf(IOException.class));

        CloseableHttpClient mockHttpClient = mock(CloseableHttpClient.class);
        ItemRetriever retriever = new ItemRetriever(mockHttpClient);
        when(mockHttpClient.execute(anyObject())).thenThrow(new IOException());

        retriever.retrieve("");

    }
}
