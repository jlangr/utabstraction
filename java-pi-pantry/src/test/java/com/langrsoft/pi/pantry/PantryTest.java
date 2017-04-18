package com.langrsoft.pi.pantry;

import static com.langrsoft.util.LambdaUtil.map;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.time.*;
import java.util.*;
import org.junit.Test;
import com.langrsoft.util.TestClock;

/**
 * // Tests for Pantry class
 *
 * author: I'll never tell
 */
public class PantryTest {
   static final LocalDate NOW = LocalDate.now();

   private Pantry pantry = new Pantry();

   @Test
   public void contains1() {
      try {
         Pantry pantry = new Pantry();
         Item i1 = new Item("cheerios");
         pantry.purchase(i1);
         assertThat(pantry.contains("cheerios"), equalTo(true));
      } catch (IllegalArgumentException e) {
         fail("purchase failed:" + e.getMessage());
      }
   }

   /**
    * contains2
    */
   @Test
   public void contains2() {
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

   /**
    * count2
    *
    * count of items by name
    */
   @Test
   public void count() {
      try {
         Pantry pantry = new Pantry();
         assertThat(pantry.count("sugar"), is(equalTo(0)));

         pantry.purchase(new Item("sugar"));

         pantry.purchase(new Item("cheerios"));

         pantry.purchase(new Item("cheerios"));
         assertThat("pantry count not correct for cheerioes", pantry.count("cheerios"), is(equalTo(2)));

      } catch (IllegalArgumentException e) {
         fail("purchase failed:" + e.getMessage());
      }

   }

   /**
    * count
    *
    * count of all items
    */
   @Test
   public void count2() {
      Pantry pantry = new Pantry();
      pantry.purchase(new Item("sugar"));
      pantry.purchase(new Item("sugar"));
      pantry.purchase(new Item("cheerios"));
      pantry.purchase(new Item("cheerios"));
      assertThat("pantry count not 4", pantry.count(), is(equalTo(4)));
   }

   /**
    * dt
    *
    * ensure purchase date set to today
    */
   @Test
   public void get0() {
      Pantry pantry = new Pantry();
      Item sugar = new Item("sugar");
      sugar.setDescription("refined sweetener");
      pantry.purchase(sugar);
      Item retrieved = pantry.getItemNamed("sugar");
      assertThat("item should not be null", retrieved, is(not(nullValue()))); // null?
      assertThat(retrieved.getDescription(), is(equalTo("refined sweetener")));
   }

   /**
    * dt
    *
    * ensure purchase date set to today
    * uses the java clock class as a basis for the fake
    */
   @Test
   public void dt() {
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
   }

   @Test
   public void get3() {
      Pantry pantry = new Pantry();
      assertThat(pantry.getItemNamed("did not purchase"), is(nullValue()));
   }

   @Test
   public void purchaseNull() {
      try {
         Pantry pantry = new Pantry();
         pantry.purchase(null);
         fail("should have thrown IllegalArgumentException");
      } catch (IllegalArgumentException e) {
         // ok
      }
   }

   @Test
   public void get2() {
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
   }

   @Test
   public void expieringToday() {
      Pantry pantry = new Pantry();
      LocalDate d = LocalDate.now();
      pantry.setClock(TestClock.fixedTo(d));
      Item item1 = new Item("milk");
      item1.setCategory("milk");
      item1.setExpirationDate(d);
      pantry.purchase(item1);
      // add another item
      Item item2 = new Item("blood orange juice 24oz");
      item2.setExpirationDate(d.plusDays(1));
      item2.setCategory("orange juice");
      pantry.purchase(item2);
      // retrieve
      List<Item> items = pantry.getItemsExpiringToday();
      List<String> actualItemNames = new ArrayList<>();
      for (Item i: items) {
         actualItemNames.add(i.getName());
      }
      System.out.println("actual item names:" + actualItemNames);
      List<String> expectedItemNames = new ArrayList<>();
      expectedItemNames.add("milk");
      assertThat(actualItemNames, equalTo(expectedItemNames));
   }


   @Test
   public void allExpiredItemsIncludesItemsExpiringTodayOrEarlier() {
      pantry.setClock(TestClock.fixedTo(NOW));
      pantry.purchase(new ItemBuilder("milk").withExpirationDate(NOW).create());
      pantry.purchase(new ItemBuilder("juice").withExpirationDate(NOW.plusDays(1)).create());
      pantry.purchase(new ItemBuilder("curdled milk").withExpirationDate(NOW.minusDays(1)).create());

      List<Item> items = pantry.getAllExpiredItems();

      assertThat(map(items, Item::getName),
         containsInAnyOrder("milk", "curdled milk"));
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
      } catch (RuntimeException e) {
         // ok
      }
   }
}
