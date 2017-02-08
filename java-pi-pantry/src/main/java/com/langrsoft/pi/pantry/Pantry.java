package com.langrsoft.pi.pantry;

import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Pantry {
    private Map<String, List<Item>> items = new HashMap<>();
    private Clock clock = Clock.systemDefaultZone();

    public void purchase(Item item) {
        item.setPurchaseDate(LocalDate.now(clock));

        List<Item> existingItems = getItemsNamed(item.getName());
        if (existingItems.isEmpty())
            items.put(item.getName(), existingItems);
        existingItems.add(item);
    }

    void setClock(Clock clock) {
        this.clock = clock;
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

    public List<Item> getItemsExpiringToday() {
        return items.values().stream()
                .flatMap(c -> c.stream())
                .filter(item -> item.getExpirationDate().isEqual(LocalDate.now(clock)))
                .collect(Collectors.toList());
    }
}
