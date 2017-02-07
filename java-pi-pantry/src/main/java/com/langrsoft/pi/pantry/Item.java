package com.langrsoft.pi.pantry;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
   // see http://stackoverflow.com/questions/10519265/jackson-overcoming-underscores-in-favor-of-camel-case for alternatives
   @JsonProperty("valid")
   private boolean isValid;
   @JsonProperty("itemname")
   private String name;
   private String alias;
   private String description;
   @JsonProperty("avg_price")
   private String averagePrice;
   @JsonProperty("rate_up")
   private int rateUp;
   @JsonProperty("rate_down")
   private int rateDown;
   private String number;

   private LocalDate purchaseDate;
   private LocalDate expirationDate;
   private String sourceName;

   public Item() {}

   public Item(String name) {
      this.name = name;
   }

   public Item(String name, String description) {
      this(name);
      this.description = description;
   }

   public String getNumber() {
      return number;
   }

   public String getAlias() {
      return alias;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public boolean isValid() {
      return isValid;
   }

   public String getAveragePrice() {
      return averagePrice;
   }

   public int getRateUp() {
      return rateUp;
   }

   public int getRateDown() {
      return rateDown;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public LocalDate getPurchaseDate() {
      return purchaseDate;
   }

   public void setPurchaseDate(LocalDate date) {
      this.purchaseDate = date;
   }

   public String getSourceName() {
      return sourceName;
   }

   public void setSourceName(String sourceName) {
      this.sourceName = sourceName;
   }

   public LocalDate getExpirationDate() {
      return expirationDate;
   }

   public void setExpirationDate(LocalDate date) {
      expirationDate = date;
   }
}
