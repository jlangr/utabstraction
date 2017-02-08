package com.langrsoft.pi.pantry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static com.langrsoft.util.JsonUtil.toJson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AnItemFactory {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private ItemFactory factory = new ItemFactory();

    @Test
    public void populatesItemFromJson() {
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
        String serverItemJson = toJson(new ItemBuilder("Cheerios").withSourceName("xxx").create());

        Item item = factory.parse(serverItemJson);

        assertThat(item.getSourceName(), equalTo("Cheerios"));
    }

    @Test(expected = ItemParseException.class)
    public void throwsARuntimeExceptionOnParseFailure() {
        factory.parse("BAD BAD JSON!");
    }

    @Test
    public void defaultsExpirationDateToIndefinite() {
        String emptyItemJson = toJson(new Item());

        Item parsedItem = factory.parse(emptyItemJson);

        assertThat(parsedItem.getExpirationDate(), equalTo(LocalDate.MAX));
    }
}
