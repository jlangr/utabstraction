package com.langrsoft.pi.pantry;

import com.langrsoft.util.TestClock;
import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * // Tests for Pantry class
 *
 * author: I'll never tell
 */
public class APantry {
    @Test
    public void contains() {
        try {
            Pantry p = new Pantry();
            Item i1 = new Item("cheerios");
            i1.setDescription("cereal");
            p.purchase(i1);
            assertThat(p.contains("cheerios"), equalTo(true));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void containsFalse() {
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
    public void count() {
        try {
            Pantry p = new Pantry();
            assertThat(p.count("sugar"), is(equalTo(0)));

            p.purchase(new Item("sugar"));

            p.purchase(new Item("cheerios"));
            p.purchase(new Item("cheerios"));
            assertThat(p.count("cheerios"), is(equalTo(2)));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }

    }

    @Test
    public void countAllItems() {
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
    public void getItem() {
        try {
            Pantry pantry = new Pantry();
            Item sugar = new Item("sugar");
            sugar.setDescription("refined sweetener");
            pantry.purchase(sugar);
            Item retrieved = pantry.getItemNamed("sugar");
            assertThat(retrieved, is(not(nullValue()))); // null?
            assertThat(retrieved.getDescription(), is(equalTo("refined sweetener")));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    /**
     * dt
     *
     * ensure purchase date set to today
     */
    @Test
    public void dt() {
        try {
            LocalDateTime todayAtNoon = LocalDate.now().atTime(12, 0, 0);
            Instant todayNoonUTC = todayAtNoon.toInstant(ZoneOffset.UTC);
            Clock clock = Clock.fixed(todayNoonUTC, ZoneOffset.UTC);
            Pantry pantry = new Pantry();
            // inject fake clock
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
    public void nullGetItem() {
        Pantry pant = new Pantry();
        assertThat(pant.getItemNamed("did not purchase"), is(nullValue()));
    }

    @Test
    public void purchaseNull() {
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
    public void getNamed() {
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
    public void expieringToday() {
        try {
            Pantry pantry = new Pantry();
            LocalDate d = LocalDate.now();
            pantry.setClock(TestClock.fixedTo(d));
            Item item1 = new Item("milk");
            item1.setExpirationDate(d);
            pantry.purchase(item1);
            // add another item
            Item item2 = new Item("ruby red lipstick");
            item2.setExpirationDate(d.plusDays(1));
            pantry.purchase(item2);
            // retrieve
            List<Item> items = pantry.getItemsExpiringToday();
            List<String> actualItemNames = new ArrayList<>();
            for (Item i: items) {
                actualItemNames.add(i.getName());
            }
            List<String> expectedItemNames = new ArrayList<>();
            expectedItemNames.add("milk");
            assertThat(actualItemNames,
                    equalTo(expectedItemNames));
        } catch (IllegalArgumentException e) {
            fail("purchase failed:" + e.getMessage());
        }
    }

    @Test
    public void tooMany() {
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
