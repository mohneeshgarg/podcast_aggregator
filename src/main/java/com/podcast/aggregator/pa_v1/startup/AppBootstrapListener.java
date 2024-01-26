package com.podcast.aggregator.pa_v1.startup;

import com.podcast.aggregator.pa_v1.util.YoutubeDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppBootstrapListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private YoutubeDataUtil youtubeDataUtil;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Fetching all the content from youtube on app startup!!");
        youtubeDataUtil.fetchAllVideosFromYoutube();
    }
}
