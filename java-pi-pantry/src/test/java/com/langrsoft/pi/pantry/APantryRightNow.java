package com.langrsoft.pi.pantry;

import java.time.LocalDate;
import java.util.*;
import org.junit.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import com.langrsoft.util.TestClock;
import static com.langrsoft.util.LambdaUtil.map;
import static java.util.Arrays.asList;

public class APantryRightNow {
   static final LocalDate TODAY = LocalDate.now();

   Pantry pantry = new Pantry();

   @Before
   public void setPantryClockToToday() {
      pantry.setClock(TestClock.fixedTo(TODAY));
   }

   @Test
   public void storesCurrentTimestampForPurchasedItems() {
      pantry.purchase(new Item("eggs"));

      assertThat(pantry.getItemNamed("eggs").getPurchaseDate(), is(equalTo(TODAY)));
   }

   @Test
   public void answersItemsExpiringToday() {
      pantry.purchase(new ItemBuilder("milk").withExpirationDate(TODAY).create());
      pantry.purchase(new ItemBuilder("juice").withExpirationDate(TODAY.plusDays(1)).create());

      List<Item> items = pantry.getItemsExpiringToday();

      assertThat(map(items, Item::getName), equalTo(asList("milk")));
   }

   @Test
   public void answersAllExpiredItems() {
      pantry.purchase(new ItemBuilder("milk").withExpirationDate(TODAY).create());
      pantry.purchase(new ItemBuilder("juice").withExpirationDate(TODAY.plusDays(1)).create());
      pantry.purchase(new ItemBuilder("curdled milk").withExpirationDate(TODAY.minusDays(1)).create());

      List<Item> items = pantry.getAllExpiredItems();

      assertThat(map(items, Item::getName), containsInAnyOrder("milk", "curdled milk"));
   }

}
