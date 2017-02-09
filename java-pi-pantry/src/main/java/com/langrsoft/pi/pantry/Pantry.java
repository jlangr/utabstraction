package com.langrsoft.pi.pantry;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Pantry {
    private Map<String, List<Item>> items = new HashMap<>();
    private Clock clock = Clock.systemDefaultZone();
    private static final int CAPACITY = 512;

    public void purchase(Item item) {
        if (item == null) throw new IllegalArgumentException("item");
        if (isAtCapacity()) throw new RuntimeException("too many items");

        item.setPurchaseDate(LocalDate.now(clock));

        List<Item> existingItems = getItemsNamed(item.getName());
        if (existingItems.isEmpty())
            items.put(item.getName(), existingItems);
        existingItems.add(item);
    }

    private boolean isAtCapacity() {
        return count() == CAPACITY;
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }

    public boolean contains(String name) {
        return items.containsKey(name);
    }

    public List<Item> getItemsNamed(String name) {
        if (!contains(name))
            return new ArrayList<>();
        return items.get(name);
    }

    public int count(String name) {
        return getItemsNamed(name).size();
    }

    public int count() {
        return (int) items.values().stream()
                .flatMap(Collection::stream)
                .count();
    }

    public Item getItemNamed(String name) {
        if (!contains(name)) return null;
        return getItemsNamed(name).get(0);
    }

    public List<Item> getItemsExpiringToday() {
        return items.values().stream()
                .flatMap(Collection::stream)
                .filter(item -> item.getExpirationDate().isEqual(LocalDate.now(clock)))
                .collect(Collectors.toList());
    }
}
