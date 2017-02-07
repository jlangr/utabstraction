package com.langrsoft.pi.pantry;

import java.time.LocalDate;
import java.util.*;

public class Pantry {
   private Map<String, List<Item>> items = new HashMap<>();

   public void purchase(Item item) {
      item.setPurchaseDate(LocalDate.now()); // TODO use clock

      List<Item> existingItems = getItemsNamed(item.getName());
      if (existingItems.isEmpty())
         items.put(item.getName(), existingItems);
      existingItems.add(item);
   }

   public boolean contains(String name) {
      return items.containsKey(name);
   }

   public List<Item> getItemsNamed(String name) {
      if (!contains(name))
         return new ArrayList<Item>();
      return items.get(name);
   }

   public int count(String name) {
      return getItemsNamed(name).size();
   }

   public Item getItemNamed(String name) {
      if (!contains(name)) return null;
      return getItemsNamed(name).get(0);
   }

}
