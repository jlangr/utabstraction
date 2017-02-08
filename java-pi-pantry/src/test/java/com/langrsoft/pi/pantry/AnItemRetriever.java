package com.langrsoft.pi.pantry;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class AnItemRetriever {
    private ItemRetriever retriever;

    // TODO categorize slow
    // TODO suppress logging
    @Test
    @Ignore
    public void retrievesItemInformationViaHttpGet() {
        ItemRetriever retriever = new ItemRetriever();
        Item item = retriever.retrieve("0016000275652");
        assertThat(item.getName(), startsWith("Wheaties"));
    }
}
