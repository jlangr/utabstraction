package com.langrsoft.pi.pantry;

import java.time.LocalDate;

public class ItemBuilder {
    private String name;
    private String description = "";
    private String sourceName = "";
    private String number = "";
    private LocalDate expirationDate = LocalDate.MAX;

    public ItemBuilder(String name) {
        this.name = name;
    }

    public Item create() {
        Item item = new Item(name);
        item.setDescription(description);
        item.setSourceName(sourceName);
        item.setNumber(number);
        item.setExpirationDate(expirationDate);
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
}
