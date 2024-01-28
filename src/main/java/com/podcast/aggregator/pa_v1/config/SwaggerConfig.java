package com.podcast.aggregator.pa_v1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SwaggerConfig {

    private String APP_NAME = "PodCasts Aggregator";
    private String APP_VERSION = "1.0";

    @Bean
    public OpenAPI readAndWriteApi(){
        return new OpenAPI().info(appInfo());
    }

    public Info appInfo(){
        return new Info()
                .title(APP_NAME)
                .version(APP_VERSION);
    }

}
