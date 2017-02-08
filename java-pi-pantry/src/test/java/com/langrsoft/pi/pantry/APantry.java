package com.langrsoft.pi.pantry;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static java.util.Arrays.asList;
import java.time.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

import com.langrsoft.util.TestClock;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;

public class APantry {
    Pantry pantry = new Pantry();

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

        assertThat(containsItem, not(equalTo(true)));
    }

    @Test
    public void countOfItemNotPurchasedIsZero() {
        assertThat(pantry.count("sugar"), is(equalTo(0)));
    }

    @Test
    public void tracksCountOfItemsPurchased() {
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
        LocalDate now = LocalDate.now();
        pantry.setClock(TestClock.fixedTo(now));

        pantry.purchase(new Item("peanut butter"));

        Item retrieved = pantry.getItemNamed("peanut butter");
        assertThat(retrieved.getPurchaseDate(), is(equalTo(now)));
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

        assertThat(coffees.stream().map(coffee -> coffee.getDescription()).collect(toList()),
                equalTo(asList("small", "large")));
    }

    @Test
    public void listsItemsExpiringToday() {
        LocalDate today = LocalDate.now();
        pantry.setClock(TestClock.fixedTo(today));
        pantry.purchase(new ItemBuilder("milk").withExpirationDate(today).create());
        pantry.purchase(new ItemBuilder("item expiring tomorrow").withExpirationDate(today.plusDays(1)).create());

        List<Item> items = pantry.getItemsExpiringToday();

        assertThat(items.stream().map(item -> item.getName()).collect(toList()),
                equalTo(asList("milk")));
    }
}
