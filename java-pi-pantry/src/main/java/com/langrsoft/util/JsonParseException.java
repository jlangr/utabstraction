package com.langrsoft.util;

import java.io.IOException;

public class JsonParseException extends RuntimeException {
   private static final long serialVersionUID = 1L;

    public JsonParseException(IOException e) {
        super(e);
    }
}
