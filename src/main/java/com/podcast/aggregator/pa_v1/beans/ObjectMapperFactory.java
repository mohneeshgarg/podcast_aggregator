package com.podcast.aggregator.pa_v1.beans;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

    public static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
