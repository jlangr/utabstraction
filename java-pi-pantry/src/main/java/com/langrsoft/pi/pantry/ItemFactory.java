package com.langrsoft.pi.pantry;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.langrsoft.util.JsonUtil;

public class ItemFactory {
    private Map<String, String> numberToLocalNameMappings = new HashMap<>();

    public Item create(String responseBody) {
        Item item = JsonUtil.parse(responseBody, Item.class);
        item.setSourceName(item.getName());
        item.setExpirationDate(LocalDate.MAX);
        changeNameIfLocalMappingExists(item);
        return item;
    }

    private void changeNameIfLocalMappingExists(Item item) {
        if (numberToLocalNameMappings.containsKey(item.getNumber()))
            item.setName(numberToLocalNameMappings.get(item.getNumber()));
    }

    public void setNumberToLocalNameMappings(Map<String, String> numberToLocalNameMappings) {
        this.numberToLocalNameMappings = numberToLocalNameMappings;
    }
}
