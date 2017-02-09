package com.langrsoft.pi.pantry;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

// based on http://site.foodshare.org/site/DocServer/Food_Storage_and_Shelf_Life_Guidelines.pdf?docID=5822
public class ShelfLifeRepository {
    private Map<String, ShelfLife> shelfLifeByCategory = new HashMap<>();
    private Map<String, ShelfLife> data = ImmutableMap.<String, ShelfLife>builder()
            .put("shredded high moisture cheese", new ShelfLife(21, 180))
            .put("shredded low moisture cheese", new ShelfLife(30, 180))
            .put("high moisture cheese", new ShelfLife(14, 180))
            .put("string cheese", new ShelfLife(14, 180))
            .put("hard cheese", new ShelfLife(180, 180))
            .put("processed cheese", new ShelfLife(30, 180))
            .put("margarine", new ShelfLife(180, 360))
            .put("cream cheese", new ShelfLife(60, 0))
            .put("yogurt", new ShelfLife(14, 30))
            .put("cottage cheese", new ShelfLife(14, 0))
            .put("ricotta cheese", new ShelfLife(14, 0))
            .put("sour cream", new ShelfLife(14, 0))
            .put("butter", new ShelfLife(60, 360))
            .put("milk", new ShelfLife(5, 0))
            .put("buttermilk", new ShelfLife(10, 0))
            .put("half and half", new ShelfLife(3, 120))
            .put("heavy cream", new ShelfLife(10, 90))
            .put("light cream", new ShelfLife(7, 90))
            .put("eggs", new ShelfLife(28, 0))
            .put("juice", new ShelfLife(21, 240))
            .put("yeast", new ShelfLife(720, 2520))
            .put("ice cream", new ShelfLife(0, 120))
            .put("bbq sauce", new ShelfLife(360, 0))
            .put("honey", new ShelfLife(Integer.MAX_VALUE, 0))
            .put("jam", new ShelfLife(540, 0))
            .put("jelly", new ShelfLife(540, 0))
            .put("ketchup", new ShelfLife(540, 0))
            .put("molasses", new ShelfLife(720, 0))
            .put("olives", new ShelfLife(360, 0))
            .put("salad dressing", new ShelfLife(360, 0))
            .put("salsa", new ShelfLife(360, 0))
            .put("spaghetti sauce", new ShelfLife(540, 0))
            .put("canned beans", new ShelfLife(1080, 0))
            .put("canned tuna", new ShelfLife(1080, 0))
            .put("canned salmon", new ShelfLife(180, 0))
            .put("canned sardines", new ShelfLife(180, 0))
            .put("canned crab", new ShelfLife(180, 0))
            .put("frosting", new ShelfLife(300, 0))
            .put("canned fruit", new ShelfLife(360, 0))
            .put("jarred sauerkraut", new ShelfLife(360, 0))
            .put("tomatoes", new ShelfLife(360, 0))
            .put("tomato sauce", new ShelfLife(360, 0))
            .put("jarred gravy", new ShelfLife(720, 0))
            .put("canned soup", new ShelfLife(720, 0))
            .put("jarred cream sauce", new ShelfLife(720, 0))
            .put("vegetables", new ShelfLife(720, 0))
            .put("pie filling", new ShelfLife(1080, 0))
            .put("pancake mix", new ShelfLife(270, 0, 1800))
            .put("cake mix", new ShelfLife(360, 0, 1800))
            .put("brownie mix", new ShelfLife(360, 0, 1800))
            .put("baking powder", new ShelfLife(540, 0, 1800))
            .put("baking soda", new ShelfLife(Integer.MAX_VALUE, 0, Integer.MAX_VALUE))
            .put("beans", new ShelfLife(360, 0, 10800))
            .put("bouillon", new ShelfLife(360, 0, 1800))
            .put("bread", new ShelfLife(5, 90))
            .put("cake", new ShelfLife(4, 90))
            .put("cereal", new ShelfLife(360, 0))
            .put("cookies", new ShelfLife(120, 120))
            .put("cornmeal", new ShelfLife(360, 720))
            .put("crackers", new ShelfLife(240, 0, 360))
            .put("white flour", new ShelfLife(360, 0, 1800))
            .put("whole wheat flour", new ShelfLife(90, 0, 1800))
            .put("dried fruit", new ShelfLife(180, 0, 1800))
            .put("macaroni and cheese", new ShelfLife(270, 0))
            .put("shelled nuts", new ShelfLife(180, 0))
            .put("nuts", new ShelfLife(180, 0))
            .put("oatmeal", new ShelfLife(360, 0, 10800))
            .put("oil", new ShelfLife(180, 0))
            .put("pasta", new ShelfLife(720, 0, 10800))
            .put("peanut butter", new ShelfLife(540, 0, 1440))
            .put("popcorn", new ShelfLife(720, 0, 1800))
            .put("microwave popcorn", new ShelfLife(360, 0))
            .put("instant potatoes", new ShelfLife(360, 0, 2520))
            .put("brown rice", new ShelfLife(360, 1080))
            .put("white rice", new ShelfLife(720, 0, 10800))
            .put("rice mix", new ShelfLife(180, 0))
            .put("shortening", new ShelfLife(240, 0))
            .put("spice", new ShelfLife(1440, 0))
            .put("stuffing mix", new ShelfLife(270, 0))
            .put("brown sugar", new ShelfLife(540, 0, Integer.MAX_VALUE))
            .put("white sugar", new ShelfLife(720, 0, Integer.MAX_VALUE))
            .put("confectioner's sugar", new ShelfLife(540, 0, Integer.MAX_VALUE))
            .put("sugar substitute", new ShelfLife(720, 0, Integer.MAX_VALUE))
            .put("PopTarts", new ShelfLife(270, 0))
            .put("PopTarts with fruit", new ShelfLife(180, 0))
            .put("tortillas", new ShelfLife(90, 180))
            .put("cocoa mix", new ShelfLife(1080, 0, 5400))
            .put("liquid coffee creamer", new ShelfLife(270, 0))
            .put("powdered coffee creamer", new ShelfLife(720, 0))
            .put("ground coffee", new ShelfLife(720, 720))
            .put("instant coffee", new ShelfLife(360, 0))
            .put("bottled juice", new ShelfLife(270, 0))
            .put("juice box", new ShelfLife(120, 0))
            .put("canned juice", new ShelfLife(540, 0))
            .put("evaporated milk", new ShelfLife(360, 0))
            .put("non-fat dry milk", new ShelfLife(360, 0, 7200))
            .put("sweetened milk", new ShelfLife(360, 0))
            .put("condensed milk", new ShelfLife(360, 0))
            .put("rice milk", new ShelfLife(180, 0))
            .put("soy milk", new ShelfLife(180, 0))
            .put("bagged tea", new ShelfLife(540, 0))
            .put("instant tea", new ShelfLife(1080, 0))
            .put("loose leaf tea", new ShelfLife(720, 0))
            .put("water", new ShelfLife(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE))
            .put("salmon", new ShelfLife(2, 90))
            .put("fish", new ShelfLife(2, 360))
            .put("raw shrimp", new ShelfLife(2, 270))
            .put("crab legs", new ShelfLife(3, 0))
            .put("shucked oysters", new ShelfLife(1, 90))
            .put("raw lobster tails", new ShelfLife(4, 270))
            .put("raw scallops", new ShelfLife(1, 90))
            .put("roast beef", new ShelfLife(3, 360))
            .put("steak", new ShelfLife(3, 360))
            .put("ground beef", new ShelfLife(2, 270))
            .put("roast pork", new ShelfLife(3, 360))
            .put("pork chops", new ShelfLife(3, 360))
            .put("ground pork", new ShelfLife(2, 270))
            .put("roast lamb", new ShelfLife(3, 360))
            .put("lamb chops", new ShelfLife(3, 360))
            .put("ground lamb", new ShelfLife(2, 270))
            .put("poultry", new ShelfLife(2, 360))
            .put("ground poultry", new ShelfLife(2, 270))
            .put("bacon", new ShelfLife(7, 180, 14))
            .put("ham", new ShelfLife(7, 360, 14))
            .put("hot dogs", new ShelfLife(14, 270))
            .put("lunch meat", new ShelfLife(3, 60, 14))
            .put("pepperoni", new ShelfLife(30, 0, 180))
            .put("salami", new ShelfLife(30, 0, 180))
            .put("raw sausage", new ShelfLife(2, 180))
            .put("sausage", new ShelfLife(7, 270))
            .build();

    public ShelfLifeRepository() {
        shelfLifeByCategory.putAll(data);
    }

    public boolean contains(String category) {
        return shelfLifeByCategory.containsKey(category);
    }

    public ShelfLife get(String category) {
        return shelfLifeByCategory.get(category);
    }

    public void add(String category, ShelfLife shelfLife) {
        shelfLifeByCategory.put(category, shelfLife);
    }
}
