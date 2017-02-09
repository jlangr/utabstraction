package com.langrsoft.pi.pantry;

import com.langrsoft.testutil.Slow;
import com.langrsoft.util.HttpClient;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

public class AnItemRetrieverWithLiveApi {
    @Ignore("cannot run without valid API key set in property")
    @Category(Slow.class)
    @Test
    public void retrievesItemInformationViaLiveHttpGet() {
        ItemRetriever retriever = new ItemRetriever(new HttpClient());
        Item item = retriever.retrieve("0016000275652");
        assertThat(item.getName(), startsWith("Wheaties"));
    }
}
