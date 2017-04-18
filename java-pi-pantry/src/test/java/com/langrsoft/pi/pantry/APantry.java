package com.langrsoft.pi.pantry;

import static com.langrsoft.util.LambdaUtil.map;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

public class APantry {
   private Pantry pantry = new Pantry();

   @Test
   public void containsPurchasedItem() {
      pantry.purchase(new Item("cheerios"));

      boolean contains = pantry.contains("cheerios");

      assertThat(contains, is(true));
   }

   @Test
   public void doesNotContainUnpurchasedItem() {
      assertThat(pantry.contains("sugar"), is(false));
   }

   @Test
   public void hasCountZeroForItemsNotPurchased() {
      assertThat(pantry.count("sugar"), is(equalTo(0)));
   }

   @Test
   public void answersCountOfItemsByName() {
      pantry.purchase(new Item("sugar"));
      pantry.purchase(new Item("cheerios"));
      pantry.purchase(new Item("cheerios"));

      assertThat(pantry.count("cheerios"), is(equalTo(2)));
   }

   @Test
   public void answersCountOfAllItems() {
      pantry.purchase(new Item("sugar"));
      pantry.purchase(new Item("sugar"));
      pantry.purchase(new Item("cheerios"));
      pantry.purchase(new Item("cheerios"));

      int count = pantry.count();

      assertThat(count, is(equalTo(4)));
   }

   @Test
   public void storesAllInformationForPurchasedItems() {
      pantry.purchase(new ItemBuilder("sugar").withDescription("refined sweetener").create());

      Item retrieved = pantry.getItemNamed("sugar");

      assertThat(retrieved.getDescription(), is(equalTo("refined sweetener")));
   }

   @Test
   public void answersNullForUnpurchasedItem() {
      assertThat(pantry.getItemNamed("did not purchase"), is(nullValue()));
   }

   @Test(expected = IllegalArgumentException.class)
   public void throwsOnPurchaseWithNullName() {
      pantry.purchase(null);
   }

   @Test
   public void retrievesItemsByName() {
      pantry.purchase(new ItemBuilder("coffee").withDescription("small").create());
      pantry.purchase(new ItemBuilder("coffee").withDescription("large").create());

      List<Item> items = pantry.getItemsNamed("coffee");

      assertThat(map(items, Item::getDescription), equalTo(asList("small", "large")));
   }

   @Test
   public void returnsEmptyListWhenNoItemPurchasedWithName() {
      assertThat(pantry.getItemsNamed("pizza"), is(emptyList()));
   }

   @Test
   public void throwsWhenPurchasingAboveCapacity() {
      for (int i = 0; i < Pantry.CAPACITY; i++)
         pantry.purchase(new Item());

      try {
         pantry.purchase(new Item());
         fail();
      } catch (RuntimeException expected) {
      }
   }
}
