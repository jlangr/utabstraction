package com.langrsoft.pi.pantry;

import com.langrsoft.util.JsonParseException;
import com.langrsoft.util.TestClock;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.langrsoft.util.JsonUtil.toJson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.is;
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

        Item item = factory.create(responseBody);

        assertThat(item.getName(), equalTo("Cheerios"));
        assertThat(item.getDescription(), equalTo("Da Big Box"));
    }

    @Test
    public void assignsNameToSourceName() {
        String serverItemJson = toJson(new ItemBuilder("Cheerios").withSourceName("xxx").create());

        Item item = factory.create(serverItemJson);

        assertThat(item.getSourceName(), equalTo("Cheerios"));
    }

    @Test
    public void replacesNameUsingLookupTable() {
        String serverItemJson = toJson(new ItemBuilder("Wheaties 40oz").withNumber("999").create());
        Map<String,String> numbersToLocalNames = new HashMap<>();
        numbersToLocalNames.put("999", "Wheaties");
        factory.setNumberToLocalNameMappings(numbersToLocalNames);

        Item item = factory.create(serverItemJson);

        assertThat(item.getName(), is(equalTo("Wheaties")));
        assertThat(item.getSourceName(), is(equalTo("Wheaties 40oz")));
    }

    @Test(expected = JsonParseException.class)
    public void throwsARuntimeExceptionOnParseFailure() {
        factory.create("BAD BAD JSON!");
    }

    @Test
    public void defaultsSellByDateToPurchaseDate() {
        LocalDate now = LocalDate.now();
        factory.setClock(TestClock.fixedTo(now));

        Item parsedItem = factory.create(toJson(new Item()));

        assertThat(parsedItem.getSellByDate(), equalTo(now));
    }

    @Test
    public void defaultsCategoryToNameIfRecognized() {
        assertThat(new ShelfLifeRepository().contains("milk"), is(true));
        String itemJson = toJson(new ItemBuilder("milk").create());

        Item parsedItem = factory.create(itemJson);

        assertThat(parsedItem.getCategory(), is(equalTo("milk")));
    }

    @Test
    public void defaultsCategoryToNullWhenNotRecognized() {
        String itemJson = toJson(new ItemBuilder("nonexistent category").create());

        Item parsedItem = factory.create(itemJson);

        assertThat(parsedItem.getCategory(), is(nullValue()));
    }

    @Test
    public void defaultsExpirationDateToIndefinite() {
        String emptyItemJson = toJson(new Item());

        Item parsedItem = factory.create(emptyItemJson);

        assertThat(parsedItem.getExpirationDate(), equalTo(LocalDate.MAX));
    }
}
