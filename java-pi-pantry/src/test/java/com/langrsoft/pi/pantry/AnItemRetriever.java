package com.langrsoft.pi.pantry;

import com.langrsoft.util.HttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.langrsoft.util.JsonUtil.toJson;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnItemRetriever {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @InjectMocks
    ItemRetriever retriever;
    @Mock
    HttpClient client;

    @Test
    public void parsesResponseJsonToItem() {
        String wheatiesUpc = "0016000275652";
        String responseText = toJson(new ItemBuilder("Wheaties 12oz").create());
        when(client.retrieveText(matches("http://.*/json/.*/" + wheatiesUpc)))
                .thenReturn(responseText);

        Item item = retriever.retrieve(wheatiesUpc);

        assertThat(item.getName(), startsWith("Wheaties"));
    }
}
