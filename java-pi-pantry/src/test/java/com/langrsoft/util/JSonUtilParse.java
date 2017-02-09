package com.langrsoft.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class JSonUtilParse {
    static class X {
        public int y;
    }

    @Test
    public void createsInstanceOfClassFromJson() {
        X x = JsonUtil.parse("{\"y\": 42}", X.class);

        assertThat(x.y, is(equalTo(42)));
    }

    @Test(expected=JsonParseException.class)
    public void throwsRuntimeExceptionOnJsonParseException() {
        JsonUtil.parse("?", X.class);
    }
}
