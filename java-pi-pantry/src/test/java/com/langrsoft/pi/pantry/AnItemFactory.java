package com.langrsoft.pi.pantry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class AnItemFactory {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private ItemFactory factory = new ItemFactory();

    @Test
    public void populatesAnItemFromJson() {
        String responseBody = "{\"valid\":\"true\","
                              + "\"number\":\"0016000275645\","
                              + "\"itemname\":\"Cheerios\","
                              + "\"alias\":\"\","
                              + "\"description\":\"Da Big Box\","
                              + "\"avg_price\":\"\","
                              + "\"rate_up\":0,"
                              + "\"rate_down\":0}";
        Item item = factory.parse(responseBody);

        assertThat(item.getName(), equalTo("Cheerios"));
        assertThat(item.getDescription(), equalTo("Da Big Box"));
    }

    @Test
    public void assignsNameToSourceName() {
        String serverItemJson = toJson(new ItemBuilder("Cheerios").withSourceName("sourceName").create());

        Item item = factory.parse(serverItemJson);

        assertThat(item.getSourceName(), equalTo("Cheerios"));
    }

    private String toJson(Item serverItem) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(serverItem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = ItemParseException.class)
    public void throwsARuntimeExceptionOnParseFailure() {
        factory.parse("BAD BAD JSON!");
    }

    @Test
    public void expirationDateIndefiniteByDefault() {
        String emptyItemJson = toJson(new Item());

        Item parsedItem = factory.parse(emptyItemJson);

        assertThat(parsedItem.getExpirationDate(), equalTo(LocalDate.MAX));
    }
}
