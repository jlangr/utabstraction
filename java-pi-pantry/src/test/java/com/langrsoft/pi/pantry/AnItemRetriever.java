package com.langrsoft.pi.pantry;

import com.langrsoft.util.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.langrsoft.util.JsonUtil.toJson;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnItemRetriever {
    @InjectMocks
    ItemRetriever itemRetriever;
    @Mock
    HttpClient httpClient;

    @Test
    public void parsesResponseJsonToItem() {
        String wheatiesUpc = "0016000275652";
        String responseText = toJson(new ItemBuilder("Wheaties").create());
        when(httpClient.retrieveText(matches("http://.*/json/.*/" + wheatiesUpc)))
                .thenReturn(responseText);

        Item item = itemRetriever.retrieve(wheatiesUpc);

        assertThat(item.getName(), is(equalTo(("Wheaties"))));
    }
}
