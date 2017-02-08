package com.langrsoft.pi.pantry;

public class ItemBuilder {
    private String name;
    private String description = "";
    private String sourceName = "";

    public ItemBuilder(String name) {
        this.name = name;
    }

    public Item create() {
        Item item = new Item(name);
        item.setDescription(description);
        item.setSourceName(sourceName);
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
}
