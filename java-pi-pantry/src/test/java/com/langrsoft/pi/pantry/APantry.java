package com.langrsoft.pi.pantry;

import com.langrsoft.util.TestClock;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class APantry {
    Pantry pantry = new Pantry();
    static final LocalDate TODAY = LocalDate.now();

    @Test
    public void containsAPurchasedItem() {
        pantry.purchase(new Item("cheerios"));

        boolean containsItem = pantry.contains("cheerios");

        assertThat(containsItem, equalTo(true));
    }

    @Test
    public void doesNotContainItemNotPurchased() {
        pantry.purchase(new Item("cheerios"));

        boolean containsItem = pantry.contains("sugar");

        assertThat(containsItem, is(equalTo(false)));
    }

    @Test
    public void countOfItemNotPurchasedIsZero() {
        assertThat(pantry.count("sugar"), is(equalTo(0)));
    }

    @Test
    public void answersCountOfItemsPurchased() {
        pantry.purchase(new Item("sugar"));
        pantry.purchase(new Item("cheerios"));
        pantry.purchase(new Item("cheerios"));

        int count = pantry.count("cheerios");

        assertThat(count, is(equalTo(2)));
    }

    @Test
    public void retainsItemDetails() {
        Item sugar = new ItemBuilder("sugar").withDescription("refined sweetener").create();

        pantry.purchase(sugar);

        Item retrieved = pantry.getItemNamed("sugar");
        assertThat(retrieved.getDescription(), is(equalTo("refined sweetener")));
    }

    @Test
    public void attachesDateToThePurchase() {
        pantry.setClock(TestClock.fixedTo(TODAY));

        pantry.purchase(new Item("eggs"));

        Item retrieved = pantry.getItemNamed("eggs");
        assertThat(retrieved.getPurchaseDate(), is(equalTo(TODAY)));
    }

    @Test
    public void returnsNullWhenRetrieveByItemNameFindsNothing() {
        assertThat(pantry.getItemNamed("did not purchase"), is(nullValue()));
    }

    @Test
    public void retrievesAllItemsWithMatchingName() {
        pantry.purchase(new ItemBuilder("coffee").withDescription("small").create());
        pantry.purchase(new ItemBuilder("coffee").withDescription("large").create());

        List<Item> coffees = pantry.getItemsNamed("coffee");

        assertThat(coffees.stream().map(Item::getDescription).collect(toList()),
                equalTo(asList("small", "large")));
    }

    @Test
    public void listsItemsExpiringToday() {
        LocalDate today = LocalDate.now();
        pantry.setClock(TestClock.fixedTo(today));
        pantry.purchase(new ItemBuilder("milk").withExpirationDate(today).create());
        pantry.purchase(new ItemBuilder("item expiring tomorrow").withExpirationDate(today.plusDays(1)).create());

        List<Item> items = pantry.getItemsExpiringToday();

        assertThat(items.stream().map(Item::getName).collect(toList()),
                equalTo(singletonList("milk")));
    }
}
