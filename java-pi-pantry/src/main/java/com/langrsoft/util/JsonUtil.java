package com.langrsoft.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// TODO add tests

public class JsonUtil {
    public static <T> String toJson(T object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> T parse(String json, Class<T> type) {
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
}
