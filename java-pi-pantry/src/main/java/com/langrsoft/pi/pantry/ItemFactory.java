package com.langrsoft.pi.pantry;

import java.io.IOException;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemFactory {
   private ObjectMapper mapper = new ObjectMapper();

   public Item parse(String responseBody) {
      try {
         Item item = mapper.readValue(responseBody, Item.class);
         item.setSourceName(item.getName());
         item.setExpirationDate(LocalDate.MAX);
         return item;
      } catch (IOException e) {
         throw new ItemParseException();
      }
   }

}
