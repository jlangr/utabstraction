package com.langrsoft.pi.pantry;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.langrsoft.util.JsonUtil;

public class ItemFactory {
    private Map<String, String> numberToLocalNameMappings = new HashMap<>();
    private Clock clock = Clock.systemDefaultZone();
    private ShelfLifeData shelfLifeData = new ShelfLifeData();

    public Item create(String json ) {
        Item item = JsonUtil.parse(json, Item.class);
        item.setSourceName(item.getName());
        item.setExpirationDate(LocalDate.MAX);
        item.setSellByDate(LocalDate.now(clock));
        changeNameIfLocalMappingExists(item);
        changeCategoryIfRecognized(item);
        return item;
    }

    private void changeCategoryIfRecognized(Item item) {
        if (shelfLifeData.contains(item.getName()))
            item.setCategory(item.getName());
    }

    private void changeNameIfLocalMappingExists(Item item) {
        if (numberToLocalNameMappings.containsKey(item.getNumber()))
            item.setName(numberToLocalNameMappings.get(item.getNumber()));
    }

    public void setNumberToLocalNameMappings(Map<String, String> numberToLocalNameMappings) {
        this.numberToLocalNameMappings = numberToLocalNameMappings;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
