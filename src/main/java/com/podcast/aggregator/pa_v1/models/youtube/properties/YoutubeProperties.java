package com.podcast.aggregator.pa_v1.models.youtube.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "youtube")
public class YoutubeProperties {

    private String url;

    private String key;

    private Integer maxResults;

}
