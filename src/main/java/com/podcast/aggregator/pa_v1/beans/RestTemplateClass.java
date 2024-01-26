package com.podcast.aggregator.pa_v1.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateClass {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
