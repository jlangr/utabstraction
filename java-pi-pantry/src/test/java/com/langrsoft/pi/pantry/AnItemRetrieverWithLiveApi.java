package com.langrsoft.pi.pantry;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

public class AnItemRetrieverWithLiveApi {
    // TODO mark as slow test?
    @Ignore("for now")
    @Test
    public void retrievesItemInformationViaHttpGet() {
        ItemRetriever retriever = new ItemRetriever();
        Item item = retriever.retrieve("0016000275652");
        assertThat(item.getName(), startsWith("Wheaties"));
    }
}
