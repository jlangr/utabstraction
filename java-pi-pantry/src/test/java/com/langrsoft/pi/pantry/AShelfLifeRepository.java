package com.langrsoft.pi.pantry;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AShelfLifeRepository {
    ShelfLifeRepository repo = new ShelfLifeRepository();

    @Test
    public void containsExistingValuesForKnownCategories() {
        assertThat(repo.contains("water"), is(true));
    }

    @Test
    public void allowsStoringShelfLifeInfoForNewCategories() {
        int refrigeratedDaysOfLife = 42;
        repo.add("goop", new ShelfLife(refrigeratedDaysOfLife, 0));

        assertThat(repo.get("goop").getRefrigerated(), is(equalTo(refrigeratedDaysOfLife)));
    }
}
