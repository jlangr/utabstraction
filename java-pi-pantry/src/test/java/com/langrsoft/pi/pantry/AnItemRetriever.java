package com.langrsoft.pi.pantry;

import static com.langrsoft.util.JsonUtil.toJson;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.langrsoft.util.HttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class AnItemRetriever {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    ItemRetriever retriever;
    HttpClient client;

    // TODO inject mocks etc.?
    @Before
    public void create() {
        client = mock(HttpClient.class);
        retriever = new ItemRetriever(client);
    }

    @Test
    public void parsesResponseJsonToItem() throws IOException {
        String responseText = toJson(new ItemBuilder("Wheaties 12oz").create());
        when(client.retrieveText(anyString())).thenReturn(responseText);

        Item item = retriever.retrieve("0016000275652");

        assertThat(item.getName(), startsWith("Wheaties"));
    }

    @Test
    public void throwsRuntimeExceptionOnHttpClientFailure() throws IOException {
        when(client.retrieveText(anyString())).thenThrow(new IOException());
        thrown.expect(RuntimeException.class);
        thrown.expectCause(instanceOf(IOException.class));

        retriever.retrieve("");
    }
}
