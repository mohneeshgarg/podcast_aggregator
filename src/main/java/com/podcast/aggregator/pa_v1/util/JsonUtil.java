package com.podcast.aggregator.pa_v1.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.podcast.aggregator.pa_v1.beans.ObjectMapperFactory;

import java.util.Map;

public class JsonUtil {

    private static ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    public static Map<String, Object> objectToMap(Object obj){
        return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
        });
    }
}
