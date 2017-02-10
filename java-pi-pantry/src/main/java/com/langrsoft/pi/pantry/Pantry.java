package com.langrsoft.pi.pantry;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pantry {
    private Map<String, List<Item>> items = new HashMap<>();
    private Clock clock = Clock.systemDefaultZone();
    private static final int CAPACITY = 512;

    public void purchase(Item item) {
        if (item == null) throw new IllegalArgumentException("item");
        if (isAtCapacity()) throw new RuntimeException("too many items");

        item.setPurchaseDate(LocalDate.now(clock));

        addItem(item);
    }

    private void addItem(Item item) {
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
        return (int) flatten(items.values()).count();
    }

    public Item getItemNamed(String name) {
        if (!contains(name)) return null;
        return getItemsNamed(name).get(0);
    }

    public List<Item> getItemsExpiringToday() {
        return filterFlattened(items.values(),
                item -> item.getExpirationDate().isEqual(LocalDate.now(clock)));
    }

    public List<Item> getAllExpiredItems() {
        return filterFlattened(items.values(),
                item -> !item.getExpirationDate().isAfter(LocalDate.now(clock)));
    }

    private <T> List<T> filterFlattened(Collection<List<T>> values, Predicate<T> predicate) {
        return flatten(values)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private <T> Stream<T> flatten(Collection<List<T>> values) {
        return values.stream().flatMap(Collection::stream);
    }
}
