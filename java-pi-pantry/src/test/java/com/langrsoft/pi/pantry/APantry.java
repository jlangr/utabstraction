package com.langrsoft.pi.pantry;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.time.*;
import java.util.*;
import static java.util.stream.Collectors.toList;
import org.junit.*;

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
   public void countOfItemPurchase() {
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
      // TODO use clock
//      Clock clock = Clock.fixed(Instant., zone);
//      LocalDate today = LocalDate.now(clock);
      Item peanutButter = new Item("peanut butter");

      pantry.purchase(peanutButter);

      Item retrieved = pantry.getItemNamed("peanut butter");
      assertThat(retrieved.getPurchaseDate(), is(equalTo(LocalDate.now())));
   }

   @Test
   public void itemRetrievalByNameReturnsNullWhenNotFound() {
      assertThat(pantry.getItemNamed("did not purchase"), is(nullValue()));
   }

   @Test
   public void retrievesAllItemsForName() {
      Item smallCoffee = new ItemBuilder("coffee").withDescription("small").create();
      Item largeCoffee = new ItemBuilder("coffee").withDescription("large").create();

      pantry.purchase(smallCoffee);
      pantry.purchase(largeCoffee);

      List<Item> coffees = pantry.getItemsNamed("coffee");
      assertThat(coffees.stream().map(coffee -> coffee.getDescription()).collect(toList()),
         equalTo(Arrays.asList("small", "large")));
   }
}
