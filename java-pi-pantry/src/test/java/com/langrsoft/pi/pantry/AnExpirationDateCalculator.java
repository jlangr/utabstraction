package com.langrsoft.pi.pantry;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnExpirationDateCalculator {
    static final LocalDate TODAY = LocalDate.now();
    private ExpirationDataCalculator calculator;
    private ShelfLifeRepository data;

    @Before
    public void create() {
        data = new ShelfLifeRepository();
        calculator = new ExpirationDataCalculator(data);
    }

    @Test
    public void returnsItemExpirationDateWhenSet() {
        Item item = new ItemBuilder("").withExpirationDate(TODAY).create();

        LocalDate expiration = calculator.getExpirationDate(item);

        assertThat(expiration, is(equalTo(TODAY)));
    }

    @Test
    public void returnsIndefiniteWhenNeitherExpirationNorCategorySet() {
        assertThat(calculator.getExpirationDate(new Item()), is(equalTo(LocalDate.MAX)));
    }

    @Test
    public void calculatesUsingShelfLifeAndSellByDate() {
        int refrigerationLife = 10;
        data.add("smelt", new ShelfLife(refrigerationLife, 0));
        Item item = new ItemBuilder("").withCategory("smelt").withSellByDate(TODAY).create();

        LocalDate expiration = calculator.getExpirationDate(item);

        assertThat(expiration, is(equalTo(TODAY.plusDays(refrigerationLife))));
    }
}
