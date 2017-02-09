package com.langrsoft.pi.pantry;

import com.langrsoft.util.TestClock;
import org.junit.Test;

import java.time.*;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class APantry {
    @Test
    public void containsAPurchasedItem() {
        try {
            Pantry pantry = new Pantry();
            Item cheerios = new Item("cheerios");
            cheerios.setDescription("cereal");
            pantry.purchase(cheerios);
            assertThat(pantry.contains("cheerios"), equalTo(true));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void doesNotContainItemNotPurchased() {
        try {
            Pantry pantry = new Pantry();
            Item cheerios = new Item("cheerios");
            cheerios.setDescription("cereal");
            pantry.purchase(cheerios);
            assertThat(pantry.contains("sugar"), is(equalTo(false)));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void countOfItemNotPurchasedIsZero() {
        Pantry pantry = new Pantry();
        assertThat(pantry.count("sugar"), is(equalTo(0)));
    }

    @Test
    public void answersCountOfItemsPurchasedByName() {
        try {
            Pantry pantry = new Pantry();
            pantry.purchase(new Item("sugar"));
            pantry.purchase(new Item("cheerios"));
            pantry.purchase(new Item("cheerios"));
            assertThat(pantry.count("cheerios"), is(equalTo(2)));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void answersCountOfAllItemsPurchased() {
        try {
            Pantry pantry = new Pantry();
            pantry.purchase(new Item("sugar"));
            pantry.purchase(new Item("sugar"));
            pantry.purchase(new Item("cheerios"));
            pantry.purchase(new Item("cheerios"));
            assertThat(pantry.count(), is(equalTo(4)));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void retainsItemDetails() {
        try {
            Pantry pantry = new Pantry();
            Item sugar = new Item("sugar");
            sugar.setDescription("refined sweetener");
            pantry.purchase(sugar);
            Item retrieved = pantry.getItemNamed("sugar");
            assertThat(retrieved, is(not(nullValue())));
            assertThat(retrieved.getDescription(), is(equalTo("refined sweetener")));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void attachesDateToThePurchase() {
        try {
            LocalDateTime todayAtNoon = LocalDate.now().atTime(12, 0, 0);
            Instant todayNoonUTC = todayAtNoon.toInstant(ZoneOffset.UTC);
            Clock clock = Clock.fixed(todayNoonUTC, ZoneOffset.UTC);
            Pantry pantry = new Pantry();
            pantry.setClock(clock);
            Item eggs = new Item("eggs");
            eggs.setDescription("oval ovum");
            pantry.purchase(eggs);
            Item retrieved = pantry.getItemNamed("eggs");
            assertThat(retrieved, is(not(nullValue())));
            assertThat(retrieved.getPurchaseDate(), is(equalTo(LocalDate.now())));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void returnsNullWhenRetrieveByItemNameFindsNothing() {
        Pantry pantry = new Pantry();
        assertThat(pantry.getItemNamed("did not purchase"), is(nullValue()));
    }

    @Test
    public void throwsOnPurchaseOfNullItem() {
        try {
            Pantry pantry = new Pantry();
            pantry.purchase(null);
            fail("should have thrown IllegalArgumentException");
        }
        catch(IllegalArgumentException e) {
            // ok
        }
    }

    @Test
    public void retrievesAllItemsWithMatchingName() {
        try {
            Pantry pantry = new Pantry();
            Item smallCoffee = new Item("coffee");
            smallCoffee.setDescription("small");
            Item largeCoffee = new Item("coffee");
            largeCoffee.setDescription("large");
            pantry.purchase(smallCoffee);
            pantry.purchase(largeCoffee);
            List<Item> coffees = pantry.getItemsNamed("coffee");
            assertThat(coffees.stream().map(Item::getDescription).collect(toList()),
                    equalTo(asList("small", "large")));
            List<Item> items = pantry.getItemsNamed("pizza");
            assertThat(items, is(emptyList()));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void listsItemsExpiringToday() {
        try {
            Pantry pantry = new Pantry();
            LocalDate today = LocalDate.now();
            pantry.setClock(TestClock.fixedTo(today));
            Item item1 = new Item("milk");
            item1.setExpirationDate(today);
            pantry.purchase(item1);
            Item item2 = new Item("ruby red lipstick");
            item2.setExpirationDate(today.plusDays(1));
            pantry.purchase(item2);
            List<Item> items = pantry.getItemsExpiringToday();
            assertThat(items.stream().map(Item::getName).collect(toList()),
                    equalTo(singletonList("milk")));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void throwsWhenAddingTooManyItems() {
        Pantry pantry = new Pantry();
        for (int i = 0; i < 512; i++) {
            Item item = new Item();
            pantry.purchase(item);
        }

        try {
            Item item = new Item();
            pantry.purchase(item);
            fail("expected exception");
        }
        catch (RuntimeException e)
        {
            // ok
        }
    }
}
