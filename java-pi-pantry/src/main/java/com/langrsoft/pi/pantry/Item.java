package com.langrsoft.pi.pantry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Item {
    // see http://stackoverflow.com/questions/10519265/jackson-overcoming-underscores-in-favor-of-camel-case for alternatives
    @JsonProperty("valid")
    private boolean isValid;
    @JsonProperty("itemname")
    private String name;
    // alias is used by the UPC API for "developer" purposes. We will ignore it and track a sourceName instead.
    private String alias;
    private String description;
    @JsonProperty("avg_price")
    private String averagePrice;
    @JsonProperty("rate_up")
    private int rateUp;
    @JsonProperty("rate_down")
    private int rateDown;
    private String number;

    @JsonIgnore
    private LocalDate purchaseDate;
    @JsonIgnore
    private LocalDate expirationDate;
    @JsonIgnore
    private String sourceName;
    @JsonIgnore
    private String category;
    @JsonIgnore
    private LocalDate sellByDate;

    public Item() {
    }

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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getSellByDate() {
        return sellByDate;
    }

    public void setSellByDate(LocalDate sellByDate) {
        this.sellByDate = sellByDate;
    }
}
