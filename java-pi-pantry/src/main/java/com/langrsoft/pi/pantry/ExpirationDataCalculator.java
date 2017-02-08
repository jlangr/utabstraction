package com.langrsoft.pi.pantry;

import java.time.LocalDate;

public class ExpirationDataCalculator {
    private ShelfLifeRepository shelfLifeData;

    public ExpirationDataCalculator(ShelfLifeRepository shelfLifeData) {
        this.shelfLifeData = shelfLifeData;
    }

    public LocalDate getExpirationDate(Item item) {
        if (item.getExpirationDate() != null)
            return item.getExpirationDate();
        if (item.getCategory() == null)
            return LocalDate.MAX;
        return getExpirationDate(shelfLifeData.get(item.getCategory()), item.getSellByDate());
    }

    public LocalDate getExpirationDate(ShelfLife shelfLife, LocalDate sellByDate) {
        return sellByDate.plusDays(shelfLife.getRefrigerated());
    }
}
