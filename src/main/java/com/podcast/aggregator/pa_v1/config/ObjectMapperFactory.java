package com.podcast.aggregator.pa_v1.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

    public static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
