package com.langrsoft.util;

import com.langrsoft.util.JsonParseException;
import com.langrsoft.util.JsonUtil;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class JSonUtilToJson {
    @Test
    public void convertsObjectToString() {
        assertThat(JsonUtil.toJson(new Object() { public int x = 42; }),
                is(equalTo("{\"x\":42}")));
    }

    @Test(expected=JsonParseException.class)
    public void throwsRuntimeExceptionOnJsonProcessingException() {
        JsonUtil.toJson(new Object());
    }
}
