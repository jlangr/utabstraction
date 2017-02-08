package com.langrsoft.pi.pantry;

import java.time.LocalDate;

public class ItemBuilder {
    private String name;
    private String description;
    private String sourceName;
    private String number;
    private String category;
    private LocalDate expirationDate;
    private LocalDate sellByDate;

    public ItemBuilder(String name) {
        this.name = name;
    }

    public Item create() {
        Item item = new Item(name);
        item.setDescription(description);
        item.setSourceName(sourceName);
        item.setNumber(number);
        item.setExpirationDate(expirationDate);
        item.setSellByDate(sellByDate);
        item.setCategory(category);
        return item;
    }

    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder withSourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public ItemBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public ItemBuilder withExpirationDate(LocalDate date) {
        this.expirationDate = date;
        return this;
    }

    public ItemBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ItemBuilder withSellByDate(LocalDate date) {
        this.sellByDate = date;
        return this;
    }
}
